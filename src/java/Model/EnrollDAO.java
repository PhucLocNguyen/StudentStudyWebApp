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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class EnrollDAO {

    public boolean enrollClass(int student_id, int class_id) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql;
        boolean status = false;
        try {
            con = DBUtils.getConnection();
            sql = "INSERT INTO Enroll (student_id, class_id) VALUES (?, ?) ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, student_id);
            preStm.setInt(2, class_id);
            preStm.executeUpdate();
            status = true;

        } catch (SQLException e) {
            System.out.println("SQL ERROR Enroll Class: " + e.getMessage());
            e.getStackTrace();
        }
        return status;
    }

    public List<EnrollDTO> enrolledClasses(int student_id) {
        List<EnrollDTO> list = new ArrayList<>();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        StudentDTO student = null;
        ClassesDTO classes = null;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT student_id ,class_id FROM Enroll WHERE student_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, student_id);
            rs = preStm.executeQuery();
            ClassesDAO class_DAO = new ClassesDAO();
            StudentDAO student_DAO = new StudentDAO();
            if (rs != null) {
                while (rs.next()) {
                    classes = class_DAO.showClassById(student_id);
                    student = student_DAO.showStudentById(student_id);
                    list.add(new EnrollDTO(student, classes));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR CLasses: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }
}
