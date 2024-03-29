/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.ClassesDTO;
import Model.EnrollDAO;
import Model.ExerciseDAO;
import Model.ExerciseDTO;
import Model.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class InsideClass extends HttpServlet {

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
            out.println("<title>Servlet InsideClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsideClass at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("logout").forward(request, response);
            return;
        }
        int class_id = 0;
        try {
            class_id = Integer.parseInt(request.getParameter("class_id"));
        } catch (NumberFormatException e) {
            class_id = -1;
        }
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                System.out.println("Number format in InsideClass: " + e.getMessage());
            }
        }
        ClassesDAO classesDAO = new ClassesDAO();
        ClassesDTO classesDTO = classesDAO.showClassById(class_id);
        ExerciseDAO dao = new ExerciseDAO();
        String role = (String) session.getAttribute("role");
        List<ExerciseDTO> lisExc = dao.getExerciseOnPaging(class_id, role, page);
        for (ExerciseDTO exerciseDTO : lisExc) {
            System.out.println("start date: "+exerciseDTO.getStartDate());
            System.out.println("End date: "+exerciseDTO.getEndDate());
        }
        int totalExercise = new ExerciseDAO().totalExercise(class_id, role);
        int endPage = totalExercise / 3;
        if (totalExercise % 3 != 0) {
            endPage++;
        }
        EnrollDAO enrollDAO = new EnrollDAO();
        List<StudentDTO> listStudent = enrollDAO.getStudentInClass(class_id);
//        
        if (classesDTO != null) {
            request.setAttribute("page", endPage);
            request.setAttribute("classes", classesDTO);
            request.setAttribute("listExercise", lisExc);
            request.setAttribute("listStudent", listStudent);
            request.getRequestDispatcher("insideClass.jsp").forward(request, response);
        } else {
            response.sendError(400, "Get class id Error");
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
