/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtils;
import Model.UserGoogleDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class UserGoogleDAO {

    public static UserGoogleDTO login(UserGoogleDTO user) {
        UserGoogleDTO userData = new UserGoogleDTO();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Connection con = null;
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
                } else {
                    sql = "SELECT student_id, name, email, thumbnail FROM STUDENTs where email = ? ";
                    preStm = con.prepareStatement(sql);
                    preStm.setString(1, userData.getEmail());
                    rs = preStm.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            userData = new UserGoogleDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
}
