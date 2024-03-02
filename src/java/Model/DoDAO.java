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
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class DoDAO {

    public DoDTO showStudentAnswerById(int excercise_id, int student_id) {
        DoDTO studentDo = null;
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String sql = "SELECT excercise_id, student_id, solution, score, is_grade, created_date FROM Do WHERE excercise_id = ? AND student_id = ? ";
        ExcerciseDTO excercise = null;
        StudentDTO student = null;

        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, excercise_id);
            preStm.setInt(2, student_id);
            rs = preStm.executeQuery();
            ExcerciseDAO excerciseDAO = new ExcerciseDAO();
            StudentDAO studentDAO = new StudentDAO();
            if (rs != null) {
                if (rs.next()) {
                    excercise = excerciseDAO.showExcerciseByID(excercise_id);
                    student = studentDAO.showStudentById(student_id);
                    studentDo = new DoDTO(excercise, student, rs.getString(3), rs.getFloat(4), rs.getBoolean(5), rs.getDate(6));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in show student answer by ID : " + e.getMessage());
            e.printStackTrace();
        }
        return studentDo;
    }

    public ArrayList<DoDTO> showAllAnswer(int excercise_id) {
        ArrayList<DoDTO> listAnswer = new ArrayList<>();
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String sql = "SELECT excercise_id, student_id, solution, score, is_grade, created_date FROM Do WHERE excercise_id = ? ";
        ExcerciseDTO excercise = null;
        StudentDTO student = null;

        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, excercise_id);
            rs = preStm.executeQuery();
            ExcerciseDAO excerciseDAO = new ExcerciseDAO();
            StudentDAO studentDAO = new StudentDAO();
            if (rs != null) {
                while (rs.next()) {
                    excercise = excerciseDAO.showExcerciseByID(excercise_id);
                    student = studentDAO.showStudentById(rs.getInt(2));
                    listAnswer.add(new DoDTO(excercise, student, rs.getString(3), rs.getFloat(4), rs.getBoolean(5), rs.getDate(6)));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in show all answer: " + e.getMessage());
            e.printStackTrace();
        }
        return listAnswer;
    }

    public ArrayList<StudentDTO> showStudentNotAnswer(int excercise_id) {
        ArrayList<StudentDTO> listStudent = new ArrayList<>();
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        StudentDTO student = null;
        String sql = "SELECT student_id From Enroll e WHERE e.student_id NOT IN(SELECT student_id FROM Do Where excercise_id = ? ) AND e.class_id in (SELECT class_id FROM Excercises ex WHERE e.class_id = ex.class_id ) ";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, excercise_id);
            rs = preStm.executeQuery();
            StudentDAO studentDAO = new StudentDAO();
            ExcerciseDAO excerciseDAO = new ExcerciseDAO();
            if (rs != null) {
                while (rs.next()) {
                    student = studentDAO.showStudentById(rs.getInt(1));
                    listStudent.add(student);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Show student not answer By ID Excercise: " + e.getMessage());
            e.getStackTrace();
        }
        return listStudent;
    }

    public ArrayList<DoDTO> ShowStudentAnswerSubmitted(int excercise_id, int class_id) {
        ArrayList<DoDTO> listDo = new ArrayList<>();
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        DoDTO doStudent = null;
        String sql = "SELECT * From Enroll e WHERE e.student_id IN(SELECT student_id FROM Do Where excercise_id = ?) AND e.class_id = ?  ";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, excercise_id);
            preStm.setInt(2, class_id);
            rs = preStm.executeQuery();
            DoDAO doDAO = new DoDAO();

            if (rs != null) {
                while (rs.next()) {
                    doStudent = doDAO.showStudentAnswerById(excercise_id, rs.getInt(1));
                    listDo.add(doStudent);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Show student has answer By ID Excercise: " + e.getMessage());
            e.getStackTrace();
        }
        return listDo;
    }

    public boolean addScoreToDo(float score, int excercise_id, int student_id) {
        boolean output = false;
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "UPDATE Do SET score = ?, is_grade= 1 WHERE excercise_id = ? AND student_id = ?";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setFloat(1, score);
            preStm.setInt(2, excercise_id);
            preStm.setInt(3, student_id);
            preStm.executeUpdate();
            output = true;
        } catch (SQLException e) {
            System.err.println("Error in addScoreToDo: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }

    public boolean updateScoreToDo(float score, int excercise_id, int student_id) {
        boolean output = false;
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "  UPDATE Do SET score = ? WHERE excercise_id = ? AND student_id = ? ";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setFloat(1, score);
            preStm.setInt(2, excercise_id);
            preStm.setInt(3, student_id);
            preStm.executeUpdate();
            output = true;
        } catch (SQLException e) {
            System.err.println("Error in updateScoreToDo: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }
}
