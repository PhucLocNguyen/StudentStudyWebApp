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
                sql = "SELECT * from STUDENTs ";
                sql += " WHERE email = ? ";

                preStm = con.prepareStatement(sql);
                preStm.setString(1, user.getEmail());
                userData.setEmail(user.getEmail());
                rs = preStm.executeQuery();
                System.out.println("Logic: " + rs);
                if (!rs.next()) {
                    String name = user.getEmail().split("@fpt")[0];
                    userData.setPicture(user.getPicture());
                    userData.setName(name);
                    userData.setPassword("");
                    sql = "INSERT INTO STUDENTs (name, image, email, password) VALUES (?, ?, ?, ?)";
                    preStm = con.prepareStatement(sql);
                    preStm.setString(1, userData.getName());
                    preStm.setString(2, userData.getPicture());
                    preStm.setString(3, userData.getEmail());
                    preStm.setString(4, userData.getPassword());
                    preStm.executeUpdate();
                } else {
                    sql = "SELECT * FROM STUDENTs where email = ? ";
                    preStm = con.prepareStatement(sql);
                    preStm.setString(1, userData.getEmail());
                    rs = preStm.executeQuery();
                    System.out.println("Nhay vo else");
                    if (rs != null) {
                        rs.next();
                        String username = rs.getString("name");
                        String password = rs.getString("password");
                        userData.setPassword(password);
                        userData.setName(username);

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
