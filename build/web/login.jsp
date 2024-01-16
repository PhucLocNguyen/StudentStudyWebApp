<%-- 
    Document   : login
    Created on : Jan 13, 2024, 6:22:35 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="header">
            <%@include file="./Components/Header.jsp" %>
        </div>
        <div id="content">
            <h1>Login Form</h1>
            <form method="POST" class="form" id="loginForm" action="login">
                <div>
                    <label for="username">Username</label>
                    <input type="text" name="username" placeholder="Enter your username..."/>
                </div>

                <div>
                    <label for="password">Password</label>
                    <input type="password" name="password" placeholder="Enter your password..."/>
                </div>
                <input type="submit"/>
            </form>
           <%= request.getAttribute("ms")!=null?request.getAttribute("ms"):"" %> <p class="ms"></p>
        </div>
        <div id="footer">
            <%@include file="./Components/Footer.jsp" %>
        </div>
    </body>
</html>
