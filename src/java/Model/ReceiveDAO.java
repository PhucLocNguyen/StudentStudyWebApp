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
 * @author ACER
 */
public class ReceiveDAO {

    public List<ReceiveDTO> loadReceiveByStudentId(int studentID) {
        Connection con = null;
        PreparedStatement preStm = null;
        String sql = "";
        List<ReceiveDTO> listReceive = new ArrayList<>();
        NotificationDAO notificationDAO = new NotificationDAO();
        StudentDAO studentDAO = new StudentDAO();

        try {
            con = DBUtils.getConnection();
            sql = "SELECT notification_id,student_id,is_read FROM [QuanLySinhVien].[dbo].[Receive] WHERE student_id = ? ";
            preStm = con.prepareCall(sql);
            preStm.setInt(1, studentID);
            ResultSet rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    StudentDTO student = studentDAO.showStudentById(rs.getInt("student_id"));
                    ReceiveDTO receive = new ReceiveDTO(notificationDAO.loadNotificationByID(rs.getInt("notification_id")), student, rs.getBoolean("is_read"));
                    listReceive.add(receive);
                }
            }
        } catch (SQLException e) {
            System.out.println("Load receive by student ID in ReceiveDAO SQL wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return listReceive;
    }

    public boolean seenTheNotification(int notificationID, int studentID) {
        boolean output = false;
        Connection con = null;
        PreparedStatement preStm = null;
        String sql;
        NotificationDAO notificationDAO = new NotificationDAO();
        notificationDAO.loadNotificationByID(notificationID);
        try {
            con = DBUtils.getConnection();

            sql = "UPDATE [dbo].[Receive] SET is_read = 1 WHERE student_id = ? AND notification_id = ?";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, studentID);
            preStm.setInt(2, notificationID);
            if (preStm.executeUpdate() > 0) {
                output = true;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SQL ERROR create Receive For Student In Class in ReceiveDAO: " + e.getMessage());
            e.getStackTrace();
        }
        return output;
    }
}
