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
            <%            ClassesDTO getClass = (ClassesDTO) request.getAttribute("classes");
                if (getClass != null) {
            %>
            <div class="bg-body-tertiary pt-3">
                <div class="container">
                    <div class="row">
                        <div>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page"><%=getClass.getName()%> </li>
                                </ol>
                            </nav>
                        </div>
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
                                if (request.getAttribute("listExercise") != null) {
                                    List<ExerciseDTO> list = (List<ExerciseDTO>) request.getAttribute("listExercise");

                                    for (ExerciseDTO exc : list) {%>
                            <a href="<%="exerciseView?exercise_id=" + exc.getExerciseID() + "&class_id=" + exc.getClasses().getId()%>" class="card rounded-4 text-decoration-none my-2" style="min-height: 5rem;">
                                <div class="card-body">
                                    <h5 class="card-title fs-3"> <%= exc.getTitle()%> </h5>
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
                                if (request.getAttribute("listExercise") != null) {
                                    List<ExerciseDTO> list = (List<ExerciseDTO>) request.getAttribute("listExercise");

                                    for (ExerciseDTO exc : list) {%>
                            <a href="answerquestion?class_id=<%= getClass.getId()%>&exercise_id=<%= exc.getExerciseID()%>&action=" class="card rounded-4 text-decoration-none my-2" style="min-height: 5rem;">
                                <div class="card-body">
                                    <h5 class="card-title fs-3"> <%= exc.getTitle()%> </h5>
                                    <span class="badge rounded-pill text-bg-secondary my-1 me-3">From : <%=exc.getCreatedDate()%></span>
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
                                    <a href="#myModal" role="button" class="btn btn-primary d-block w-100 mb-2 mt-2" data-bs-toggle="modal" data-class-id ="${requestScope.classes.id}" data-student-id ="<%= id%>" onclick="showPopup(this)">Xem tổng điểm của tôi</a>
                                    <button class="btn btn-danger d-block w-100">Thoát lớp học</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Modal HTML -->
                    <div id="myModal" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
            <%}
            %>
        </div>
        <%@include file="./Components/Footer.jsp" %>

        <script>
            var selectModal = document.querySelector("div#myModal > .modal-dialog");
            function showPopup(element) {
                selectModal.innerHTML = '<div class="spinner"></div>';
                var classId = element.getAttribute("data-class-id");
                var studentId = element.getAttribute("data-student-id")
                $.ajax({
                    url: "enroll-class",
                    method: "GET",
                    data: {class_id: classId},
                    success: function (data) {
                        // Xử lý dữ liệu nhận được ở đây
                        console.log("Data from json: " + data.name);
                        selectModal.innerHTML = '<div class="modal-content">' +
                                '<div class="modal-header">' +
                                '<h5 class="modal-title">Thông tin chi tiết lớp học ' + data.name + '</h5>' +
                                '<button type="button" class="btn-close" data-bs-dismiss="modal"></button>' +
                                '</div>' +
                                '<form accept-charset="UTF-8" action="enroll-class" method="POST" id="formEnrollClass">' +
                                '<div class="modal-body">' +
                                '<p class="text-primary mb-1">Giảng viên ' + data.lecturer.name + ' </p>' +
                                '<p class="text-primary mb-1">Thông tin chi tiết lớp học:</p>' +
                                '<p class="text-secondary mb-1">' + data.description + '</p>' +
                                '<p class="text-primary mb-1">Password</p>' +
                                '<input type="password" class="form-control" name="password" id="passwordInput"/>' +
                                '<input type="hidden" name="class_id" value="' + data.id + '">' +
                                '<p class="card-text text-danger" id="messageError"></p>' +
                                '<input type="checkbox" onclick="myFunction(0)"> Show Password' +
                                '</div>' +
                                '<div class="modal-footer">' +
                                '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>' +
                                '<button type="submit" class="btn btn-primary">Enroll</button>' +
                                '</div>' +
                                '</form>' +
                                '</div>';
                        var getFormSubmit = document.querySelector("#formEnrollClass");
                        getFormSubmit.addEventListener("submit", (event) => {
                            console.log(event);
                            event.preventDefault();
                            getDataFormEvent = {
                                password: event.srcElement[0].value,
                                class_id: event.srcElement[1].value
                            };

                            $.ajax({
                                url: "enroll-class",
                                method: "POST",
                                data: {
                                    class_id: getDataFormEvent.class_id,
                                    password: getDataFormEvent.password
                                }, success: function (msg) {
                                    var redirectUrl = msg;
                                    console.log(msg);
                                    window.location.href = redirectUrl;
                                }, error: function (xhr, status, error) {
                                    $("#messageError").html("Wrong password, please try another password!");
                                }
                            })
                        });
                    },
                    error: function (xhr, status, error) {
                        // Xử lý lỗi nếu có
                        console.log("Lỗi: " + error);
                    }
                });

            }
        </script>
    </body>

</html>
