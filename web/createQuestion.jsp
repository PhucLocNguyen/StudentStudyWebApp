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
        <link rel="stylesheet" href="./Assets/css/style.css">
        <link rel="stylesheet" href="./Assets/themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/froala_editor/froala_editor.css"/>
        <script src="./js/froala_editor/froala_editor.pkgd.min.js"></script>
        <script src="./js/froala_editor/plugins/image.min.js"></script>
        <link rel="stylesheet" href="./Assets/froala_editor/css/plugins/table.min.css">
        <!-- Include the Tables plugin JS file -->
        <script src="./js/froala_editor/plugins/table.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
            <%                    
                String classId = request.getParameter("class_id");
            %>
            <!-- Ket thuc tao lop hoc -->

            <div class="bg-body-tertiary pt-3">
                <div class="container">
                    <div class="row">
                        <div class="card rounded-4">
                            <form accept-charset="UTF-8" action="createQuestion" method="post" enctype="multipart/form-data">
                                <div class="card-body">
                                    <h1 class="card-title fw-bolder fs-2">${not empty requestScope.nextaction?"Update question":"Create Question"}</h1>
                                    <label class="form-label fs-5 fw-medium">Tieu de cau hoi</label>
                                    <input type="text" class="form-control" placeholder="Nhap ten cua lop hoc" name="title" value="${requestScope.title}">
                                    <div>
                                        <p class="form-label fs-5 fw-medium">Mo ta</p>
                                        <textarea id="froala-editor" class="form-control" rows="3" 
                                                  placeholder="Them mo ta cho lop hoc cua ban" name="description">${requestScope.description}</textarea>
                                    </div>

                                    <div>
                                        <p class="form-label fs-5 fw-medium">Tệp đính kèm</p>
                                        <input type="file" class="form-control" id="inputGroupFile01" name="thumbnail" value="${requestScope.thumbnail}">
                                    </div>

                                    <!--                                        <label class="form-label fs-5 fw-medium">Tệp đính kèm</label>
                                                                            <input type="file" class="form-control" id="inputGroupFile01" name="thumbnail">-->

                                    <input type="hidden" name="classId" value="<%=classId%>">
                                    <input type="hidden" name="action" value="${not empty requestScope.nextaction?"update":"create"}">
                                    <input type="hidden" name="exercise_id" value="${requestScope.exercise_id}">
                                    <label class="form-label fs-5 fw-medium">Status: </label>
                                    <select id="status_option" class="form-label fs-5 fw-medium" name="status" style="margin-top: 8px;" onchange="changeOption()">
                                        <option ${requestScope.status eq "active"?'selected':''} value="active" selected >Active</option>
                                        <option ${requestScope.status eq "pending"?'selected':''} value="pending" >Pending</option>
                                        <!--<option value="inactive">Inactive</option>-->
                                    </select>
                                    </br>
                                    <div id="time_container" class="">
                                        <label class="form-label fs-5 fw-medium">Ngay mo: </label>
                                        <input id="time-input" name="start_time" type="datetime-local" class="form-control" value="${requestScope.start_date}">

                                        <label class="form-label fs-5 fw-medium">Ngay dong:</label>
                                        <input id="time-input" name="end_time" type="datetime-local" class="form-control" value="${requestScope.end_date}">
                                        </br><h4 style="color: red;"> ${requestScope.timeerror} </h4>
                                        </br><h4 style="color: red;"> ${requestScope.error} </h4>
                                        <!--<input onchange="timeChange()" type="time" id="time-input" name="time" step="3600" min="00:00" max="23:59" pattern="[0-2][0-9]:[0-5][0-9]">-->
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
            },
            events: {
                'image.removed': function ($img) {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        // Image was removed.
                        if (this.readyState == 4 && this.status == 200) {
                            console.log('image was deleted');
                        }
                    };
                    xhttp.open("POST", "http://localhost:8080/LoginGoogle/imageUploadRemove", true);
                    console.log($img);
                    xhttp.send(JSON.stringify({
                        src: $img[0].currentSrc
                    }));
                }
            }
        });
        const getStatus = document.getElementById("status_option");
//            getStatus.addEventListener("change", changeOption());
            function changeOption() {
                var value = getStatus.value;
                var end_time = document.getElementById("start_time");
                var container = document.getElementById("time_container");
                if (value === "pending") {
                    container.classList.add("d-none");
                } else {
                    container.classList.remove("d-none");
                }
            }
    </script>
</body>
</html>
