<%-- 
    Document   : Home
    Created on : Jan 21, 2024, 11:34:32 AM
    Author     : ACER
--%>

<%@page import="Model.EnrollDAO"%>
<%@page import="Model.ClassesDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>
    <%@include file="./Components/Header.jsp" %>
    <body>
        <div class="bg-body-tertiary" >
            <div id="carouselExampleInterval" class="carousel slide container mb-2 mt-2" data-bs-ride="carousel">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0"
                                    class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                                    aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                                    aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner " style="height: 270px;">
                            <div class="carousel-item active rounded-4" data-bs-interval="10000">
                                <img src="./Assets/img/slide.jpg" class="d-block w-100 rounded-4 img-fluid" alt="...">
                            </div>
                            <div class="carousel-item" data-bs-interval="10000">
                                <img src="./Assets/img/slide.jpg" class="d-block w-100 rounded-4 img-fluid" alt="...">
                            </div>
                            <div class="carousel-item" data-bs-interval="10000">
                                <img src="./Assets/img/slide.jpg" class="d-block w-100 rounded-4 img-fluid" alt="...">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval"
                                data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval"
                                data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
            <!-- Ket thuc slide -->


            <div class="container mt-5">

                <div class="row">
                    <h3 class="fw-medium mb-1">Kham pha cac lop hoc</h3>
                </div>
                <div class="row mt-3 mb-4">
                    <% List<ClassesDTO> list = (List<ClassesDTO>) request.getAttribute("class_list");
                        if (list != null) {
                            int count = 0;
                            EnrollDAO enrollDAO = new EnrollDAO();
                            for (ClassesDTO items : list) {
                                if (getRole.equals("student") && !enrollDAO.isEnrolledClass(id, items.getId())) {
                                    count++;

                    %>
                    <div class="col-lg-4">
                        <div class="card rounded-4">
                            <img src="<%=items.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="<%= items.getName()%>" style="max-height: 10rem;">
                            <div class="card-body">
                                <h5 class="card-title"><%=items.getName()%></h5>
                                <p class="card-text">Giảng viên: <%= items.getLecturer().getEmail()%></p>
                                <a href="<%="#myModal" + count%>" role="button" class="btn btn-lg btn-primary" data-bs-toggle="modal">Show more</a>
                            </div>
                        </div>
                    </div>
                    <div id="<%="myModal" + count%>" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title"><%=items.getName()%></h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>

                                <form accept-charset="UTF-8" action="enroll-class" method="POST">

                                    <div class="modal-body">

                                        <p class="text-primary mb-1">Giảng viên <%= items.getLecturer().getName()%></p>

                                        <p class="text-primary mb-1">Thông tin chi tiết lớp học:</p>
                                        <p class="text-secondary mb-1"><%=items.getDescription()%></p>
                                        <p class="text-primary mb-1">Password</p>
                                        <input type="password" class="form-control passwordInput" name="password" />
                                        <input type="hidden" name="class_id" value="<%= items.getId()%>">
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
                    <%} else {%>
                    <div class="col-lg-4">
                        <div class="card rounded-4">
                            <img src="<%=items.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="<%= items.getName()%>" style="max-height: 10rem;">
                            <div class="card-body">
                                <h5 class="card-title"><%=items.getName()%></h5>
                                <p class="card-text">Giảng viên: <%= items.getLecturer().getEmail()%></p>
                                <a href="<%="#showclass=" + items.getId()%>" class="btn btn-lg btn-primary" >Go to class</a>
                            </div>
                        </div>
                    </div>

                    <%}
                            }
                        }%>
                </div>
                <!-- Ket thuc kham pha hoc phan -->

                <div class="row">
                    <h3 class="fw-medium mb-3">Lop hoc cua ban</h3>
                </div>
                <div class="row">
                    <%
                        List<ClassesDTO> listClassEnrolled = (List<ClassesDTO>) request.getAttribute("classListEnrolled");
                        if (listClassEnrolled.size() > 0) {
                            for (ClassesDTO item : listClassEnrolled) {


                    %>
                    <div class="col-lg-3">
                        <div class="card">
                            <img src="<%=item.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="<%= item.getName()%>" style="max-height: 10rem;">
                            <div class="card-body">
                                <h5 class="card-title"><%= item.getName()%></h5>
                                <p class="card-text">Giảng viên: <%= item.getLecturer().getEmail()%></p>
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                    <!--                    <div class="col-lg-3">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">Ten lop hoc</h5>
                                                    <p class="card-text">Fpt, VietNam</p>
                                                    <span class="badge rounded-pill text-bg-secondary">19 cau hoi</span>
                                                    <span class="badge rounded-pill text-bg-secondary">2 thanh vien</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">Ten lop hoc</h5>
                                                    <p class="card-text">Fpt, VietNam</p>
                                                    <span class="badge rounded-pill text-bg-secondary">19 cau hoi</span>
                                                    <span class="badge rounded-pill text-bg-secondary">2 thanh vien</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">Ten lop hoc</h5>
                                                    <p class="card-text">Fpt, VietNam</p>
                                                    <span class="badge rounded-pill text-bg-secondary">19 cau hoi</span>
                                                    <span class="badge rounded-pill text-bg-secondary">2 thanh vien</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                    
                                </div>-->

                </div>
                <script>
                    var x = document.querySelectorAll(".passwordInput");
                    function myFunction(checkId) {
                        var targetElement = x[checkId - 1];
                        if (targetElement.type === "password") {
                            targetElement.type = "text";
                        } else {
                            targetElement.type = "password";
                        }
                    }
                </script>
                <script>
                    function showPopup(courseId) {
                        $.post("popup-class", {
                            id: courseId
                        }, function (data, status) {
                            alert("Data: " + data + "\nStatus: " + status);
                        });
                    }
                </script>
                <%@include file="./Components/Footer.jsp" %>
                </body>
                </html>
