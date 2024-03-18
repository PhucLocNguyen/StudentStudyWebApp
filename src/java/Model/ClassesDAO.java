/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBUtils;
import Model.ClassesDTO;

/**
 *
 * @author ACER
 */
public class ClassesDAO {
    
    public boolean addClass(String name, String image, String password, String description, int lecturer_id) {
        Connection con = null;
        PreparedStatement preStm = null;
        boolean status = false;
        String sql;
        try {
            con = DBUtils.getConnection();
            sql = "INSERT INTO Classes (name, thumbnail ,password, description, lecturer_id) VALUES (?,?,?,?,?)";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, name);
            preStm.setString(2, image);
            preStm.setString(3, password);
            preStm.setString(4, description);
            preStm.setInt(5, lecturer_id);
            if (preStm.executeUpdate() == 1) {
                status = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR Insert CLasses: " + e.getMessage());
            e.getStackTrace();
        }
        
        return status;
        
    }
    
    public boolean deleteClassByID(int class_id) {
        Connection con = null;
        PreparedStatement preStm = null;
        boolean status = false;
        String sql;
        try {
            con = DBUtils.getConnection();
            sql = "DELETE FROM Classes WHERE class_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, class_id);
            if (preStm.executeUpdate() == 1) {
                status = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR Delete CLasse by ID: " + e.getMessage());
            e.getStackTrace();
        }
        
        return status;
    }
    
    public boolean checkingClassesPassword(String password, int class_id) {
        boolean status = false;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        try {
            con = DBUtils.getConnection();
            sql = "SELECT * FROM Classes Where class_id = ? AND password = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, class_id);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            if (rs != null) {
                if (rs.next()) {
                    status = true;
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR Checking CLass Password By ID: " + e.getMessage());
            e.getStackTrace();
        }
        return status;
    }
    
    public ClassesDTO showClassById(int class_id) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        LectureDTO lecture = null;
        ClassesDTO classes = null;
        try {
            con = DBUtils.getConnection();
            sql = "SELECT class_id, name, thumbnail, password, description, lecturer_id FROM Classes Where class_id = ? ";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, class_id);
            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            
            if (rs != null) {
                if (rs.next()) {
                    lecture = lecturer_DAO.searchLectureById(rs.getInt(6));
                    classes = new ClassesDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), lecture);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("SQL ERROR Show CLass By ID: " + e.getMessage());
            e.getStackTrace();
        }
        return classes;
    }
    
    public List<ClassesDTO> showClass() {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        LectureDTO lecture = null;
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT c.class_id, c.name, c.thumbnail, c.password, c.description, c.lecturer_id FROM Classes c ORDER BY c.created_date DESC;";
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            if (rs != null) {
                while (rs.next()) {
                    lecture = lecturer_DAO.searchLectureById(rs.getInt(6));
                    list.add(new ClassesDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), lecture));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR CLasses: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }
    
    public List<ClassesDTO> showClassOwnedByStudentID(int stduent_id, String sortByCondition) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        LectureDTO lecture = null;
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT c.class_id,c.name,c.thumbnail,c.password,c.description,c.lecturer_id FROM Classes c \n"
                    + "JOIN Enroll e on c.class_id = e.class_id WHERE e.student_id = ? ";
            switch (sortByCondition) {
                case "1":
                    sql += "ORDER BY c.name";
                    break;
                case "2":
                    sql += "ORDER BY c.name DESC";
                    break;
                case "3":
                    sql += "ORDER BY c.created_date DESC";
                    break;
                case "4":
                    sql += "ORDER BY c.created_date";
                    break;
            }
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, stduent_id);
            rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new ClassesDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), new LectureDAO().searchLectureById(rs.getInt(6))));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("SQL ERROR show CLasses by Student ID: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }
    
    public List<ClassesDTO> showClassOwnedByLectureID(int lecutre_id, String sortByCondition) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        LectureDTO lecture = null;
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT class_id, name, thumbnail, password, description, lecturer_id FROM Classes WHERE lecturer_id = ? ";
            switch (sortByCondition) {
                case "1":
                    sql += "ORDER BY name";
                    break;
                case "2":
                    sql += "ORDER BY name DESC";
                    break;
                case "3":
                    sql += "ORDER BY created_date DESC";
                    break;
                case "4":
                    sql += "ORDER BY created_date";
                    break;
            }
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, lecutre_id);
            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            if (rs != null) {
                while (rs.next()) {
                    lecture = lecturer_DAO.searchLectureById(rs.getInt(6));
                    list.add(new ClassesDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), lecture));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("SQL ERROR show CLasses owned by lecture ID: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }
    
    public List<ClassesDTO> showClassWithKeyWord(String keyWord, String role, String sortByCondition) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        String condition = "";
        LectureDTO lecture = null;
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT c.class_id,c.name,c.thumbnail,c.password,c.description,c.lecturer_id FROM Classes c JOIN Lecturers l ON c.lecturer_id = l.lecturer_id WHERE c.name like ? ";
            condition = "and c.class_id not in (SELECT class_id FROM Enroll) ";
            if (role.equals("student")) {
                sql += condition;
            }
            switch (sortByCondition) {
                case "1":
                    sql += "ORDER BY c.name";
                    break;
                case "2":
                    sql += "ORDER BY c.name DESC";
                    break;
                case "3":
                    sql += "ORDER BY c.created_date DESC";
                    break;
                case "4":
                    sql += "ORDER BY c.created_date";
                    break;
            }
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + keyWord + "%");
            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            if (rs != null) {
                while (rs.next()) {
                    lecture = lecturer_DAO.searchLectureById(rs.getInt(6));
                    list.add(new ClassesDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), lecture));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR CLasses: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }
    
    public boolean isLectureInClass(int lecturer_id, int class_id) {
        boolean output = false;
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Classes WHERE lecturer_id = ? AND class_id = ? ";
        try {
            con = DBUtils.getConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, lecturer_id);
            preStm.setInt(2, class_id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                output = true;
            }
        } catch (SQLException e) {
            System.err.println("SQL isLectureInClass Error: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }
}
