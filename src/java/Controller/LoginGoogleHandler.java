/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import utils.Constants;
import utils.DBUtils;
import utils.GoogleUtils;

/**
 *
 * @author ACER
 */
@WebServlet(name = "LoginGoogleHandler", urlPatterns = {"/LoginGoogleHandler"})
public class LoginGoogleHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginGoogleHandler</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Servlet LoginGoogleHandler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        Connection con = null;
        UserGoogleDto userData = new UserGoogleDto();
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String msg = "";
        HttpSession session = request.getSession();

        try (PrintWriter out = response.getWriter()) {
            String accessToken = GoogleUtils.getToken(code);
            UserGoogleDto user = GoogleUtils.getUserInfo(accessToken);
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginGoogleHandler</title>");
            out.println("</head>");
            out.println("<body>");

            try {
                if (user != null) {
                    con = DBUtils.getConnection();
                    String sql = "SELECT * from STUDENTs ";
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
                        msg = "Create account successful !!!";
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
                            msg = "Welcome back " + username;

                        }
                    }

                    request.setAttribute("msg", msg);
//                    request.getRequestDispatcher("Home.jsp").forward(request, response);
                    session.setAttribute("user", userData);

                    response.sendRedirect("Home.jsp");
                }
                rs.close();
                preStm.close();
                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            out.println("<h1>User Data :" + user.getEmail() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
