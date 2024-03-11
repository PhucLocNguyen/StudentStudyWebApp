<%-- 
    Document   : teacherViewQuestion.jsp
    Created on : Feb 27, 2024, 7:39:33 PM
    Author     : ACER
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.DoDTO"%>
<%@page import="Model.ExerciseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>View Question for teacher</title>
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
            <%                 int sizeDone = 0, sizeNot = 0;
                ExerciseDTO exercise = (ExerciseDTO) request.getAttribute("question");
                List<DoDTO> listDoAnswer = (List<DoDTO>) request.getAttribute("listAnswered");
                sizeDone = listDoAnswer.size();
                List<StudentDTO> listNotAnswer = (List<StudentDTO>) request.getAttribute("listDidNotAnswer");
                sizeNot = listNotAnswer.size();
            %>
            <div class="bg-body-tertiary pt-3">
                <div class="container">
                    <div class="row">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <li class="breadcrumb-item"><a href="insideClass?class_id=<%=request.getParameter("class_id")%>">Classroom</a></li>
                                <li class="breadcrumb-item active" aria-current="page"><%=exercise.getTitle()%></li>
                            </ol>
                        </nav>
                        <div class="py-4 bg-image-full rounded-4" style="background-image: url('./img/background.jpg')">
                            <div class="text-center">
                                <h1 class="text-white fs-3 fw-bolder"><%=exercise.getTitle()%></h1>
                                <h5 class="text-white fw-medium mb-0"><%=exercise.getDescription()%></h5>
                            </div>
                        </div>
                    </div>

                    <div class="row my-3">
                        <!-- Cac cau hoi trong lop -->
                        <div class="col-lg-8 card mt-2 rounded-2" id="listStudentDone">
                            <h5 class="card-title fs-3 fw-bolder pt-3">Danh sách sinh viên đã nộp bài <span class=" fs-4 text-success">(<%=sizeDone%>)</span></h5>
                            <%
                                for (DoDTO elem : listDoAnswer) {
                                    if (elem != null) {
                            %>
                            <a href="#myModal" class="card rounded-4 text-decoration-none my-2 answerSubmitted" id="student<%=elem.getStudent().getId()%>" style="min-height: 5rem;" data-bs-toggle="modal" exercise-id="<%=elem.getExercise().getExerciseID()%>" student-id="<%= elem.getStudent().getId()%>">
                                <div class="card-body row align-content-center justify-content-center">
                                    <div class="col-lg-9">
                                        <h5 class="card-title fs-3"><%=elem.getStudent().getName()%></h5>
                                        <span class="badge rounded-pill text-bg-secondary my-1 me-3"><%=elem.getCreateDate()%></span>
                                    </div>  
                                    <div class="col-lg-3 row flex-column align-content-center justify-content-center">
                                        <% if (elem.isIsGrade()) {%>

                                        <img src="./Assets/img/checked.png" alt="Grade successful" class="statusGradeMark">
                                        <p class="mb-0 badge rounded-pill text-bg-secondary my-2">Đã chấm <%=elem.getScore()%> /10 điểm</p>
                                        <%} else {
                                        %><img src="./Assets/img/wait.png" alt="waitting for grade" class="statusGradeMark">
                                        <p class="mb-0 badge rounded-pill text-bg-secondary my-2">Đang chờ để chấm điểm</p>
                                        <%} %>
                                    </div>
                                </div>

                            </a>
                            <%
                                    }
                                }

                            %>
                        </div>
                        <!-- Ket thuc cua cau hoi trong lop hoc -->

                        <div class="col-lg-4" id="studentNoSubmit">
                            <div class="card mt-2 rounded-4">
                                <div class="card-body" style="min-height: 20rem;"> 
                                    <h5 class="card-title fs-4 fw-bolder">Sinh viên chưa trả lời câu hỏi <span class=" fs-4 text-danger">(<%=sizeNot%>)</span> </h5>
                                    <%for (StudentDTO items : listNotAnswer) {%>
                                    <a class=" row d-flex align-content-center justify-content-center text-decoration-none text-dark fs-5 fw-medium" href="#">
                                        <div class="col-lg-2">
                                            <img src="./Assets/img/<%=items.getThumbnail()%>" alt="" style="width: 100%;" class="shadow border border-danger rounded-circle">
                                        </div>
                                        <div class="col-lg-10 d-flex align-content-center flex-wrap">
                                            <h5 class="card-title fs-4 mb-0"><%=items.getName()%></h5>
                                        </div>

                                    </a>
                                    <%}%>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div id="myModal" class="modal fade" tabindex="-1">
                    <div class="modal-dialog" style="max-width: 95%;">
                        <div class="modal-content">
                            <form action="do" method="POST">
                                <div class="modal-header">
                                    <h5 class="modal-title">Chấm điểm cho sinh viên</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>
                                <div class="modal-body row">
                                    <div class="col-lg-8 card mt-2 rounded-2">
                                        <div class="row d-flex align-content-center justify-content-center">
                                            <div class="col-lg-9">
                                                <h5 class="card-title fs-3 fw-bolder pt-3" id="gradeStudent"></h5>
                                                <div class="d-flex px-2">
                                                    <span class="badge rounded-pill bg-success text-bg-secondary my-1 me-3 fs-6" id="timeSubmit"></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 flex-column align-content-center justify-content-center flex-wrap text-bg-primary rounded-3">
                                                <h4  class="card-title fs-3 fw-bolder pt-3 text-center mb-0">Điểm</h4>
                                                <h5 class="card-title fs-4 fw-bolder pt-0 text-center" id="point"></h5>
                                            </div>
                                        </div>

                                        <div class="card-body row">
                                            <div class="col-lg-12">
                                                <p class="card-title text-black fs-5" id="solution"></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4" id="formNhapDiem">
                                        <div class="card mt-2 rounded-4">
                                            <div class="card-body" style="min-height: 20rem;"> 
                                                <h5 class="card-title fs-4 fw-bolder">Nhập điểm cho sinh viên <span class=" fs-4 text-danger">(*)</span> </h5>
                                                <a class="text-decoration-none text-dark fs-5 fw-medium" href="#"></a>
                                                <div class="row d-flex align-content-center justify-content-center">
                                                    <div class="col-lg-12 d-flex align-content-center justify-content-center mb-2" id="imageGrade">
                                                    </div>

                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <input type="number" step="0.1" class="form-control mt-2" id="scoreInput" name="score" onchange="checkScoreValid(this)" placeholder="Nhập điểm tại đây..." required>
                                                        <p class="mb-0 text-danger" id="gradeError"></p>
                                                        <input type="hidden" name="action" value="grade" id="actionDo">
                                                        <input type="hidden" name="class_id" value="<%=request.getParameter("class_id")%>">
                                                        <input type="hidden" name="student_id" value="" id="student_id_field">
                                                        <input type="hidden" name="exercise_id" value="" id="exercise_id_field">
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <%@include file="./Components/Footer.jsp" %>
    </body>
    <script>
        var getFieldDone = document.querySelector("#listStudentDone");
        var getStudentNoSubmit = document.querySelector("#studentNoSubmit");
        var getListAnswer = '#listStudentDone .answerSubmitted';
        $(getListAnswer).on('click', function () {
            $(getListAnswer).removeClass('activeBox');
            $(this).addClass('activeBox');
            showAnswer(this);
        });
        
        function showAnswer(events) {
            var exercise_id = events.getAttribute("exercise-id");
            var student_id = events.getAttribute("student-id");
            
            $.ajax({
                url: "http://localhost:8080/LoginGoogle/do",
                method: "GET",
                data: {
                    exercise_id: exercise_id,
                    student_id: student_id
                },
                success: function (data) {
                    if (data.is_grade) {
                        $("#actionDo").val("edit_score");
                    } else {
                        $("#actionDo").val("grade");
                    }
                    $("#gradeStudent").text(data.student.name);
                    $("#timeSubmit").text("Đã nộp vào lúc " + data.createDate);
                    $("#point").text(data.score + " /10");
                    $("#solution").html(data.solution);
                    $("#imageGrade").html('<img src="' + data.student.thumbnail + '" alt="' + "logo của sinh viên " + data.student.name + '"  class="img-responsive shadow border border-danger rounded-circle w-60" id="imageGrade">');
                    $("#student_id_field").val(data.student.id);
                    $("#exercise_id_field").val(data.exercise.exerciseID);
                    $("#scoreInput").val(data.score);
                }, error: function (xhr, status, error) {
                    console.log(error);
                }
            })
        }
        var getErrMsg = document.querySelector("#gradeError");
        function checkScoreValid(item) {
            let scoreValue = item.value;
            if (scoreValue < 0 || scoreValue > 10) {
                getErrMsg.innerHTML = "Vui lòng nhập số điểm từ 0-10";
                item.value = 0;
            }
            
        }
    </script>
</html>
