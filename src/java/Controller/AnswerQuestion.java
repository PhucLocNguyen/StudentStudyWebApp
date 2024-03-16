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
import java.sql.Timestamp;
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
        String action = request.getParameter("action");

        ExerciseDAO exerciseDAO = new ExerciseDAO();
        StudentDAO studentDAO = new StudentDAO();
        DoDAO doDAO = new DoDAO();

        List<DoDTO> listDo = new ArrayList<>();

        int exercise_id = 0;
        int student_id = 0;

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
        ExerciseDTO exercise = exerciseDAO.loadExercise(exercise_id);
        request.setAttribute("exercise", exercise);
        // phan nay test
//        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//        Timestamp exerciseEndTimestamp = exercise.getEndDate();
//        boolean checkTime = false;
//        if (currentTimestamp.before(exerciseEndTimestamp)) checkTime = true;
//            
//        if (checkTime) {
//            if (action.equals("") || action.equals("answer")) {
//                boolean check = false;
//                if (action.equals("answer")) {
//                    String answer = request.getParameter("answer");
//                    doDAO.addAnswer(exercise_id, student_id, answer);
//                } else {
//                    DoDTO Do = doDAO.loadAnswer(student_id, exercise_id);
//                    if (Do.getStudent() == null) {
//                        check = true;
//                    }
//                }
//                listDo = doDAO.list(exercise_id);
//                request.setAttribute("check", check);
//                request.setAttribute("listDo", listDo);
//                request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
//            } else if (action.equals("edit")) {
//                DoDTO Do = doDAO.loadAnswer(student_id, exercise_id);
//                request.setAttribute("Do", Do);
//                request.getRequestDispatcher("editAnswer.jsp").forward(request, response);
//            } else if (action.equals("update")) {
//                String updateSolution = request.getParameter("updateSolution");
//                doDAO.update(exercise_id, student_id, updateSolution);
//                listDo = doDAO.list(exercise_id);
//                request.setAttribute("check", false);
//                request.setAttribute("listDo", listDo);
//                request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
//            } else if (action.equals("delete")) {
//                doDAO.delete(exercise_id, student_id);
//                request.setAttribute("check", true);
//                request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
//            }
//        } else {
//            request.setAttribute("checkTime", checkTime);
//            request.setAttribute("exercise", exercise);
//            request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
//        }

        if (action.equals("") || action.equals("answer")) {
            boolean check = false;
            if (action.equals("answer")) {
                String answer = request.getParameter("answer");
                doDAO.addAnswer(exercise_id, student_id, answer);
            } else {
                DoDTO Do = doDAO.loadAnswer(student_id, exercise_id);
                if (Do.getStudent() == null) {
                    check = true;
                }
            }
            listDo = doDAO.list(exercise_id);
            request.setAttribute("check", check);
            request.setAttribute("listDo", listDo);
            request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            DoDTO Do = doDAO.loadAnswer(student_id, exercise_id);
            request.setAttribute("Do", Do);
            request.getRequestDispatcher("editAnswer.jsp").forward(request, response);
        } else if (action.equals("update")) {
            String updateSolution = request.getParameter("updateSolution");
            doDAO.update(exercise_id, student_id, updateSolution);
            listDo = doDAO.list(exercise_id);
            request.setAttribute("check", false);
            request.setAttribute("listDo", listDo);
            request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            doDAO.delete(exercise_id, student_id);
            request.setAttribute("check", true);
            request.getRequestDispatcher("answerQuestion.jsp").forward(request, response);
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
