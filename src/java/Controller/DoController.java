/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.ClassesDTO;
import Model.DoDAO;
import Model.DoDTO;
import Model.EnrollDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
@WebServlet(name = "DoController", urlPatterns = {"/do"})
public class DoController extends HttpServlet {

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
            int exercise_id = Integer.parseInt(request.getParameter("exercise_id"));
            int student_id = Integer.parseInt(request.getParameter("student_id"));
            DoDAO doDAO = new DoDAO();
            DoDTO studentAnswer = null;
            studentAnswer = doDAO.showStudentAnswerById(exercise_id, student_id);
            Gson gson = new Gson();
            if (studentAnswer != null) {

                String doDetailJsonString = gson.toJson(studentAnswer);
                System.out.println(doDetailJsonString);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(doDetailJsonString);
                out.flush();
            } else {
                response.setStatus(400);
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
        String getAction = request.getParameter("action");
        float score = 0;
        int student_id = 0, excercise_id = 0, class_id = 0;
        try {
            score = Float.parseFloat(request.getParameter("score"));
            student_id = Integer.parseInt(request.getParameter("student_id"));
            excercise_id = Integer.parseInt(request.getParameter("excercise_id"));
            class_id = Integer.parseInt(request.getParameter("class_id"));
        } catch (NumberFormatException e) {
            System.err.println("Error parse Number format in DoController POST");
        }
        DoDAO doDAO = new DoDAO();

        if (getAction.equals("grade")) {
            doDAO.addScoreToDo(score, excercise_id, student_id);
        } else if (getAction.equals("edit_score")) {
            doDAO.updateScoreToDo(score, excercise_id, student_id);
        }
        response.sendRedirect(request.getContextPath() + "/excerciseView?excercise_id=" + excercise_id + "&class_id=" + class_id + "#student" + student_id);
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
