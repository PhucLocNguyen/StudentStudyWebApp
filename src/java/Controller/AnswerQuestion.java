/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DoDAO;
import Model.DoDTO;
import Model.ExerciseDAO;
import Model.ExerciseDTO;
import Model.StudentDAO;
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
public class AnswerQuestion extends HttpServlet {

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
        String action = request.getParameter("action");

        ExerciseDAO exerciseDAO = new ExerciseDAO();
        StudentDAO studentDAO = new StudentDAO();
        DoDAO doDAO = new DoDAO();

        List<DoDTO> listDo = new ArrayList<>();

        int exercise_id = 0;
        int student_id = 0;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role.equals("student")) {
            StudentDTO student = (StudentDTO) session.getAttribute("user");
            student_id = student.getId();
        }

        try {
            exercise_id = Integer.parseInt(request.getParameter("exercise_id"));
        } catch (NumberFormatException e) {
            System.out.println("Number format wrong: " + e.getMessage());
            e.printStackTrace();
        }
        ExerciseDTO exercise = exerciseDAO.loadExcercise(exercise_id);

        request.setAttribute("exercise", exercise);

        if (action.equals("")) {
            int class_id = 0;
            try {
                class_id = Integer.parseInt(request.getParameter("class_id"));
            } catch (NumberFormatException e) {
                System.out.println("Number format wrong: " + e.getMessage());
                e.printStackTrace();
            }
            if (class_id != 0 && exercise_id != 0) {
                listDo = doDAO.list(exercise_id);
                request.setAttribute("listDo", listDo);
                DoDTO Do = doDAO.loadAnswer(student_id, exercise_id);
                if (Do.getStudent() == null) {
                    request.setAttribute("check", true);
                } else {
                    request.setAttribute("check", false);
                }

                request.setAttribute("action", "answer");
                request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
            }

        } else if (action.equals("answer")) {
            String answer = request.getParameter("answer");
            doDAO.addAnswer(exercise_id, student_id, answer);
            listDo = doDAO.list(exercise_id);
            DoDTO Do = doDAO.loadAnswer(student_id, exercise_id);
            if (Do.getStudent() == null) {
                request.setAttribute("check", true);
            } else {
                request.setAttribute("check", false);
            }
            request.setAttribute("listDo", listDo);
            request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            DoDTO Do = doDAO.loadAnswer(student_id, exercise_id);
            request.setAttribute("Do", Do);
            request.getRequestDispatcher("editAnswer.jsp").forward(request, response);
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
