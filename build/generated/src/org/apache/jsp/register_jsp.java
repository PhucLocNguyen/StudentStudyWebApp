package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Register</title>\n");
      out.write("        <style>\n");
      out.write("            .Register_form{\n");
      out.write("                margin: auto;\n");
      out.write("                padding: 20px;\n");
      out.write("                text-align: center;\n");
      out.write("                font-size: 30px;\n");
      out.write("                border: 2px solid blue;\n");
      out.write("                width: 500px; \n");
      out.write("            }\n");
      out.write("            .Register_form h1{\n");
      out.write("                color: red;\n");
      out.write("                font-style: italic;\n");
      out.write("            }\n");
      out.write("            .input_form label{\n");
      out.write("                font-size: 23px;\n");
      out.write("                margin-right: 15px;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("            .input_form input{\n");
      out.write("                height: 20px;\n");
      out.write("                font-size: 20px;\n");
      out.write("            }\n");
      out.write("            .footer input{\n");
      out.write("                font-size: 30px;\n");
      out.write("                margin: 10px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"Register_form\">\n");
      out.write("            <h1>Register Account</h1>\n");
      out.write("            <form action=\"register\" method=\"post\">\n");
      out.write("                <div class=\"input_content\">\n");
      out.write("                    <div class=\"input_form\">\n");
      out.write("                        <label for=\"username\">Username</label>\n");
      out.write("                        <input type=\"text\" name=\"username\">\n");
      out.write("                    </div >\n");
      out.write("                    <div class=\"input_form\">\n");
      out.write("                        <label for=\"pass\">Password</label>\n");
      out.write("                        <input type=\"password\" name=\"pass\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"input_form\">\n");
      out.write("                        <label for=\"agian_pass\">Confirm password</label>\n");
      out.write("                        <input type=\"password\" name=\"confirm_pass\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");
 
                    if(request.getAttribute("message")!=null){
                        String message = (String) request.getAttribute("message");
                
      out.write("\n");
      out.write("                <div style=\"font-size: 13px; color: red\"> ");
      out.print( message);
      out.write("</div>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("                <div class=\"footer\">\n");
      out.write("                    <input  type=\"submit\" value=\"Register\" />\n");
      out.write("                    <input type=\"reset\" value=\"Reset\" />\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
