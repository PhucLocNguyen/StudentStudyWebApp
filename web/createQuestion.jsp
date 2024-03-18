<%-- 
    Document   : createQuestion
    Created on : Feb 27, 2024, 8:40:24 PM
    Author     : HA GIA KHANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inside create question</title>
        <link rel="stylesheet" href="./Assets/css/style.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/froala_editor/froala_editor.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <link rel="stylesheet" href="./css/froala_editor/plugins/table.min.css"/>
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./js/froala_editor/froala_editor.pkgd.min.js"></script>
        <script src="./js/froala_editor/plugins/image.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="./Assets/themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
</head>

<body>

    <div class="container">
        <div class="row mt-2">
            <%@include file="./Components/Header.jsp" %>

            <!-- Bat dau tao lop hoc -->
            <%                String classId = request.getParameter("class_id");
                String action = request.getParameter("action");

            %>
            <!-- Ket thuc tao lop hoc -->

            <div class="bg-body-tertiary pt-3">
                <div class="container">
                    <div class="row">
                        <div class="card rounded-4">
                            <form accept-charset="UTF-8" action="createQuestion" method="post" enctype="multipart/form-data">
                                <div class="card-body">
                                    <h1 class="card-title fw-bolder fs-2">${not empty requestScope.nextaction?"Update question":"Create Question"}</h1>

                                    <label class="form-label fs-5 fw-medium">Title</label>
                                    <input type="text" class="form-control" placeholder="Enter the title" name="title" value="${requestScope.title}">
                                    <div>
                                        <p class="form-label fs-5 fw-medium">description</p>
                                        <textarea id="froala-editor" class="form-control" rows="3" 
                                                  placeholder="Enter description" name="description">${requestScope.description}</textarea>
                                    </div>

                                    <div>
                                        <p class="form-label fs-5 fw-medium">File attachment</p>
                                        <input type="file" class="form-control" id="inputGroupFile01" name="thumbnail">
                                    </div>
                                    <input type="hidden" name="classId" value="<%=classId%>">
                                    <input type="hidden" name="action" value="${not empty requestScope.nextaction?"update":"create"}">
                                    <input type="hidden" name="exercise_id" value="${requestScope.exercise_id}">
                                    <label class="form-label fs-5 fw-medium">Status: </label>
                                    <select id="status_option" class="form-label fs-5 fw-medium" name="status" style="margin-top: 8px;" onchange="changeOption()">
                                        <option ${requestScope.status eq "active"?'selected':''} value="active" selected >Active</option>
                                        <option ${requestScope.status eq "pending"?'selected':''} value="pending">Pending</option>
                                        <!--<option value="inactive">Inactive</option>-->
                                    </select>
                                    </br>
                                    <div id="time_container" class="">
                                        <label class="form-label fs-5 fw-medium">Start date: </label>
                                        <input id="time-input" name="start_time" type="datetime-local" class="form-control" value="${requestScope.start_date}">

                                        <label class="form-label fs-5 fw-medium">Close date:</label>
                                        <input id="time-input" name="end_time" type="datetime-local" class="form-control" value="${requestScope.end_date}">
                                        </br><h4 style="color: red;"> ${requestScope.timeerror} </h4>
                                        </br><h4 style="color: red;"> ${requestScope.error} </h4>
                                    </div>
                                    <input class="btn btn-primary my-3" type="submit" value="SAVE">

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="./Components/Footer.jsp" %>

        </div>
    </div>            
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>

    <script>
                                        let editor = new FroalaEditor('#froala-editor', {
                                            // Set the image upload URL.
                                            entities: '',
                                            imageUploadURL: 'upload-image',

                                            imageUploadParams: {
                                                id: 'my_editor'
                                            }
                                        })

                                        const getStatus = document.getElementById("status_option");
                                        var value = getStatus.value;
                                        var container = document.getElementById("time_container");
                                        if (value === "pending") {
                                            container.classList.add("d-none");
                                        } else {
                                            container.classList.remove("d-none");
                                        }
                                        function changeOption() {
                                            value = getStatus.value;
                                        }
    </script>
</body>
</html>
