<%-- Document : MyCourse Created on : Jan 21, 2024, 2:59:01 PM Author : ACER
--%> <%@page import="java.util.ArrayList"%>
<%@page import="Model.ClassesDAO"%>
<%@page import="java.util.List"%>
<%@page import="Model.ClassesDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>My Courses</title>
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

    <body>
        <div style="min-height: 650px">

            <%@include file="./Components/Header.jsp" %>
            <div class="bg-body-tertiary">
                <div class="container pt-3">

                    <%  if (getRole != null && getRole.equals("lecturer")) {%>
                    <!-- Button HTML (to Trigger Modal) -->
                    <div class="row align-items-center justify-content-between mt-2">
                        <div class="col-sm-12 col-lg-4">
                            <h4 class="fs-2 mb-0">Classroom management</h4>

                        </div>
                        <div class="col-sm-12 col-lg-5">
                            <form action="showdashboard" id="formSelect">
                                <select class="form-select" aria-label="Sort from A to Z" id="sort-select" name="selectValue"  onchange="selectChanged()">
                                    <option value="1" <% if ("1".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from A to Z</option>
                                    <option value="2" <% if ("2".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from Z to A</option>
                                    <option value="3" <% if ("3".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from newest to oldest</option>
                                    <option value="4" <% if ("4".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from oldest to newest</option>
                                </select>
                            </form>
                        </div>
                        <div class="col-sm-12 col-lg-3">
                            <a href="#myModal" role="button" class="btn btn-primary" data-bs-toggle="modal">Create</a>
                        </div>

                    </div>
                    <div class="row mt-3">
                        <%
                            List<ClassesDTO> classOwned = (List<ClassesDTO>) request.getAttribute("listClass");
                            for (ClassesDTO item : classOwned) {

                        %>

                        <div class="col-lg-4 mt-3" >
                            <div class="card rounded-4">
                                <img src="<%=item.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="<%= item.getName()%>" style="max-height: 10rem;">
                                <div class="card-body" style="min-height: 151.6px">
                                    <h5 class="card-title"><%=item.getName()%></h5>
                                    <p class="card-text">Lecturer : <%= item.getLecturer().getEmail()%></p>
                                    <a href="<%="insideClass?class_id=" + item.getId()%>" class="btn btn-lg btn-primary" >Go to class</a>
                                </div>
                            </div>
                        </div>
                        <%                }
                        %>
                    </div>
                    <!-- Modal HTML -->
                    <div id="myModal" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Create classroom</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>
                                <form accept-charset="UTF-8" action="create-class" method="POST" enctype="multipart/form-data" onsubmit="validateForm(event)">
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <p class="text-primary mb-1">Class name</p>
                                            <input type="text" class="inputFormat form-control" placeholder="ex: Mathematics" name="className"/>
                                        </div>
                                        <div class="mb-3">
                                            <p class="text-primary mb-1">Thumbnail</p>
                                            <input type="file" class="inputFormat form-control" id="inputGroupFile01" name="thumbnail" accept="image/*">
                                        </div>
                                        <div class="mb-3">
                                            <p class="text-primary mb-1">Password</p>
                                            <input type="password" class="inputFormat form-control" name="password" id="passwordInput"/>
                                            <input type="checkbox" onclick="myFunction()"> Show Password
                                            <input type="hidden" name="action" value="create">
                                            <script>
                                                function myFunction() {
                                                    var x = document.getElementById("passwordInput");
                                                    if (x.type === "password") {
                                                        x.type = "text";
                                                    } else {
                                                        x.type = "password";
                                                    }
                                                }
                                            </script>
                                        </div>
                                        <div class="mb-3">
                                            <p class="text-primary mb-1">Description</p>
                                            <textarea id="froala-editor" name="description">
                                            </textarea>
                                            <p class="text-danger mt-2" id="errorMsg">${requestScope.errorMsg}</p>
                                        </div>

                                        <!--<p class="text-secondary"><small>If you don't save, your changes will be lost.</small></p>-->
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-primary" onclick="">Create</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <%} else {
                    %>
                    <div class="col-sm-12 col-lg-5">
                        <form action="showdashboard" id="formSelect">
                            <select class="form-select" aria-label="Sort from A to Z" id="sort-select" name="selectValue"  onchange="selectChanged()">
                                <option value="1" <% if ("1".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from A to Z</option>
                                <option value="2" <% if ("2".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from Z to A</option>
                                <option value="3" <% if ("3".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from newest to oldest</option>
                                <option value="4" <% if ("4".equals(request.getAttribute("selectValue"))) { %> selected <% } %>>Sort from oldest to newest</option>
                            </select>
                        </form>
                    </div>
                    <div class="row mt-3 pb-3">
                        <%
                            List<ClassesDTO> listClass = (List<ClassesDTO>) request.getAttribute("listClass");
                            if (listClass.size() > 0) {
                                for (ClassesDTO item : listClass) {


                        %>
                        <div class="col-lg-4 mt-3">
                            <a href="<%="insideClass?class_id=" + item.getId()%>" style="text-decoration: none">
                                <div class="card rounded-4" >
                                    <img src="<%=item.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="..." style="max-height: 10rem;">
                                    <div class="card-body">
                                        <h5 class="card-title"><%=item.getName()%></h5>
                                        <p class="card-text">Lecturer: <%= item.getLecturer().getEmail()%></p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <%
                                    }
                                }
                            }%> 
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
                                <a class="page-link" href="showdashboard?keyWord=${requestScope.keyWord}&page=<%=i%>&selectValue=${requestScope.selectValue}" tabindex="-1"><%= i%></a>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </nav>
                </div>
            </div>

            <%@include file="./Components/Footer.jsp" %>
        </div>

        <script>
            function validateForm(event) {
                event.preventDefault();
                var getFormInput = event.srcElement;
                var getAllInput = document.getElementsByClassName("inputFormat");
                var check = false, i;
                for (i = 0; i < getAllInput.length; i++) {
                    let inputSelected = getFormInput[i];
                    let inputValue = inputSelected.value.trim();
                    if (inputValue.length === 0) {
                        check = true;
                    }
                    if (check && i === getAllInput.length - 1) {
                        document.getElementById("errorMsg").innerText = "if you leave atleast one input is empty the form is not allow to save !";
                        return;
                    }
                }
                sessionStorage.setItem("toastMessage", "Create class successful!!!");
                event.target.submit();
            }
        </script>
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

        </script>
        <script>
            function selectChanged() {
                var formSelect = document.getElementById("formSelect");
                formSelect.submit();
            }

        </script>
    </body>
</html>