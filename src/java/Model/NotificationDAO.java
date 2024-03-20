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
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class NotificationDAO {

    public boolean createNotificationInClassActivity(String message, int exerciseID) {
        boolean output = false;
        Connection con = null;
        PreparedStatement preStm = null;
        String sql;
        try {
            con = DBUtils.getConnection();
            sql = "INSERT INTO [dbo].[Notifications] ([message],[exercise_id]) VALUES (?,?)";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, message);
            preStm.setInt(2, exerciseID);
            if (preStm.executeUpdate() > 0) {
                output = true;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR create Notification In Class Activity in NotificationDAO: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }

    public NotificationDTO loadNotificationByID(int notificationID) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        NotificationDTO notification = null;
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        try {
            con = DBUtils.getConnection();
            sql = "SELECT notification_id, message, created_date, exercise_id FROM [QuanLySinhVien].[dbo].[Notifications] WHERE notification_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, notificationID);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    notification = new NotificationDTO(notificationID, rs.getString("message"), rs.getTimestamp("created_date"), exerciseDAO.loadExercise(rs.getInt("exercise_id")));
                }
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("load notification by ID SQL wrong in NotificationDAO: " + e.getMessage());
            e.printStackTrace();
        }
        return notification;
    }
}
