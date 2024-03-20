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
                        <tr><th>Index</th><th>Image</th><th>Email</th><th>Student Code</th><th>Fullname</th><th>Enroll Date</th><th>Show score</th></tr>
                    </thead>
                    <tbody>
                        <% if (request.getAttribute("listStudent") != null) {
                                int count = 0;
                                List<EnrollDTO> enrollList = (List<EnrollDTO>) request.getAttribute("listStudent");
                                for (EnrollDTO enroll : enrollList) {
                                    count++;
                        %>
                        <tr><td><%=count%></td>
                            <td>
                    <center><img src="<%= enroll.getStudent().getThumbnail()%>" style="height:146px;width:111px;border-width:0px;"></center>
                    </td>
                    <td><%= enroll.getStudent().getEmail()%></td>
                    <td><%=enroll.getStudent().getId()%></td>
                    <td><%=enroll.getStudent().getName()%></td>
                    <td><%=enroll.getEnroll_date()%></td>
                    <td><a href="#myModal" role="button" class="btn btn-primary btn btn-primary d-block w-100" data-bs-toggle="modal" student-id="<%=enroll.getStudent().getId()%>" data-class-id="<%=enroll.getClasses().getId()%>" data-action="score" onclick="showPopup(this)">Show score</a></td>
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
        <script>
            var selectModal = document.querySelector("div#myModal > .modal-dialog");
            function showPopup(element) {
                selectModal.innerHTML = '<div class="spinner"></div>';
                var classId = element.getAttribute("data-class-id");
                var action = element.getAttribute("data-action");
                var student_id = element.getAttribute("student-id");
                $.ajax({
                    url: "answerquestion",
                    method: "GET",
                    data: {class_id: classId, student_id: student_id, action: action},
                    success: function (data) {
                        // Xử lý dữ liệu nhận được ở đây
                        if (data.length > 0) {
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
                                        '<td class="answerFormat">' + item.solution + '</td>' +
                                        '<td>' + status + '</td>' +
                                        '<td>' + item.score + '</td>' +
                                        '</tr>';
                            });
                            tableHTML += '<tr style="color: red;font-size: 20px"><td colspan="3" style="text-align: center">Average Score:</td><td>' + average / data.length + '</td></tr></table></div>';
                            selectModal.innerHTML = tableHTML;
                        }
                    },
                    error: function (xhr, status, error) {
                        // Xử lý lỗi nếu có
                        let tableHTML = '<div class="modal-content" style="min-height: 350px;">' +
                                '<div class="modal-header">' +
                                '<h5 class="modal-title">Score: </h5>' +
                                '<button type="button" class="btn-close" data-bs-dismiss="modal"></button>' +
                                '</div><p class="text-center fs-3 mt-3">There are currently no posts to display</p></div>';
                        selectModal.innerHTML = tableHTML;
                    }
                });

            }
        </script>
    </body>
</html>
