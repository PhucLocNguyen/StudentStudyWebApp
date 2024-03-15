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

    public boolean addExercise(String title, String description, String media, String status, int classID, int lecturerID) {
        Connection con = null;
        PreparedStatement preStm = null;
        boolean check = false;
        String sql;
        try {
            con = DBUtils.getConnection();
            sql = "INSERT INTO [dbo].[Exercises]\n"
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


    public ExerciseDTO loadExercise(int exerciseId) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        ExerciseDTO exercise = new ExerciseDTO();
        ClassesDAO classDao = new ClassesDAO();
        LectureDAO lectureDao = new LectureDAO();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT *  FROM Exercises WHERE exercise_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, exerciseId);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    exercise.setExerciseID(rs.getInt("exercise_id"));
                    exercise.setTitle(rs.getString("title"));
                    exercise.setMedia(rs.getString("media"));
                    exercise.setDescription(rs.getString("description"));
                    exercise.setStatus(rs.getString("status"));
                    exercise.setCreatedDate(rs.getDate("created_date"));
                    exercise.setClasses(classDao.showClassById(rs.getInt("class_id")));
                    exercise.setLecturer(lectureDao.searchLectureById(rs.getInt("lecturer_id")));
                }
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("LoadExercise SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return exercise;
    }

    public int totalExercise(int classID, String role) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        int countRow = 0;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT COUNT(*) FROM Exercises WHERE class_id = ? ";
            if (role.equals("student")) {
                sql += "AND status = 'Active' ";
            }
            preStm = con.prepareCall(sql);
            preStm.setInt(1, classID);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    countRow = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("countRow SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return countRow;
    }

    public List<ExerciseDTO> getExerciseOnPaging(int classID, String role, int page) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        List<ExerciseDTO> listExercise = new ArrayList<>();
        ClassesDAO classDao = new ClassesDAO();
        LectureDAO lectureDao = new LectureDAO();
        try {
            con = DBUtils.getConnection();
            if(role.equals("student")){
                sql = "SELECT * From\n"
                    + "(SELECT ROW_NUMBER() OVER(ORDER BY start_date) AS number,* From Exercises WHERE class_id = ? AND status = 'Active'  ) as paging\n"
                    + "WHERE number BETWEEN ?*3-2 and ?*3 ";
            }else {
                sql = "SELECT * From\n"
                    + "(SELECT ROW_NUMBER() OVER(ORDER BY start_date) AS number,* From Exercises WHERE class_id = ? ) as paging\n"
                    + "WHERE number BETWEEN ?*3-2 and ?*3 ";
            }
            
            preStm = con.prepareCall(sql);
            preStm.setInt(1, classID);
            preStm.setInt(2, page);
            preStm.setInt(3, page);
            ResultSet rs = preStm.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    int id = rs.getInt("exercise_id");
                    String title = rs.getString("title");
                    String media = rs.getString("media");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Date created_date = rs.getDate("created_date");
                    int lectureID = rs.getInt("lecturer_id");
                    ExerciseDTO exercise = new ExerciseDTO(id, title, media, description, status, created_date, classDao.showClassById(classID), lectureDao.searchLectureById(lectureID));
                    listExercise.add(exercise);
                }
            }
        } catch (SQLException e) {
            System.out.println("getExerciseOnPaging SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return listExercise;
    }

}
