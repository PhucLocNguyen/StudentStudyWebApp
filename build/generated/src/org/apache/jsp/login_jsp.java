package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Account;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Login</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <style>\n");
      out.write("            div{\n");
      out.write("                margin: auto;\n");
      out.write("                padding: 20px;\n");
      out.write("                text-align: center;\n");
      out.write("                font-size: 30px;\n");
      out.write("                border: 2px solid blue;\n");
      out.write("                width: 500px; \n");
      out.write("            }\n");
      out.write("            div, form{\n");
      out.write("                font-style: italic;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("            input{\n");
      out.write("                font-size: 20px;\n");
      out.write("            }\n");
      out.write("            .button{\n");
      out.write("                font-size: 20px;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div>\n");
      out.write("            <h1 style=\"color: red\"> Login</h1>\n");
      out.write("            <form action=\"login\" method=\"post\">\n");
      out.write("                Username <input type=\"text\" name=\"username\"> <br>\n");
      out.write("                Password <input type=\"password\" name=\"password\"> <br>\n");
      out.write("                ");
 
                    if(request.getAttribute("error")!=null){
                        String error = (String) request.getAttribute("error");
                
      out.write("\n");
      out.write("                <h5 style=\"color: red; font-size: 20px; border: none; margin: 0px \" > ");
      out.print(error );
      out.write(" </h5>\n");
      out.write("                ");
 
                    }
                
      out.write("\n");
      out.write("                <input class=\"button\" type=\"submit\" value=\"Submit\" />\n");
      out.write("                <input class=\"button\" type=\"reset\" value=\"Reset\" />\n");
      out.write("                <input type=\"submit\" value=\"register\" name=\"register\" />\n");
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
