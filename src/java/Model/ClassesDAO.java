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
            sql = "SELECT TOP 6 c.class_id, c.name, c.thumbnail, c.password, c.description, c.lecturer_id FROM Classes c ORDER BY c.created_date DESC;";
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
            sql = "SELECT c.class_id,c.name,c.thumbnail,c.password,c.description,c.lecturer_id FROM Classes c\n"
                    + "JOIN Enroll e on c.class_id = e.class_id WHERE e.student_id = ? ";
            switch (sortByCondition) {
                case "1":
                    sql += "ORDER BY c.name";
                    break;
                case "2":
                    sql += "ORDER BY c.name DESC";
                    break;
                case "3":
                    sql += "ORDER BY e.enroll_date DESC";
                    break;
                case "4":
                    sql += "ORDER BY e.enroll_date";
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

    public int totalClassFound(String keyWord, int id, int SQLcondition, String role) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        String condition = "";
        switch (SQLcondition) {
            case 1:
                condition = " SELECT 'Enroll' AS source, COUNT(*) AS number\n"
                        + "        FROM Classes c\n"
                        + "        JOIN Enroll e ON c.class_id = e.class_id\n"
                        + "        WHERE e.student_id = ? ";
                break;
            case 2:
                condition = "SELECT 'ClassesWithLecturer' AS source, COUNT(*) AS number\n"
                        + "        FROM Classes\n"
                        + "        WHERE lecturer_id = ?";
                break;
            case 3:
                condition = "SELECT 'ClassesWithoutEnroll' AS source, COUNT(*) AS number\n"
                        + "        FROM Classes c\n"
                        + "        JOIN Lecturers l ON c.lecturer_id = l.lecturer_id\n"
                        + "        WHERE c.name LIKE ? \n";
                if (role.equals("student")) {
                    condition += " AND c.class_id NOT IN (SELECT class_id FROM Enroll)";
                }
                break;
        }
        int total = 0;
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT number\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        source,\n"
                    + "        number,\n"
                    + "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS row_num\n"
                    + "    FROM (" + condition + " ) AS subquery\n"
                    + ") AS numbered_rows";

            preStm = con.prepareStatement(sql);
            switch (SQLcondition) {
                case 1:
                case 2:
                    preStm.setInt(1, id);
                    break;
                case 3:
                    preStm.setString(1, "%" + keyWord + "%");
                    break;
            }

            rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    total = rs.getInt("number");
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR CLasses: " + e.getMessage());
            e.getStackTrace();
        }
        return total;
    }

    public List<ClassesDTO> showClassWithKeyWord(String keyWord, String role, String sortByCondition, int page, int id) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        String condition = "";
        LectureDTO lecture = null;
        List<ClassesDTO> list = new ArrayList<>();
        switch (sortByCondition) {
            case "1":
                condition = "c.name ";
                break;
            case "2":
                condition = "c.name DESC";
                break;
            case "3":
                condition = "c.created_date DESC";
                break;
            case "4":
                condition = "c.created_date ASC";
                break;
        }

        try {
            con = DBUtils.getConnection();
            if (role.equals("student")) {
                sql = "SELECT * FROM (SELECT ROW_NUMBER() Over( ORDER BY " + condition + " ) as number, c.class_id,c.name,c.thumbnail,c.password,c.description,c.lecturer_id \n"
                        + "FROM Classes c JOIN Lecturers l ON c.lecturer_id = l.lecturer_id WHERE c.name LIKE ? AND c.class_id NOT IN (SELECT class_id FROM Enroll e WHERE e.student_id = ? )) as paging\n"
                        + "WHERE number BETWEEN ? and ?";

                preStm = con.prepareStatement(sql);
                preStm.setString(1, "%" + keyWord + "%");
                preStm.setInt(2, id);
                preStm.setInt(3, page * 6 - 5);
                preStm.setInt(4, page * 6);
            } else {
                sql = "SELECT * FROM (SELECT ROW_NUMBER() Over( ORDER BY " + condition + " ) as number, c.class_id,c.name,c.thumbnail,c.password,c.description,c.lecturer_id \n"
                        + "FROM Classes c JOIN Lecturers l ON c.lecturer_id = l.lecturer_id WHERE c.name LIKE ? ) as paging\n"
                        + "WHERE number BETWEEN ? and ? ";

                preStm = con.prepareStatement(sql);
                preStm.setString(1, "%" + keyWord + "%");
                preStm.setInt(2, page * 6 - 5);
                preStm.setInt(3, page * 6);
            }

            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            if (rs != null) {
                while (rs.next()) {
                    lecture = lecturer_DAO.searchLectureById(rs.getInt(7));
                    list.add(new ClassesDTO(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), lecture));
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

    public List<ClassesDTO> showClassOwnedByLectureIDPaging(int lecutre_id, String sortByCondition, int page) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        LectureDTO lecture = null;
        String condition = "";
        switch (sortByCondition) {
            case "1":
                condition = "ORDER BY name";
                break;
            case "2":
                condition = "ORDER BY name DESC";
                break;
            case "3":
                condition = "ORDER BY created_date DESC";
                break;
            case "4":
                condition = "ORDER BY created_date";
                break;
        }
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER()  OVER(" + condition + ") as number, class_id, name, thumbnail, password, description, lecturer_id FROM Classes WHERE lecturer_id = ? ) as paing\n"
                    + "WHERE  number between ? and ? ";

            preStm = con.prepareStatement(sql);
            preStm.setInt(1, lecutre_id);
            preStm.setInt(2, page * 6 - 5);
            preStm.setInt(3, page * 6);
            rs = preStm.executeQuery();
            LectureDAO lecturer_DAO = new LectureDAO();
            if (rs != null) {
                while (rs.next()) {
                    lecture = lecturer_DAO.searchLectureById(rs.getInt(7));
                    list.add(new ClassesDTO(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), lecture));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("SQL ERROR show CLasses owned by lecture ID: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }

    public List<ClassesDTO> showClassOwnedByStudentIDPaging(int stduent_id, String sortByCondition, int page) {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        String condition = "";
        switch (sortByCondition) {
            case "1":
                condition = "ORDER BY c.name";
                break;
            case "2":
                condition = "ORDER BY c.name DESC";
                break;
            case "3":
                condition = "ORDER BY e.enroll_date DESC";
                break;
            case "4":
                condition = "ORDER BY e.enroll_date";
                break;
        }
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT * FROM\n"
                    + "(SELECT ROW_NUMBER()  OVER(" + condition + ") as number ,c.class_id,c.name,c.thumbnail,c.password,c.description,c.lecturer_id FROM Classes c \n"
                    + "JOIN Enroll e on c.class_id = e.class_id WHERE e.student_id = ? ) AS paging WHERE number between ? and ?";

            preStm = con.prepareStatement(sql);
            preStm.setInt(1, stduent_id);
            preStm.setInt(2, page * 6 - 5);
            preStm.setInt(3, page * 6);
            rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new ClassesDTO(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new LectureDAO().searchLectureById(rs.getInt(7))));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("SQL ERROR show CLasses by Student ID: " + e.getMessage());
            e.getStackTrace();
        }
        return list;
    }
}
