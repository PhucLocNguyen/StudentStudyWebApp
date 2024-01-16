<%-- 
    Document   : login
    Created on : Dec 17, 2023, 11:25:35 PM
    Author     : User
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            div{
                margin: auto;
                padding: 20px;
                text-align: center;
                font-size: 30px;
                border: 2px solid blue;
                width: 500px; 
            }
            div, form{
                font-style: italic;
                font-weight: bold;
            }
            input{
                font-size: 20px;
            }
            .button{
                font-size: 20px;
            }
            
        </style>
    </head>
    <body>
        <div>
            <h1 style="color: red"> Login</h1>
            <form action="login" method="post">
                Username <input type="text" name="username"> <br>
                Password <input type="password" name="password"> <br>
                <% 
                    if(request.getAttribute("error")!=null){
                        String error = (String) request.getAttribute("error");
                %>
                <h5 style="color: red; font-size: 20px; border: none; margin: 0px " > <%=error %> </h5>
                <% 
                    }
                %>
                <input class="button" type="submit" value="Submit" />
                <input class="button" type="reset" value="Reset" />
                <input type="submit" value="register" name="register" />
            </form>
        </div>
    </body>
</html>
