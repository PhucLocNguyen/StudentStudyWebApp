/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.ClassesDTO;
import Model.ExerciseDAO;
import Model.LectureDTO;
import Model.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SearchingClass extends HttpServlet {

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
        ClassesDAO classdao = new ClassesDAO();
        String keyWord = request.getParameter("keyWord");
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
        if (keyWord.isEmpty()) {
            keyWord = "";
        }
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                System.out.println("Number format in InsideClass: " + e.getMessage());
            }
        }
        int totalClass = new ClassesDAO().totalClassFound(keyWord, 0, 3, (String) session.getAttribute("role"));
        int endPage = totalClass / 6;
        if (totalClass % 6 != 0) {
            endPage++;
        }
        
        // Moi sua khuc nay
        String getRole = "";
        int id = 0;
        HttpSession getSession = request.getSession(false);
        if (getSession != null && session.getAttribute("user") != null) {
            getRole = (String) session.getAttribute("role");
            if (getRole.equals("lecturer")) {
                LectureDTO user = (LectureDTO) session.getAttribute("user");           
                id = user.getId();
            } else {
                StudentDTO user = (StudentDTO) session.getAttribute("user");               
                id = user.getId();
            }
        }
        //Toi day
        
        List<ClassesDTO> listSearching = classdao.showClassWithKeyWord(keyWord, (String) session.getAttribute("role"), sortByCondition, page,id);
        request.setAttribute("page", endPage);
        request.setAttribute("selectValue", sortByCondition);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("listSearching", listSearching);
        request.getRequestDispatcher("searching.jsp").forward(request, response);
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
