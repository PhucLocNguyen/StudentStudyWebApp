/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class ExcerciseDAO {
    public ExcerciseDTO showExcerciseByID(int excercise_id) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        ExcerciseDTO excercise = null;
        LectureDTO lecturer = null;
        ClassesDTO classes = null;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT excercise_id, title, media, description, status, created_date, class_id, lecturer_id From Excercises WHERE excercise_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, excercise_id);
            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            ClassesDAO classes_DAO = new ClassesDAO();
            if (rs != null) {
                if (rs.next()) {
                    classes = classes_DAO.showClassById(rs.getInt(7));
                    lecturer = lecturer_DAO.searchLectureById(rs.getInt(8));
                   excercise = new ExcerciseDTO(excercise_id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getDate(6), classes, lecturer);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR in show excerices By ID: " + e.getMessage());
            e.getStackTrace();
        }
        return excercise;
    }
}
