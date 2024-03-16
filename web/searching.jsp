<%-- 
    Document   : Searching
    Created on : Feb 8, 2024, 11:43:25 AM
    Author     : User
--%>

<%@page import="Model.ClassesDAO"%>
<%@page import="Model.EnrollDAO"%>
<%@page import="Model.ClassesDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Searching <%= request.getAttribute("keyWord")%></title>
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
        <div class="container">

            <%@include file="./Components/Header.jsp" %>

            <%                    List<ClassesDTO> listSearching = (List<ClassesDTO>) request.getAttribute("listSearching");
            %>
            <div class="row mt-3">
                <div class="col-lg-6">
                    <h3 class="fw-medium mb-1">Search results: <%= listSearching.size()%></h3>
                </div>
                <div class="col-lg-6">
                    <form action="searchingclass" id="formSelect">
                        <select class="form-select" aria-label="Sort from A to Z" id="sort-select" name="selectValue"  onchange="selectChanged()">
                            <option value="1" <% if ("1".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from A to Z</option>
                            <option value="2" <% if ("2".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from Z to A</option>
                            <option value="3" <% if ("3".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from newest to oldest</option>
                            <option value="4" <% if ("4".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from oldest to newest</option>
                        </select>
                            <input type="hidden" name="keyWord" value="${requestScope.keyWord}">
                    </form>
                </div>
            </div>



            <div class="row mt-3 mb-4">
                <%
                    if (listSearching != null) {
                        int count = 0;
                        EnrollDAO enrollDAO = new EnrollDAO();
                        for (ClassesDTO items : listSearching) {
                            if (getRole.equals("student") && !enrollDAO.isEnrolledClass(id, items.getId())) {
                                count++;

                %>
                <div class="col-lg-4">
                    <div class="card rounded-4">
                        <img src="<%=items.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="<%= items.getName()%>" style="max-height: 10rem;">
                        <div class="card-body">
                            <h5 class="card-title"><%=items.getName()%></h5>
                            <p class="card-text">Giảng viên: <%= items.getLecturer().getEmail()%></p>
                            <a href="#myModal" role="button" class="btn btn-lg btn-primary" data-bs-toggle="modal" data-class-id = "<%= items.getId()%>" onclick="showPopup(this)">Show more</a>
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
                            <%
                                ClassesDAO classesDAO = new ClassesDAO();
                                if ((getRole.equals("lecturer") && classesDAO.isLectureInClass(id, items.getId())) || getRole.equals("student")) {
                            %>
                            <a href="<%="insideClass?class_id=" + items.getId()%>" class="btn btn-lg btn-primary" >Go to class</a>
                            <%
                                }
                            %>

                        </div>
                    </div>
                </div>
                <% }
                        }
                    }
                %>       

                <!-- Ket thuc row thu 2 -->
            </div>
            <div id="myModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">

                </div>
            </div>
        </div>


        <%@include file="./Components/Footer.jsp" %>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script>
            function selectChanged() {
                var formSelect = document.getElementById("formSelect");
                formSelect.submit();
            }
        </script>
    </body>
</html>
