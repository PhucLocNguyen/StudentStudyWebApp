/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.ClassesDTO;
import Model.EnrollDAO;
import Model.LectureDTO;
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
 * @author User
 */
public class ShowDashBoard extends HttpServlet {

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
        List<ClassesDTO> listClass = new ArrayList<>();
        ClassesDAO classDAO = new ClassesDAO();
        String sortByCondition = "1";
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("logout").forward(request, response);
            return;
        }
        if (request.getParameter("selectValue") != null) {
            sortByCondition = request.getParameter("selectValue");
        }
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                System.out.println("Number format in InsideClass: " + e.getMessage());
            }
        }

        String getRole = (String) session.getAttribute("role");
        if (getRole.equals("student")) {
            EnrollDAO enrollDAO = new EnrollDAO();
            StudentDTO student = (StudentDTO) session.getAttribute("user");
            int totalClass = new ClassesDAO().totalClassFound("", student.getId(),1,getRole);
            int endPage = totalClass / 6;
            if (totalClass % 6 != 0) {
                endPage++;
            }
            request.setAttribute("page", endPage);
            listClass = classDAO.showClassOwnedByStudentIDPaging(student.getId(), sortByCondition, page);
        } else {
            LectureDTO lecture = (LectureDTO) session.getAttribute("user");
            int totalClass = new ClassesDAO().totalClassFound("", lecture.getId(),2,getRole);
            int endPage = totalClass / 6;
            if (totalClass % 6 != 0) {
                endPage++;
            }
            request.setAttribute("page", endPage);
            listClass = classDAO.showClassOwnedByLectureIDPaging(lecture.getId(), sortByCondition,page);
            
        }
        
        request.setAttribute("selectValue", sortByCondition);
        request.setAttribute("listClass", listClass);
        request.getRequestDispatcher("myCourse.jsp").forward(request, response);
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
