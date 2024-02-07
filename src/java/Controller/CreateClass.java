/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClassesDAO;
import Model.LectureDTO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ACER
 */
@MultipartConfig
@WebServlet(name = "CreateClass", urlPatterns = {"/create-class"})
public class CreateClass extends HttpServlet {

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
            out.println("<title>Servlet CreateClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateClass at " + request.getContextPath() + "</h1>");
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
        String name, image, password, description;
        HttpSession session = request.getSession();
        LectureDTO user = (LectureDTO) session.getAttribute("user");
        int lecturer_id = user.getId();
        System.out.println("Lecturer id : " + user.getId());
        name = request.getParameter("className");
        image = request.getParameter("thumbnail");
        Part filePart = request.getPart("thumbnail");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().trim();
        password = request.getParameter("password");
        description = request.getParameter("description");

        fileName = fileName.replaceAll("\\s", "_");
//        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
// Lấy đường dẫn đầy đủ đến thư mục "Assets/img/"
        String uploadPath = "D:\\Download\\GithubInstaller\\LoginFormGoogle\\web\\Assets" + File.separator + "img";
        System.out.println("Path:" + uploadPath);
// Tạo đường dẫn đầy đủ đến file trong thư mục "Assets/img/"
        String filePath = uploadPath + File.separator + fileName;

        ClassesDAO classDAO = new ClassesDAO();
        InputStream input = filePart.getInputStream();

        try {
            // Tạo thư mục nếu nó chưa tồn tại
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Chuyển file vào thư mục "Assets/img/"
            Files.copy(input, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.err.println("Error writing file to disk: " + e.getMessage());
            e.printStackTrace();
        } finally {
            input.close();
        }

        if (classDAO.addClass(name, fileName, password, description, lecturer_id)) {
            request.setAttribute("message", "Create Successfully !!!");
        } else {
            request.setAttribute("message", "Failed !!!");
        }

        response.sendRedirect("MyCourse.jsp");
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
