<%-- 
    Document   : Searching
    Created on : Feb 8, 2024, 11:43:25 AM
    Author     : User
--%>

<%@page import="Model.ClassesDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Searching <%= request.getAttribute("keyWord") %></title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
         <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    </head>
    <%@include file="./Components/Header.jsp" %>
    <body>
        <div class="container">
                <% 
                    List<ClassesDTO> listSearching = (List<ClassesDTO>)request.getAttribute("listSearching");
                %>
                <div class="row">
                    <h3 class="fw-medium mb-1">Search results: <%= listSearching.size() %>
                    </h3>
                </div>
                              
                      
                    
                <div class="row mt-3 mb-4">
                    <% 
                        int count = 0;
                        for (ClassesDTO item: listSearching){
                            count++;
                    %> 
                    <div class="col-lg-4">
                        <div class="card rounded-4">
                            <img src="<%=item.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="..." style="max-height: 10rem;">
                            <div class="card-body">
                                <h5 class="card-title"><%=item.getName()%></h5>
                                <p class="card-text">Giảng viên: <%= item.getLecturer().getEmail() %></p>
                                <a href="<%="#myModal" + count%>" role="button" class="btn btn-lg btn-primary" data-bs-toggle="modal">Show more</a>
                            </div>
                        </div>
                    </div>
                    <div id="<%="myModal" + count%>" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title"><%=item.getName()%></h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>

                                <form accept-charset="UTF-8" action="enroll-class" method="POST">

                                    <div class="modal-body">
                                        <p class="text-primary mb-1">Giảng viên <%= item.getLecturer().getName() %></p>
                                        
                                        <p class="text-primary mb-1">Thông tin chi tiết lớp học:</p>
                                        <p class="text-secondary mb-1"><%=item.getDescription() %></p>
                                        <p class="text-primary mb-1">Password</p>
                                        <input type="password" class="form-control" name="password" id="passwordInput"/>
                                        <input type="hidden" name="class_id" value="<%= item.getId() %>">
                                        <input type="checkbox" onclick="myFunction(<%=count%>)"> Show Password


                                    </div>

                                    <!--<p class="text-secondary"><small>If you don't save, your changes will be lost.</small></p>-->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-primary" >Enroll</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                     <% }%>       
                    
                <!-- Ket thuc row thu 2 -->
            </div>
        <%@include file="./Components/Footer.jsp" %>
        <script>
            var x = document.querySelectorAll("#passwordInput");
            function myFunction(checkId) {
                var targetElement = x[checkId - 1];
                if (targetElement.type === "password") {
                    targetElement.type = "text";
                } else {
                    targetElement.type = "password";
                }
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    </body>
</html>
