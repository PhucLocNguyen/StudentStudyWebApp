/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.ClassesDTO;
import Model.EnrollDAO;
import Model.EnrollDTO;
import Model.LectureDAO;
import Model.LectureDTO;
import Model.StudentDAO;
import Model.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.FileUtils;

/**
 *
 * @author hn190
 */
@MultipartConfig
public class ViewProfileServlet extends HttpServlet {

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
        EnrollDAO enrollDAO = new EnrollDAO();
        StudentDAO studentDAO = new StudentDAO();
        LectureDAO lectureDAO = new LectureDAO();
        ClassesDAO classesDAO = new ClassesDAO();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String role = request.getParameter("role");

        try {

            if (action.equals("showmyprofile")) {

                int idNumber = Integer.parseInt(id);
                if (role.equals("lecturer")) {

                    lectureDAO = new LectureDAO();
                    LectureDTO lecture = lectureDAO.searchLectureById(idNumber);
                    List<ClassesDTO> list = classesDAO.showClassOwnedByLectureID(idNumber, "0");
                    request.setAttribute("list", list);
                    request.setAttribute("object", lecture);
                    request.setAttribute("action", "showmyprofile");
                    request.getRequestDispatcher("viewProfile.jsp").forward(request, response);

                } else if (role.equals("student")) {

                    List<EnrollDTO> list = enrollDAO.enrolledClasses(idNumber);
                    StudentDTO student = studentDAO.showStudentById(idNumber);

                    request.setAttribute("list", list);
                    request.setAttribute("object", student);
                    request.setAttribute("action", "showmyprofile");
                    request.getRequestDispatcher("viewProfile.jsp").forward(request, response);

                }

            } else if (action.equals("showprofile")) {

                int idNumber = Integer.parseInt(id);

                if (role.equals("lecturer")) {

                } else if (role.equals("student")) {

                    List<EnrollDTO> list = enrollDAO.enrolledClasses(idNumber);
                    StudentDTO student = studentDAO.showStudentById(idNumber);

                    request.setAttribute("action", "showprofile");
                    request.setAttribute("list", list);
                    request.setAttribute("student", student);
                    request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
                }

            } else if (action.equals("edit")) {

                int idNumber = Integer.parseInt(id);

                if (role.equals("lecturer")) {

                    LectureDTO lecturer = lectureDAO.searchLectureById(idNumber);
                    request.setAttribute("object", lecturer);
                    request.getRequestDispatcher("editProfile.jsp").forward(request, response);

                } else if (role.equals("student")) {

                    StudentDTO student = studentDAO.showStudentById(idNumber);
                    request.setAttribute("object", student);
                    request.getRequestDispatcher("editProfile.jsp").forward(request, response);

                }

            } else if (action.equals("update")) {

                int idNumber = Integer.parseInt(id);
                String name = request.getParameter("name").trim();

                Part filePart = request.getPart("thumbnail");
                String imageUrl;
                if (filePart != null && filePart.getSize() > 0) {
                    FileUtils fileUtils = new FileUtils();
                    imageUrl = fileUtils.insertImage(filePart);
                } else {
                    imageUrl = "";
                }

                if (name.isEmpty() || name.equals("")) {
                    request.setAttribute("error", "Name cannot be blank");
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        String getRole = (String) session.getAttribute("role");
                        if (getRole.equals("lecturer")) {
                            LectureDTO lecturer = lectureDAO.searchLectureById(idNumber);
                            request.setAttribute("object", lecturer);
                        } else {
                            StudentDTO student = studentDAO.showStudentById(idNumber);
                            request.setAttribute("object", student);
                        }
                    }
                    request.getRequestDispatcher("editProfile.jsp").forward(request, response);
                } else {

                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        String getRole = (String) session.getAttribute("role");
                        if (getRole.equals("lecturer")) {
                            LectureDTO lecturer = new LectureDTO(idNumber, name, imageUrl);
                            lectureDAO.update(lecturer);
                            LectureDTO user = (LectureDTO) session.getAttribute("user");
                            user.setName(name);
                            user.setThumbnail(imageUrl);
                        } else {

                            StudentDTO student = new StudentDTO(idNumber, name, imageUrl);
                            studentDAO.update(student);
                            StudentDTO user = (StudentDTO) session.getAttribute("user");
                            user.setName(name);
                            user.setThumbnail(imageUrl);
                        }
                    }
                    response.sendRedirect("home");
                }

            } else if (action.equals("showlist")) {

                Integer class_id = null;
                try {
                    class_id = Integer.parseInt(request.getParameter("classid"));
                } catch (NumberFormatException e) {
                    System.out.println("ViewProfileServlet action showList ERROR: " + e.getMessage());
                }
                List<StudentDTO> listStudent = enrollDAO.getStudentInClass(class_id);
                request.setAttribute("listStudent", listStudent);
                request.getRequestDispatcher("studentListForProfile.jsp").forward(request, response);

            }

        } catch (IOException | NumberFormatException | ServletException e) {

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
