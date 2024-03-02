/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.ClassesDTO;
import Model.EnrollDAO;
import Model.StudentDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EnrollClass", urlPatterns = {"/enroll-class"})
public class EnrollClass extends HttpServlet {

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
            out.println("<title>Servlet EnrollClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnrollClass at " + request.getContextPath() + "</h1>");
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
    private Gson gson = new Gson();

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int getClassId = Integer.parseInt(request.getParameter("class_id"));
        ClassesDAO classDAO = new ClassesDAO();
        ClassesDTO class_detail = classDAO.showClassById(getClassId);
        if (class_detail != null) {
            class_detail.setPassword("Đã bảo mật !!!");
            class_detail.getLecturer().setPassword("Đã bảo mật !!!");

            String classDetailJsonString = gson.toJson(class_detail);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(classDetailJsonString);
            out.flush();
        } else {
            response.setStatus(400);
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
        String password;
        int class_id;

        password = request.getParameter("password");
        class_id = Integer.parseInt(request.getParameter("class_id"));
        HttpSession session = request.getSession();
        String getRole = (String) session.getAttribute("role");
        if (getRole.equals("student")) {
            StudentDTO student = (StudentDTO) session.getAttribute("user");
            ClassesDAO classesDAO = new ClassesDAO();
            EnrollDAO enrollDAO = new EnrollDAO();
            if (classesDAO.checkingClassesPassword(password, class_id)) {
                enrollDAO.enrollClass(student.getId(), class_id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String redirectUrl = gson.toJson("insideClass?class_id=" + class_id);
                response.getWriter().print(redirectUrl);
            } else {
                response.setStatus(400);
            }
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
