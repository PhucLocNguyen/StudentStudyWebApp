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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author HA GIA KHANH
 */
public class ExcerciseDAO {
    public boolean addExcercise(String title, String description, String media, String status, int classID, int lecturerID, Timestamp start_time, Timestamp end_time) {
        Connection con = null;
        PreparedStatement preStm = null;
        boolean check = false;
        String sql;
        try {
            con = DBUtils.getConnection();
            sql = "INSERT INTO [dbo].[Excercises]\n"
                    + "           ([title], [media], [description], [status], [class_id], [lecturer_id], start_date, end_date)\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?)";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, title);
            preStm.setString(2, media);
            preStm.setString(3, description);
            preStm.setString(4, status);
            preStm.setInt(5, classID);
            preStm.setInt(6, lecturerID);
            preStm.setTimestamp(7, start_time);
            preStm.setTimestamp(8, end_time);
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
    
    public List<ExcerciseDTO> getAllExcercise(int classID) {
        List<ExcerciseDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement preStm = null;
        String sql;
        ExcerciseDTO exc = new ExcerciseDTO();
        ClassesDTO classes = null;
        ClassesDAO classDao = new ClassesDAO();
        LectureDTO lecturer = null;
        LectureDAO lectureDao = new LectureDAO();
        try {
//            sql = "SELECT [excercise_id]\n"
//                    + "      ,[title]\n"
//                    + "      ,[media]\n"
//                    + "      ,[description]\n"
//                    + "      ,[status]\n"
//                    + "      ,[created_date]\n"
//                    + "      ,[class_id]\n"
//                    + "      ,[lecturer_id]\n"
//                    + "  FROM [dbo].[Excercises] WHERE [class_id] = ? ";
            sql = "SELECT TOP (1000) [excercise_id]\n"
                    + "      ,[title]\n"
                    + "      ,[media]\n"
                    + "      ,[description]\n"
                    + "      ,[status]\n"
                    + "      ,[created_date]\n"
                    + "      ,[class_id]\n"
                    + "      ,[lecturer_id]\n"
                    + "      ,[end_date]\n"
                    + "      ,[start_date]\n"
                    + "  FROM [QuanLySinhVien].[dbo].[Excercises] WHERE [class_id] = ? ";
            
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, classID);
            ResultSet rs = preStm.executeQuery();
            
            if(rs != null) {
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
                    Date start_date = rs.getDate("start_date");
                    Date end_date = rs.getDate("end_date");
                    
                    exc = new ExcerciseDTO(id, title, media, description, status, created_date, classes, lecturer, start_date, end_date);
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
}
