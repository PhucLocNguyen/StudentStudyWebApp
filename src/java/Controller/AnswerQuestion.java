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
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringEscapeUtils;
import utils.CharacterUtils;

/**
 *
 * @author User
 */
public class AnswerQuestion extends HttpServlet {

    private Gson gson = new Gson();

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
        PrintWriter out = response.getWriter();
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

        if (action.equals("") || action.equals("answer")) {
            boolean check = false;
            if (action.equals("answer")) {
                String answer = request.getParameter("answer");
                if (answer.trim().isEmpty()) {
                    request.setAttribute("error", "Answer is not be a blank");
                    check = true;
                } else {
                    doDAO.addAnswer(exercise_id, student_id, answer);

                }
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
        } else if (action.equals("score")) {
            int class_id = 0;
            List<DoDTO> listScore = null;
            try {
                class_id = Integer.parseInt(request.getParameter("class_id"));
            } catch (NumberFormatException e) {
                System.out.println("Number format wrong: " + e.getMessage());
                e.printStackTrace();
            }
            if (role.equals("lecturer")) {
                int getStudentID = Integer.parseInt(request.getParameter("student_id"));
                student_id = getStudentID;
            }
            listScore = doDAO.listScore(student_id, class_id, role);
            if (listScore.size() > 0) {
                String listScoreJson = gson.toJson(listScore);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(listScoreJson);
                out.flush();
            } else {
                response.setStatus(401);
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
