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
public class LectureDAO {

    public LectureDTO login(String email, String password) {
        Connection con = DBUtils.getConnection();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        LectureDTO user = null;
        String sql = "SELECT * FROM Lecturers WHERE email = ? AND password = ? ";
        try {
            preStm = con.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    user = new LectureDTO();
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));

                    user.setThumbnail(rs.getString(3));
                    user.setEmail(rs.getString(4));

                    user.setPassword(rs.getString(5));
                }
            }
            rs.close();
            preStm.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Login lecture error: " + e.getMessage());
        }
        return user;
    }

    public LectureDTO searchLectureById(int id) {
        Connection con = DBUtils.getConnection();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        LectureDTO user = new LectureDTO();
        String sql = "SELECT lecturer_id, name, thumbnail, email, password FROM Lecturers WHERE lecturer_id = ? ";
        try {
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setThumbnail(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    user.setPassword(rs.getString(5));
                }
            }
            rs.close();
            preStm.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Search lecture by ID error: " + e.getMessage());
        }
        return user;
    }

    
      public void update(LectureDTO lecturer) {
        try {
            Connection con = DBUtils.getConnection();

            String sql = " UPDATE Lecturers SET name = ?, thumbnail = ? WHERE lecturer_id = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, lecturer.getName());
            stmt.setString(2, lecturer.getThumbnail());
            stmt.setInt(3, lecturer.getId());

            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Update: " + e.getMessage());
        }
    }

}
