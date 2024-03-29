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
            sql = "INSERT INTO Do (exercise_id, student_id, solution) VALUES (?,?,?)";
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
            sql = "SELECT * FROM Do WHERE exercise_id = ? ORDER BY created_date DESC";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, exerciseID);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int exerciseId = rs.getInt("exercise_id");
                    int student_id = rs.getInt("student_id");
                    String solution = rs.getString("solution");
                    float score = rs.getFloat("score");
                    boolean isGrade = rs.getBoolean("is_grade");
                    Date createDate = rs.getDate("created_date");
                    DoDTO Do = new DoDTO(studentDAO.showStudentById(student_id), exerciseDAO.loadExercise(exerciseId), solution, score, isGrade, createDate);
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
            sql = "SELECT * FROM Do WHERE student_id = ? AND exercise_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, studentId);
            preStm.setInt(2, exerciseId);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Do.setExercise(exerciseDAO.loadExercise(rs.getInt("exercise_id")));
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
            sql = "UPDATE Do SET solution = ? WHERE exercise_id = ? and student_id = ? ";
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
            sql = "DELETE FROM Do WHERE exercise_id = ? and student_id = ? ";
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

    public boolean addScoreToDo(float score, int exercise_id, int student_id) {
        boolean output = false;
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "UPDATE Do SET score = ?, is_grade= 1 WHERE exercise_id = ? AND student_id = ?";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setFloat(1, score);
            preStm.setInt(2, exercise_id);
            preStm.setInt(3, student_id);
            preStm.executeUpdate();
            output = true;
        } catch (SQLException e) {
            System.err.println("Error in addScoreToDo: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }

    public List<StudentDTO> showStudentNotAnswer(int exercise_id, int class_id) {
        List<StudentDTO> listStudent = new ArrayList<>();
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        StudentDTO student = null;
        String sql = "SELECT * From Enroll e WHERE e.student_id NOT IN(SELECT student_id FROM Do Where exercise_id = ?) AND e.class_id = ? ";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, exercise_id);
            preStm.setInt(2, class_id);
            rs = preStm.executeQuery();
            StudentDAO studentDAO = new StudentDAO();
            ExerciseDAO exerciseDAO = new ExerciseDAO();
            if (rs != null) {
                while (rs.next()) {
                    student = studentDAO.showStudentById(rs.getInt(1));
                    listStudent.add(student);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Show student not answer By ID Exercise: " + e.getMessage());
            e.getStackTrace();
        }
        return listStudent;
    }

    public boolean updateScoreToDo(float score, int exercise_id, int student_id) {
        boolean output = false;
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "  UPDATE Do SET score = ? WHERE exercise_id = ? AND student_id = ? ";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setFloat(1, score);
            preStm.setInt(2, exercise_id);
            preStm.setInt(3, student_id);
            preStm.executeUpdate();
            output = true;
        } catch (SQLException e) {
            System.err.println("Error in updateScoreToDo: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }
    
    public List<DoDTO> listScore(int student_id, int class_id,String role) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        List<DoDTO> listScore = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT e.exercise_id,d.score,d.solution,d.is_grade FROM Exercises e \n"
                    + "LEFT JOIN (SELECT exercise_id, student_id, score, solution,is_grade FROM Do WHERE student_id = ? )d\n"
                    + "ON e.exercise_id = d.exercise_id WHERE e.class_id = ? AND e.status IN('active') ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, student_id);
            preStm.setInt(2, class_id);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    DoDTO Do = new DoDTO();
                    Do.setExercise(new ExerciseDAO().loadExercise(rs.getInt("exercise_id")));
                    Do.setStudent(new StudentDAO().showStudentById(student_id));
                    
                    if (rs.getString("solution") == null) {
                        Do.setSolution("");
                        Do.setScore(0);
                    } else {
                        Do.setSolution(rs.getString("solution"));
                        Do.setScore(rs.getFloat("score"));
                        Do.setIsGrade(rs.getBoolean("is_grade"));
                    }
                    listScore.add(Do);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in listScore: " + e.getMessage());
            e.getStackTrace();
        }
        return listScore;
    }
}
