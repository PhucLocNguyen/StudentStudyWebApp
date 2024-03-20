/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtils;
import Model.StudentDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class StudentDAO {

    public static StudentDTO login(StudentDTO user) {
        StudentDTO userData = new StudentDTO();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        StudentDAO studentDAO = new StudentDAO();
        try {
            if (user != null) {
                con = DBUtils.getConnection();
                String sql;
                sql = "SELECT * from Students ";
                sql += " WHERE email = ? ";

                preStm = con.prepareStatement(sql);
                preStm.setString(1, user.getEmail());
                userData.setEmail(user.getEmail());
                rs = preStm.executeQuery();
                if (!rs.next()) {
                    String name = user.getEmail().split("@fpt")[0];
                    userData.setThumbnail(user.getThumbnail());
                    userData.setName(name);
                    userData.setPassword("");
                    sql = "INSERT INTO STUDENTs (name, thumbnail, email, password) VALUES (?, ?, ?, ?)";
                    preStm = con.prepareStatement(sql);
                    preStm.setString(1, userData.getName());
                    preStm.setString(2, userData.getThumbnail());
                    preStm.setString(3, userData.getEmail());
                    preStm.setString(4, userData.getPassword());
                    preStm.executeUpdate();
                    int id = studentDAO.showIdStudentByEmail(userData.getEmail());
                    userData.setId(id);
                } else {
                    sql = "SELECT student_id, name, email, thumbnail FROM STUDENTs where email = ? ";
                    preStm = con.prepareStatement(sql);
                    preStm.setString(1, userData.getEmail());
                    rs = preStm.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            userData = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                        }
                    }
                }
            }
            rs.close();
            preStm.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("SQL USER ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return userData;

    }

    public int showIdStudentByEmail(String email) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        int id = -1;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT student_id FROM Students Where email = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    id = rs.getInt("student_id");
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Show Student By Email: " + e.getMessage());
            e.getStackTrace();
        }
        return id;
    }

    public StudentDTO showStudentById(int student_id) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        StudentDTO student = null;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT student_id, name, email ,thumbnail FROM Students Where student_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, student_id);
            rs = preStm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    student = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Show Student detail By ID: " + e.getMessage());
            e.getStackTrace();
        }
        return student;
    }

    public void update(StudentDTO student) {
        try {
            Connection con = DBUtils.getConnection();

            String sql = " UPDATE Students SET name = ? , thumbnail = ? WHERE student_id = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getThumbnail());
            stmt.setInt(3, student.getId());

            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Update: " + e.getMessage());
        }
    }
}
