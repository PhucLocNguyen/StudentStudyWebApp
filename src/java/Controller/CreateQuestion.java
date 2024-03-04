/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ExerciseDAO;
import Model.ExerciseDTO;
import Model.LectureDTO;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.FileUtils;

/**
 *
 * @author HA GIA KHANH
 */
@MultipartConfig
public class CreateQuestion extends HttpServlet {

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
            out.println("<title>Servlet CreateQuestion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateQuestion at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("logout").forward(request, response);
            return;
        }
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LectureDTO user = (LectureDTO) session.getAttribute("user");
        int lecturer_id = user.getId();
        String classID_raw = request.getParameter("classId");
        String status = request.getParameter("status");

        //media
        Part filePart = request.getPart("thumbnail");
        System.out.println(filePart.getSubmittedFileName());
        FileUtils fileUtils = new FileUtils();
        String url = fileUtils.insertImage(filePart);
//        String url = "loiquai.jpg";
        ExerciseDAO dao = new ExerciseDAO();
        try {
            int classID = Integer.parseInt(classID_raw);
            if (dao.addExcercise(title, description, url, status, classID, lecturer_id)) {
                response.sendRedirect("insideClass?class_id=" + classID);
            } else {
                request.setAttribute("error", "Fail roi");
                request.getRequestDispatcher("createQuestion.jsp?class_id=" + classID).forward(request, response);
            }

        } catch (NumberFormatException e) {
        }
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
