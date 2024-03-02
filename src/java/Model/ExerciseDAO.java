/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author HA GIA KHANH
 */
public class ExerciseDAO {

    public boolean addExcercise(String title, String description, String media, String status, int classID, int lecturerID) {
        Connection con = null;
        PreparedStatement preStm = null;
        boolean check = false;
        String sql;
        try {
            con = DBUtils.getConnection();
            sql = "INSERT INTO [dbo].[Excercises]\n"
                    + "           ([title], [media], [description], [status], [class_id], [lecturer_id])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, title);
            preStm.setString(2, media);
            preStm.setString(3, description);
            preStm.setString(4, status);
            preStm.setInt(5, classID);
            preStm.setInt(6, lecturerID);
            if (preStm.executeUpdate() > 0) {
                check = true;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Insert CLasses: " + e.getMessage());
            e.getStackTrace();
        }

        return check;

    }

    public List<ExerciseDTO> getAllExcercise(int classID) {
        List<ExerciseDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement preStm = null;
        String sql;
        ExerciseDTO exc = new ExerciseDTO();
        ClassesDTO classes = null;
        ClassesDAO classDao = new ClassesDAO();
        LectureDTO lecturer = null;
        LectureDAO lectureDao = new LectureDAO();
        try {
            sql = "SELECT [excercise_id]\n"
                    + "      ,[title]\n"
                    + "      ,[media]\n"
                    + "      ,[description]\n"
                    + "      ,[status]\n"
                    + "      ,[created_date]\n"
                    + "      ,[class_id]\n"
                    + "      ,[lecturer_id]\n"
                    + "  FROM [dbo].[Excercises] WHERE [class_id] = ? ";
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, classID);
            ResultSet rs = preStm.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("excercise_id");
                    String title = rs.getString("title");
                    String media = rs.getString("media");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Date created_date = rs.getDate("created_date");
                    int lectureID = rs.getInt("lecturer_id");
                    classes = classDao.showClassById(classID);
                    lecturer = lectureDao.searchLectureById(lectureID);
                    exc = new ExerciseDTO(id, title, media, description, status, created_date, classes, lecturer);
                    list.add(exc);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    public ExerciseDTO loadExcercise(int excerciseId) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        ExerciseDTO excercise = new ExerciseDTO();
        ClassesDAO classDao = new ClassesDAO();
        LectureDAO lectureDao = new LectureDAO();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT *  FROM Excercises WHERE excercise_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, excerciseId);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while(rs.next()){
                excercise.setExcerciseID(rs.getInt("excercise_id"));
                excercise.setTitle(rs.getString("title"));
                excercise.setMedia(rs.getString("media"));
                excercise.setDescription(rs.getString("description"));
                excercise.setStatus(rs.getString("status"));
                excercise.setCreatedDate(rs.getDate("created_date"));
                excercise.setClasses(classDao.showClassById(rs.getInt("class_id")));
                excercise.setLecturer(lectureDao.searchLectureById(rs.getInt("lecturer_id")));
                }
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("LoadExercise SQL wrong: "+e.getMessage());
            e.printStackTrace();
        }
        return excercise;
    }
}