/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.LectureDTO;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.Constants;
import utils.FileUtils;

/**
 *
 * @author ACER
 */
@MultipartConfig
@WebServlet(name = "CreateClass", urlPatterns = {"/create-class"})
public class CreateClass extends HttpServlet {

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
            out.println("<title>Servlet CreateClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateClass at " + request.getContextPath() + "</h1>");
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
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name, imageUrl, password, description, action;
        HttpSession session = request.getSession(false);
        ClassesDAO classDAO = new ClassesDAO();

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("logout").forward(request, response);
            return;
        }
        action = request.getParameter("action");
        if (action != null) {
            if (action.equals("create")) {
                LectureDTO user = (LectureDTO) session.getAttribute("user");
                int lecturer_id = user.getId();

                name = request.getParameter("className");
                Part filePart = request.getPart("thumbnail");
                password = request.getParameter("password");
                description = request.getParameter("description");
                FileUtils fileUtils = new FileUtils();
                imageUrl = fileUtils.insertImage(filePart);

                if (classDAO.addClass(name, imageUrl, password, description, lecturer_id)) {
                    request.setAttribute("message", "Create Successfully !!!");
                } else {
                    request.setAttribute("message", "Failed !!!");
                }
                response.sendRedirect("showdashboard");
//                request.getRequestDispatcher("showdashboard").forward(request, response);
            } else if (action.equals("checkPasswordToDelete")) {
                String getPassword = request.getParameter("password");
                int getClassId = Integer.parseInt(request.getParameter("class_id"));
                String getRole = (String) session.getAttribute("role");
                System.out.println("Check checking delete!");
                if (classDAO.checkingClassesPassword(getPassword, getClassId) && getRole.equals("lecturer")) {
                    classDAO.deleteClassByID(getClassId);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    String redirectUrl = gson.toJson("showdashboard");
                    response.getWriter().print(redirectUrl);
                } else {
                    response.setStatus(400);
                }
            }
        } else {
            response.setStatus(404);
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
