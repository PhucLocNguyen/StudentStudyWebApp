/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.NotificationDAO;
import Model.NotificationDTO;
import Model.ReceiveDAO;
import Model.ReceiveDTO;
import Model.StudentDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
@WebServlet(name = "NotificationController", urlPatterns = {"/notificationController"})
public class NotificationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Gson gson = new Gson();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        ReceiveDAO receiveDAO = new ReceiveDAO();
        String role = "";
        StudentDTO student = null;
        PrintWriter out = response.getWriter();
        if (session != null) {
            student = (StudentDTO) session.getAttribute("user");
            role = (String) session.getAttribute("role");
        }
        if (student != null && role.equals("student")) {
            if (action != null) {
                if (action.equals("loadByID")) {

                    List<ReceiveDTO> notificationList = receiveDAO.loadReceiveByStudentId(student.getId());
                    for (ReceiveDTO notification : notificationList) {
                        notification.setLink("insideClass?class_id=" + notification.getNotification().getExercise().getClasses().getId());
                    }
                    String jsonPacket = gson.toJson(notificationList);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(jsonPacket);
                    out.flush();
                } else if (action.equals("seenReceive")) {
                    int notificationID = Integer.parseInt(request.getParameter("notificationID"));
                    receiveDAO.seenTheNotification(notificationID, student.getId());
                } else if (action.equals("delete")) {
                    int notificationID = Integer.parseInt(request.getParameter("notificationID"));
                    receiveDAO.deleteTheNotification(notificationID, student.getId());
                }
            }
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
        processRequest(request, response);
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
