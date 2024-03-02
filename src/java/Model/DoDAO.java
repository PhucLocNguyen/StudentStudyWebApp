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

 * @author User
 */
public class DoDAO {

    public boolean addAnswer(int exerciseID, int studentID, String answer) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        try {
            con = DBUtils.getConnection();
            sql = "INSERT INTO Do (excercise_id, student_id, solution) VALUES (?,?,?)";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, exerciseID);
            preStm.setInt(2, studentID);
            preStm.setString(3, answer);
            preStm.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("addAnswer SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<DoDTO> list(int exerciseID) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        List<DoDTO> listDo = new ArrayList<>();
        StudentDAO studentDAO = new StudentDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT * FROM Do WHERE excercise_id = ? ORDER BY created_date DESC";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, exerciseID);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int exerciseId = rs.getInt("excercise_id");
                    int student_id = rs.getInt("student_id");
                    String solution = rs.getString("solution");
                    float score = rs.getFloat("score");
                    boolean isGrade = rs.getBoolean("is_grade");
                    Date createDate = rs.getDate("created_date");
                    DoDTO Do = new DoDTO(studentDAO.showStudentById(student_id), exerciseDAO.loadExcercise(exerciseId), solution, score, isGrade, createDate);
                    listDo.add(Do);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("list SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return listDo;
    }

    public DoDTO loadAnswer(int studentId, int exerciseId) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        DoDTO Do = new DoDTO();
        StudentDAO studentDAO = new StudentDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT * FROM Do WHERE student_id = ? AND excercise_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, studentId);
            preStm.setInt(2, exerciseId);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Do.setExercise(exerciseDAO.loadExcercise(rs.getInt("excercise_id")));
                    Do.setStudent(studentDAO.showStudentById(rs.getInt("student_id")));
                    Do.setSolution(rs.getString("solution"));
                    Do.setScore(rs.getFloat("score"));
                    Do.setIsGrade(rs.getBoolean("is_grade"));
                    Do.setCreateDate(rs.getDate("created_date"));
                }
            }
            con.close();
        } catch (SQLException e) {

            System.out.println("checkAnswer SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return Do;
    }

    public boolean update(int exerciseId, int studentId, String updateSolution) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        try {
            con = DBUtils.getConnection();
            sql = "UPDATE Do SET solution = ? WHERE excercise_id = ? and student_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setString(1, updateSolution);
            preStm.setInt(2, exerciseId);
            preStm.setInt(3, studentId);
            preStm.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Update SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int exerciseId, int studentId) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        try {
            con = DBUtils.getConnection();
            sql = "DELETE FROM Do WHERE excercise_id = ? and student_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, exerciseId);
            preStm.setInt(2, studentId);
            preStm.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Update SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
