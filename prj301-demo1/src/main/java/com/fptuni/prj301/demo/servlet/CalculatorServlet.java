/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fptuni.prj301.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DUNGHUYNH
 */
public class CalculatorServlet extends HttpServlet {

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
            out.println("<title>PROJ301 Demo - Login result</title>");
            out.println("</head>");
            out.println("<body>");
//            String answer = request.getParameter("result");
//            
//            if(answer.equals("3844")){
//                out.println("Correct!!");
//            }else out.println("Incorrect!!");

            String answer_raw = request.getParameter("answer");
            String result = request.getParameter("result");
            if (answer_raw != null && result!=null) {
                if (answer_raw.equals(result)) {
                    out.println("Correct!!");
                } else {
                    out.println("Incorrect!!");
                }
            } else {
                Random random = new Random();
                int firstNum = random.nextInt(100);
                int secondNum = random.nextInt(100);
                int answer = 0;
                int operationNum = random.nextInt(4);
                String operation = "";
                switch (operationNum) {
                    case 0:
                        operation = "+";
                        answer = firstNum + secondNum;
                        break;
                    case 1:
                        operation = "-";
                        answer = firstNum - secondNum;
                        break;
                    case 2:
                        operation = "x";
                        answer = firstNum * secondNum;
                        break;
                    case 3:
                        operation = "/";
                        answer = firstNum / secondNum;
                        break;
                }

                out.println("<h1>Please answer</h1>");
                out.println("<h1>" + firstNum + " " + operation + " " + secondNum + " = </h1>");
                out.println("<form action=\"calculator\" method=\"POST\">\n"
                        + "            <input name=\"answer\" type=\"text\">\n"
                        + "            <input name=\"result\" type=\"hidden\" value = \""+answer+"\">"
                        + "            <input name=\"user\" type=\"submit\">\n"
                        + "        </form>");
            }
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
