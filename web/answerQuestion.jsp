<%-- 
    Document   : answerQuestion
    Created on : Mar 1, 2024, 12:05:22 AM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.DoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Answer Question</title>
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
                <h1 class="fw-bold">${requestScope.exercise.classes.name}</h1>
                <h3 class="fw-medium">${requestScope.exercise.title}</h3>
                <span class="badge rounded-pill text-bg-secondary my-1 me-3">${requestScope.exercise.createdDate}</span>
                <span class="badge rounded-pill text-bg-secondary my-1 me-3">To : 5:30 20/09/2023</span>
                <span class="badge rounded-pill text-bg-secondary my-1">${requestScope.exercise.status}</span>

                <div class="card rounded-4 mt-3">
                    <div class="card-body" style="min-height: 10rem;">
                        <h5 class="card-title fw-bolder fs-4">Content</h5>
                        <p class="card-text fs-4">${requestScope.exercise.description}</p>
                    </div>
                </div>

                <div class="card rounded-4 mt-4">
                    <div class="card-body">
                        <!-- Comment form-->
                        <% if ((boolean) request.getAttribute("check")) {%>
                        <form class="mb-2" action="answerquestion">
                            <input type="hidden" name="action" value="${requestScope.action}">
                            <input type="hidden" name="exercise_id" value="${requestScope.exercise.excerciseID}">
                            <textarea class="form-control" rows="3" placeholder="Tra loi cau hoi" name="answer"></textarea>
                            <input type="submit" class="btn btn-primary my-3" value="SEND">
                        </form>
                        <% } else {%>   

                        <!-- Single comment-->
                        <%
                            List<DoDTO> listDo = (ArrayList<DoDTO>) request.getAttribute("listDo");
                            for (DoDTO item : listDo) {

                        %>
                        <div class="row">
                            <div class="d-flex my-3 col-11">
                                <div class="flex-shrink-0"><img  style="width: 3rem;" class="rounded-circle"
                                                                 src="./img/gumbal.jpg" alt="..." /></div>
                                <div class="ms-3">
                                    <div class="fw-bold"><%= item.getStudent().getName()%></div>
                                    <%= item.getSolution()%>
                                </div>
                            </div>
                            <%
                                if (item.getStudent().getId() == id) {
                            %>
                            <a href="answerquestion?action=edit&exercise_id=${requestScope.exercise.excerciseID}" class="col-1 position-relative" style="text-decoration: none;">
                                <span class="position-absolute top-0 start-0 translate-middle p-2" style="font-size: 40px; color: black">...</span>
                            </a>
                            <%}%>
                        </div>
                        <% }
                            }%>
                    </div>
                </div>
            </div>
        </div>
            <%@include file="Components/Footer.jsp" %>
    </body>
</html>
