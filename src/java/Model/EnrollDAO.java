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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
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


    public boolean isEnrolledClass(int student_id, int class_id) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        boolean output = false;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT enroll_date FROM Enroll WHERE student_id = ? AND class_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, student_id);
            preStm.setInt(2, class_id);
            rs = preStm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    output = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR in Checking Enrolled Class: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }

    public Date getClassEnrollDate(int student_id, int class_id) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        Date output = null;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT enroll_date FROM Enroll WHERE student_id = ? AND class_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, student_id);
            preStm.setInt(2, class_id);
            rs = preStm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    output = rs.getDate(1);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR in Checking Enrolled Class: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }

    public boolean unenrollClass(int studentID, int class_id) {
        PreparedStatement preStm = null;
        Connection con = null;
        String sql = "";
        boolean output = false;
        try {
            con = DBUtils.getConnection();
            sql = "DELETE FROM Enroll WHERE student_id = ? AND class_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, studentID);
            preStm.setInt(2, class_id);
            if (preStm.executeUpdate() == 1) {
                output = true;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR in unEnrolled Class: " + e.getMessage());
            e.getStackTrace();
        }
        return output;

    }

    public List<EnrollDTO> showEnrollStudent(int class_id) {
        List<EnrollDTO> enrollList = new ArrayList<>();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        StudentDTO student = null;
        ClassesDTO classes = null;
        StudentDAO studentDAO = new StudentDAO();
        ClassesDAO classesDAO = new ClassesDAO();
        String sql = "";
        try {
            con = DBUtils.getConnection();
            sql = "SELECT student_id, enroll_date FROM Enroll WHERE class_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, class_id);
            rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    student = studentDAO.showStudentById(rs.getInt("student_id"));
                    classes = classesDAO.showClassById(class_id);
                    enrollList.add(new EnrollDTO(student, classes, rs.getDate("enroll_date")));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR in show enrolled student in Class: " + e.getMessage());
            e.getStackTrace();
        }
        return enrollList;
    }
    
    
      public List<EnrollDTO> enrolledClasses(int student_id) {
        List<EnrollDTO> list = new ArrayList<>();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        StudentDTO student = null;
        ClassesDTO classes = null;
        int class_id = -1;
        Date enroll_date = null;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT class_id, enroll_date FROM Enroll WHERE student_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, student_id);
            rs = preStm.executeQuery();
            ClassesDAO class_DAO = new ClassesDAO();
            StudentDAO student_DAO = new StudentDAO();
            if (rs != null) {
                while (rs.next()) {
                    class_id = rs.getInt(1);
                    enroll_date = rs.getDate(2);
                    classes = class_DAO.showClassById(class_id);
                    student = student_DAO.showStudentById(student_id);
                    list.add(new EnrollDTO(student, classes, enroll_date));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR enrolled list CLasses: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }
}
