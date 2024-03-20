<%-- 
    Document   : studentList
    Created on : Mar 13, 2024, 4:31:27 PM
    Author     : ACER
--%>

<%@page import="java.util.List"%>
<%@page import="Model.EnrollDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Student list Page</title>
        <link rel="stylesheet" href="./Assets/css/style.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/froala_editor/froala_editor.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="./Assets/themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    </head>
    <body>
        <%@include file="Components/Header.jsp" %>
        <div class="bg-body-tertiary pt-3" >
            <div class="container">
                <div>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item"><a href="insideClass?class_id=${requestScope.classDetails.id}">Classroom</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Show list student in class</li>
                        </ol>
                    </nav>
                </div>
                <h1 class="text-center mb-3">List Student in classroom ${requestScope.classDetails.name}</h1>
                <table class="table" id="id" summary="Student list">
                    <caption>... then see student list </caption>
                    <thead>
                        <tr><th>Index</th><th class="text-center">Image</th><th>Email</th><th>Student Code</th><th>Full Name</th></tr>
                    </thead>
                    <tbody>
                        <% if (request.getAttribute("listStudent") != null) {
                                int count = 0;
                                List<StudentDTO> list = (List<StudentDTO>) request.getAttribute("listStudent");
                                for (StudentDTO item : list) {
                                    count++;
                        %>
                        <tr><td><%=count%></td>
                            <td>
                    <center><img src="<%= item.getThumbnail()%>" style="height:146px;width:111px;border-width:0px;"></center>
                    </td>
                    <td><a class="text-decoration-none" href="viewprofile?action=showprofile&id=<%= item.getId()%>&role=student"><%= item.getEmail()%></a></td>
                    <td><a class="text-decoration-none" href="viewprofile?action=showprofile&id=<%= item.getId()%>&role=student"><%=item.getId()%></a></td>
                    <td><a class="text-decoration-none" href="viewprofile?action=showprofile&id=<%= item.getId()%>&role=student"><%=item.getName()%></a></td>
                    
                    </tr>
                    <%}
                        }%>
                </table>


            </div>

            <div id="myModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog" style="min-width: 90%">

                </div>
            </div>
        </div>
    
    </body>
</html>
