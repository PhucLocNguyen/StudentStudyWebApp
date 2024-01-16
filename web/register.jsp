<%-- 
    Document   : register
    Created on : Dec 17, 2023, 11:31:58 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <style>
            .Register_form{
                margin: auto;
                padding: 20px;
                text-align: center;
                font-size: 30px;
                border: 2px solid blue;
                width: 500px; 
            }
            .Register_form h1{
                color: red;
                font-style: italic;
            }
            .input_form label{
                font-size: 23px;
                margin-right: 15px;
                font-weight: bold;
            }
            .input_form input{
                height: 20px;
                font-size: 20px;
            }
            .footer input{
                font-size: 30px;
                margin: 10px;
            }
        </style>
    </head>
    <body>
        <div class="Register_form">
            <h1>Register Account</h1>
            <form action="register" method="post">
                <div class="input_content">
                    <div class="input_form">
                        <label for="username">Username</label>
                        <input type="text" name="username">
                    </div >
                    <div class="input_form">
                        <label for="pass">Password</label>
                        <input type="password" name="pass">
                    </div>
                    <div class="input_form">
                        <label for="agian_pass">Confirm password</label>
                        <input type="password" name="confirm_pass">
                    </div>
                </div>
                <% 
                    if(request.getAttribute("message")!=null){
                     String message = (String) request.getAttribute("message");   
                %>
                <div style="font-size: 17px;color: red"><%=message%></div>
                <%
                    }
                %>
                <div class="footer">
                    <input  type="submit" value="Register" />
                    <input type="reset" value="Reset" />
                </div>
               
            </form>
        </div>
    </body>
</html>
