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
            sql = "INSERT INTO Classes (name, image ,password, description, lecturer_id) VALUES (?,?,?,?,?)";
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

    public List<ClassesDTO> showClass() {
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = "";
        List<ClassesDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT * FROM Classes c LEFT JOIN Lecturers l ON c.lecturer_id = l.lecturer_id;";
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new ClassesDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
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
