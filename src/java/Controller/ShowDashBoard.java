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

        List<ClassesDTO> listClass = new ArrayList<>();
        ClassesDAO classDAO = new ClassesDAO();

        HttpSession session = request.getSession();
        String getRole = (String) session.getAttribute("role");
        if (getRole.equals("student")) {
            EnrollDAO enrollDAO = new EnrollDAO();
            List<Integer> arrayIdClass = new ArrayList<>();
            StudentDTO student = (StudentDTO) session.getAttribute("user");
            arrayIdClass = enrollDAO.idClassEnrolled(student.getId());
            if (arrayIdClass != null) {
                for (Integer arrayIdClas : arrayIdClass) {
                    ClassesDTO classEnrolled = classDAO.showClassById(arrayIdClas);
                    listClass.add(classEnrolled);
                }
            }
        } else {
            LectureDTO lecture = (LectureDTO) session.getAttribute("user");
        }

        request.setAttribute("listClass", listClass);
        request.getRequestDispatcher("MyCourse.jsp").forward(request, response);
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
