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
    <style>
        table {
            border-collapse: collapse; /* Loại bỏ khoảng cách giữa các ô */
            width: 100%; /* Chiếm toàn bộ chiều rộng của phần tử cha */
        }

        th, td {
            border: 1px solid black; /* Đường kẻ 1px đen */
            padding: 8px; /* Khoảng cách giữa nội dung và đường kẻ */
            width: auto;
        }
    </style>
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
                            <a href="exerciseView?exercise_id=<%=exc.getExerciseID()%>&class_id=<%=exc.getClasses().getId()%>" class="card rounded-4 text-decoration-none my-2" style="min-height: 5rem;">
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
                    <div style="display: flex; justify-content: center">
                        <nav aria-label="...">
                            <ul class="pagination pagination-sm">
                                <%
                                    int totalPage = (int) request.getAttribute("page");
                                    for (int i = 1; i <= totalPage; i++) {
                                %>
                                <li class="page-item ">
                                    <a class="page-link" href="insideClass?class_id=<%= getClass.getId()%>&page=<%=i%>" tabindex="-1"><%= i%></a>
                                </li>
                                <%
                                    }
                                %>
                            </ul>
                        </nav>
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
                                }%>
                            

                        </div>
                        <!-- Ket thuc cua cau hoi trong lop hoc -->

                        <div class="col-lg-4">
                            <div class="card mt-2 rounded-4">
                                <div class="card-body" style="min-height: 20rem;"> 

                                    <h5 class="card-title fs-3 fw-bolder">Thông tin lớp học</h5>
                                    <a class="text-decoration-none text-dark fs-5 fw-medium" href="#">Thành viên lớp học: 53 người</a>
                                    <a href="#myModal" role="button" class="btn btn-primary d-block w-100 mb-2 mt-2" data-bs-toggle="modal" data-class-id ="<%= getClass.getId()%>" data-action="score" onclick="showPopup(this)">Xem tổng điểm của tôi</a>
                                    <button class="btn btn-danger d-block w-100">Thoát lớp học</button>
                                </div>
                            </div>
                        </div>
                                    <div style="display: flex; justify-content: center">
                                <nav aria-label="...">
                                    <ul class="pagination pagination-sm">
                                        <%
                                            int totalPage = (int) request.getAttribute("page");
                                            for (int i = 1; i <= totalPage; i++) {
                                        %>
                                        <li class="page-item ">
                                            <a class="page-link" href="insideClass?class_id=<%= getClass.getId()%>&page=<%=i%>" tabindex="-1"><%= i%></a>
                                        </li>
                                        <%
                                            }
                                        %>
                                    </ul>
                                </nav>
                            </div>
                    </div>
                                    

                    <!-- Modal HTML -->
                    <div id="myModal" class="modal fade" tabindex="-1">
                        <div class="modal-dialog" style="min-width: 90%">

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
                var action = element.getAttribute("data-action");
                $.ajax({
                    url: "answerquestion",
                    method: "GET",
                    data: {class_id: classId, action: action},
                    success: function (data) {
                        // Xử lý dữ liệu nhận được ở đây
                        console.log(data[0].student.name);
                        let tableHTML = '<div class="modal-content">' +
                                '<div class="modal-header">' +
                                '<h5 class="modal-title">Score: ' + data[0].student.name + '</h5>' +
                                '<button type="button" class="btn-close" data-bs-dismiss="modal"></button>' +
                                '</div>' + '<table style="width: 100%"><tr><td>Title</td><td>Answer</td><td>Status</td><td>Score</td></tr>';
                        var average = 0;
                        data.forEach(function (item) {
                            let status;
                            if (item.solution === '') {
                                status = 'Not answer';
                            } else {
                                status = item.isGrade ? 'Graded' : 'Not graded';
                            }
                            average += item.score;
                            tableHTML
                                    += '<tr>' +
                                    '<td>' + item.exercise.title + '</td>' +
                                    '<td>' + item.solution + '</td>' +
                                    '<td>' + status + '</td>' +
                                    '<td>' + item.score + '</td>' +
                                    '</tr>';
                        });
                        tableHTML += '<tr style="color: red;font-size: 20px"><td colspan="3" style="text-align: center">Average Score:</td><td>' + average / data.length + '</td></tr></table></div>';
                        selectModal.innerHTML = tableHTML;
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
