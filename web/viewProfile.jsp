<%-- 
    Document   : viewProfile
    Created on : Feb 27, 2024, 10:11:39 PM
    Author     : hn190
--%>

<%@page import="Model.EnrollDAO"%>
<%@page import="Model.ClassesDTO"%>
<%@page import="Model.EnrollDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Profile</title>
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="./Components/Header.jsp" %>



        <div class="container">

            <div class="row mt-3">
                <div class="col-lg-12">
                    <img src="${sessionScope.user.getThumbnail()}" class="rounded-circle d-block mx-auto" alt="..."
                         style="width: 12rem; height: 12rem; object-fit: cover;">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 my-auto text-center">
                    <h1>${sessionScope.user.getName()}</h1>
                </div>
            </div>



            <div class="row mt-3">

                <div class="col-lg-8">

                    <%                        int size_class_enroll = 0;
                        if (request.getAttribute("action") != null) {
                            String action = (String) request.getAttribute("action");
                            if (action.equals("showmyprofile")) {
                                if (session.getAttribute("role").equals("student")) {

                    %>

                    <%                        if (request.getAttribute("list") != null) {
                            List<EnrollDTO> list = (List<EnrollDTO>) request.getAttribute("list");
                            size_class_enroll = list.size();
                            for (EnrollDTO item : list) {
                    %> 
                    <a href="insideClass?class_id=<%= item.getClasses().getId()%>" class="card rounded-4 text-decoration-none my-2" style="min-height: 5rem;">
                        <div class="card-body">
                            <h5 class="card-title fs-3"><%= item.getClasses().getName()%></h5>
                            <span class="badge rounded-pill text-bg-secondary my-1 me-3">Enroll date : <%= item.getEnroll_date()%></span>
                            <span class="badge rounded-pill text-bg-secondary my-1 me-3">Ten giang vien : <%= item.getClasses().getLecturer().getName()%></span>
                        </div>
                    </a>
                    <%
                                }
                            }
                        }
                        if (session.getAttribute("role").equals("lecturer")) {
                    %>

                    <%
                        if (request.getAttribute("list") != null) {
                            List<ClassesDTO> list = (List<ClassesDTO>) request.getAttribute("list");
                            size_class_enroll = list.size();
                            for (ClassesDTO item : list) {
                    %>            

                    <div class="col-lg-12 mt-4">
                        <div class="card rounded-4">

                            <div class="card-body">
                                <h5 class="card-title"><%=item.getName()%></h5>
                                <p class="card-text">Giảng viên: <%= item.getLecturer().getEmail()%></p>
                                <a href="<%="insideClass?class_id=" + item.getId()%>" class="btn btn-lg btn-primary" >Go to class</a>
                            </div>
                        </div>
                    </div>

                    <%
                            }
                        }
                    %>



                    <%
                        }

                    } else if (action.equals("showprofile")) {
                    %>

                    <%
                        EnrollDAO enrollDAO = new EnrollDAO();

                        if (request.getAttribute("list") != null) {
                            List<EnrollDTO> list = (List<EnrollDTO>) request.getAttribute("list");
                            size_class_enroll = list.size();
                            for (EnrollDTO item : list) {

                                if (getRole.equals("student") && !enrollDAO.isEnrolledClass(id, item.getClasses().getId())) {
                    %>

                    <div class="card rounded-4 mt-2">
                        <div class="card-body">
                            <h5 class="card-title fs-3"><%= item.getClasses().getName()%></h5>
                            <span class="badge rounded-pill text-bg-secondary my-1 me-3">Enroll date : <%= item.getEnroll_date()%></span>
                            <span class="badge rounded-pill text-bg-secondary my-1 me-3">Ten giang vien : <%= item.getClasses().getLecturer().getName()%></span>
                            <a href="#myModal" role="button" class="btn btn-lg btn-primary" data-bs-toggle="modal" data-class-id = "<%= item.getClasses().getId()%>" onclick="showPopup(this)">Show more</a>
                        </div>
                    </div>

                    <%} else {%>

                    <div class="card rounded-4 mt-2">
                        <div class="card-body">
                            <h5 class="card-title fs-3"><%= item.getClasses().getName()%></h5>
                            <span class="badge rounded-pill text-bg-secondary my-1 me-3">Enroll date : <%= item.getEnroll_date()%></span>
                            <span class="badge rounded-pill text-bg-secondary my-1 me-3">Ten giang vien : <%= item.getClasses().getLecturer().getName()%></span>
                            <a href="<%="insideClass?class_id=" + item.getClasses().getId()%>" class="btn btn-lg btn-primary" >Go to class</a>
                        </div>
                    </div> 

                    <%}%>

                    <%
                            }
                        }
                    %>


                    <%
                            }
                        }
                    %>    


                </div>

                <%
                    if (session.getAttribute("role").equals("student")) {
                %>

                <div class="col-lg-4">
                    <div class="card rounded-4 my-2">
                        <div class="card-body fw-medium fs-3">
                            Số lượng khóa học : <%= size_class_enroll%>
                        </div>
                    </div>
                </div>

                <%
                } else if (session.getAttribute("role").equals("lecturer")) {
                %>

                <div class="col-lg-4 mt-3">
                    <div class="card rounded-4 my-2">
                        <div class="card-body fw-medium fs-3">
                            Số lượng lớp học : <%= size_class_enroll%>
                        </div>
                    </div>
                </div>

                <%
                    }
                %>

            </div>

            <div id="myModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">

                </div>
            </div>
        </div>

        <script>
            function myFunction() {
                var x = document.querySelector("#passwordInput");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
            }
        </script>
        <script>
            var selectModal = document.querySelector("div#myModal > .modal-dialog");
            function showPopup(element) {
                selectModal.innerHTML = '<div class="spinner"></div>';
                var classId = element.getAttribute("data-class-id");
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
