<%-- 
    Document   : insideClass
    Created on : Feb 18, 2024, 11:48:41 AM
    Author     : ACER
--%>


<%@page import="Model.ExerciseDTO"%>
<%@page import="java.util.List"%>
<%@page import="Model.ClassesDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Inside Class</title>
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
        <div class="container" id="headerContainer">
        <%@include file="./Components/Header.jsp" %>
        <%            ClassesDTO getClass = (ClassesDTO) request.getAttribute("class");
            if (getClass != null) {
        %>
        <div class="bg-body-tertiary pt-3">
            <div class="container">
                <div class="row">
                    <div class="py-4 bg-image-full rounded-4" style="background-image: url('<%=getClass.getThumbnail()%>')">
                        <div class="text-center">
                            <img class="img-fluid rounded-circle mb-3" src="<%=getClass.getLecturer().getThumbnail()%>" alt="ảnh đại diện của <%=getClass.getLecturer().getName()%>"
                                 style="height: 6rem;" />
                            <h1 class="text-white fs-3 fw-bolder"><%=getClass.getName()%></h1>
                            <p class="text-white fw-medium mb-0"><%=getClass.getLecturer().getName()%></p>
                        </div>
                    </div>
                </div>

                <%  if (getRole != null && getRole.equals("lecturer")) {%>
                <div class="row mt-3">
                <a href="createQuestion.jsp?class_id=<%=getClass.getId()%>" class="btn btn-primary rounded-4 fw-bold px-5">Tao cau hoi</a>
                </div>

                <div class="row my-3">
                    <!-- Cac cau hoi trong lop -->
                    <div class="col-lg-8">

                        <% 
                            if(request.getAttribute("listExcercise") != null) {
                                List<ExerciseDTO> list = (List<ExerciseDTO>)request.getAttribute("listExcercise");
                            
                            for (ExerciseDTO exc : list) { %>
                                <a href="#" class="card rounded-4 text-decoration-none my-2" style="min-height: 5rem;">
                                    <div class="card-body">
                                        <h5 class="card-title fs-3"> <%= exc.getTitle() %> </h5>
                                        <span class="badge rounded-pill text-bg-secondary my-1 me-3">From : 5:30 19/09/2023</span>
                                        <span class="badge rounded-pill text-bg-secondary my-1">To : 5:30 20/09/2023</span>
                                    </div>
                                </a>
                            <%   }  %>
                        
                        <% } %>
                    </div>
                    
                    <div class="col-lg-4">
                        <div class="card mt-2 rounded-4">
                            <div class="card-body" style="min-height: 20rem;"> 
                                <h5 class="card-title fs-3 fw-bolder">Thông tin lớp học</h5>
                                <a class="text-decoration-none text-dark fs-5 fw-medium" href="#">Thành viên lớp học: 53 người</a>
                                <button class="btn btn-primary d-block w-100 mb-2 mt-2">Xem tổng điểm của học sinh</button>
                                <button class="btn btn-danger d-block w-100">Xoá lớp học</button>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <% } else { %>
                <div class="row my-3">
                    <!-- Cac cau hoi trong lop -->
                    <div class="col-lg-8">
                       

                        <% 
                            if(request.getAttribute("listExcercise") != null) {
                                List<ExerciseDTO> list = (List<ExerciseDTO>)request.getAttribute("listExcercise");
                            
                            for (ExerciseDTO exc : list) { %>
                                <a href="answerquestion?class_id=<%= getClass.getId() %>&exercise_id=<%= exc.getExcerciseID()%>&action=" class="card rounded-4 text-decoration-none my-2" style="min-height: 5rem;">
                                    <div class="card-body">
                                        <h5 class="card-title fs-3"> <%= exc.getTitle() %> </h5>
                                        <span class="badge rounded-pill text-bg-secondary my-1 me-3">From : <%=exc.getCreatedDate() %></span>
                                        <span class="badge rounded-pill text-bg-secondary my-1">To : 5:30 20/09/2023</span>
                                    </div>
                                </a>
                            <%   }  
                         } %>
                        
                    </div>
                    <!-- Ket thuc cua cau hoi trong lop hoc -->

                    <div class="col-lg-4">
                        <div class="card mt-2 rounded-4">
                            <div class="card-body" style="min-height: 20rem;"> 
                                <h5 class="card-title fs-3 fw-bolder">Thông tin lớp học</h5>
                                <a class="text-decoration-none text-dark fs-5 fw-medium" href="#">Thành viên lớp học: 53 người</a>
                                <button class="btn btn-primary d-block w-100 mb-2 mt-2">Xem tổng điểm của tôi</button>
                                <button class="btn btn-danger d-block w-100">Thoát lớp học</button>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
                            <%} %>
                            
        <%@include file="./Components/Footer.jsp" %>
        

    </body>

</html>
