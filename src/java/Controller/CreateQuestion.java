/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.ClassesDTO;
import Model.ExerciseDAO;
import Model.ExerciseDTO;
import Model.LectureDTO;
import Model.NotificationDAO;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.FileUtils;

/**
 *
 * @author HA GIA KHANH
 */
@MultipartConfig
public class CreateQuestion extends HttpServlet {

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
        String classID_raw = request.getParameter("classId");
        ExerciseDAO dao = new ExerciseDAO();
        ClassesDAO classDAO = new ClassesDAO();
        NotificationDAO notificationDAO = new NotificationDAO();
        LectureDTO user = (LectureDTO) session.getAttribute("user");
        ClassesDTO classessDTO = classDAO.showClassById(Integer.parseInt(classID_raw));

        try {
            if (action != null) {
                int classID = Integer.parseInt(classID_raw);
                if (action.equals("create")) {
                    String title = request.getParameter("title");
                    String description = request.getParameter("description");
                    int lecturer_id = user.getId();

                    String status = request.getParameter("status");

                    String start_time = request.getParameter("start_time");
                    String end_time = request.getParameter("end_time");

                    //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    Timestamp start_timestamp = null;
                    Timestamp end_timestamp = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    java.util.Date start_date = null, end_date = null;
                    if (start_time != null && !start_time.isEmpty()) {
                        try {
                            start_date = sdf.parse(start_time);
                            start_timestamp = new Timestamp(start_date.getTime());
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (end_time != null && !end_time.isEmpty()) {
                        try {
                            end_date = sdf.parse(end_time);
                            end_timestamp = new Timestamp(end_date.getTime());
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Part filePart = request.getPart("thumbnail");
                    String imageUrl;
                    if (filePart != null && filePart.getSize() > 0) {
                        FileUtils fileUtils = new FileUtils();
                        imageUrl = fileUtils.insertImage(filePart);
                    } else {
                        imageUrl = "";
                    }

                    if (!validateDates(start_timestamp, end_timestamp)) {
                        request.setAttribute("timeerror", "End date must be after start date");
                        request.setAttribute("title", title);
                        request.setAttribute("description", description);
                        request.setAttribute("status", status);
                        request.setAttribute("thumbnail", imageUrl);
                        request.setAttribute("start_date", start_timestamp);
                        request.setAttribute("end_date", end_timestamp);
                        request.getRequestDispatcher("createQuestion.jsp?class_id=" + classID + "&action=" + action).forward(request, response);
                        return;
                    }
                    if (dao.addExercise(title, description, imageUrl, status, classID, lecturer_id, start_timestamp, end_timestamp)) {
                        response.sendRedirect("insideClass?class_id=" + classID);
                    } else {
                        request.setAttribute("error", "Create fail");
                        request.getRequestDispatcher("createQuestion.jsp?class_id=" + classID).forward(request, response);
                    }
                } else if (action.equals("edit")) {
                    int exercise_id = Integer.parseInt(request.getParameter("exercise_id"));
                    ExerciseDTO ex = dao.loadExercise(exercise_id);
                    request.setAttribute("title", ex.getTitle());
                    request.setAttribute("description", ex.getDescription());
                    request.setAttribute("status", ex.getStatus());
                    request.setAttribute("thumbnail", ex.getMedia());
                    request.setAttribute("start_date", ex.getStartDate());
                    request.setAttribute("end_date", ex.getEndDate());
                    request.setAttribute("exercise_id", exercise_id);
                    request.setAttribute("nextaction", "update");
                    request.getRequestDispatcher("createQuestion.jsp?action=update&class_id=" + classID).forward(request, response);
                } else if (action.equals("update")) {
                    int exercise_id = Integer.parseInt(request.getParameter("exercise_id"));
                    String title = request.getParameter("title");
                    String description = request.getParameter("description");
                    String status = request.getParameter("status");
                    ExerciseDTO exercise = dao.loadExercise(exercise_id);
                    String start_time = request.getParameter("start_time");
                    String end_time = request.getParameter("end_time");
                    //media
                    Part filePart = request.getPart("thumbnail");
                    String imageUrl;
                    if (filePart != null && filePart.getSize() > 0) {
                        FileUtils fileUtils = new FileUtils();
                        imageUrl = fileUtils.insertImage(filePart);
                    } else {
                        imageUrl = "";
                    }
                    //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    Timestamp start_timestamp = null;
                    Timestamp end_timestamp = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    java.util.Date start_date = null, end_date = null;
                    if (start_time != null && !start_time.isEmpty()) {
                        try {
                            start_date = sdf.parse(start_time);
                            start_timestamp = new Timestamp(start_date.getTime());
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (end_time != null && !end_time.isEmpty()) {
                        try {
                            end_date = sdf.parse(end_time);
                            end_timestamp = new Timestamp(end_date.getTime());
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    boolean check = validateDates(start_timestamp, end_timestamp);
                    if (!validateDates(start_timestamp, end_timestamp)) {
                        request.setAttribute("timeerror", "End date must be after start date");
                        request.setAttribute("title", title);
                        request.setAttribute("description", description);
                        request.setAttribute("status", status);
                        request.setAttribute("thumbnail", imageUrl);
                        request.setAttribute("start_date", start_timestamp);
                        request.setAttribute("end_date", end_timestamp);
                        request.setAttribute("nextaction", "update");
                        request.setAttribute("exercise_id", exercise_id);
                        request.getRequestDispatcher("createQuestion.jsp?class_id=" + classID).forward(request, response);
                    } else {
                        if (status.equals("active")) {
                            if (dao.updateExercise(exercise_id, title, imageUrl, description, status, start_timestamp, end_timestamp)) {
                                notificationDAO.createNotificationInClassActivity("Lecturer" + user.getName() + " Updated the Active exercise in class " + classessDTO.getName(), exercise.getExerciseID());
                                response.sendRedirect("insideClass?class_id=" + classID);
                            } else {
                                request.setAttribute("error", "Update fail");
                                request.setAttribute("timeerror", "End date must be after start date");
                                request.setAttribute("title", title);
                                request.setAttribute("description", description);
                                request.setAttribute("status", status);
                                request.setAttribute("thumbnail", imageUrl);
                                request.setAttribute("start_date", start_timestamp);
                                request.setAttribute("end_date", end_timestamp);
                                request.getRequestDispatcher("createQuestion.jsp?class_id=" + classID).forward(request, response);
                            }
                        } else {
                            if (dao.updateExercise(exercise_id, title, imageUrl, description, status, null, null)) {
                                response.sendRedirect("insideClass?class_id=" + classID);
                            } else {
                                request.setAttribute("error", "Update fail");
                                request.getRequestDispatcher("createQuestion.jsp?class_id=" + classID).forward(request, response);
                            }
                        }
                    }

                } else if (action.equals("stop")) {
                    int exercise_id = Integer.parseInt(request.getParameter("exercise_id"));
                    ExerciseDTO ex = dao.loadExercise(exercise_id);
                    if (dao.inactiveExercise(exercise_id)) {
                        request.setAttribute("inactive", ex.getStatus());
                        notificationDAO.createNotificationInClassActivity("Lecturer" + user.getName() + " Stop the exercise in class " + classessDTO.getName(), exercise_id);
                        response.sendRedirect("insideClass?class_id=" + classID);
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Wrong number format: " + e.getMessage());
        }
    }

    private boolean validateDates(Timestamp start, Timestamp end) {
        if (start == null && end == null) {
            return true;
        }
        if (start == null || end == null) {
            return false;
        }
        if (start.after(end)) {
            return false;
        }
        return true;
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
