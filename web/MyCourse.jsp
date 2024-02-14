<%-- Document : MyCourse Created on : Jan 21, 2024, 2:59:01 PM Author : ACER
--%> <%@page import="java.util.List"%>
<%@page import="Model.ClassesDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Course</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
         <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <%@include file="./Components/Header.jsp" %>
    <body>
        <div class="container">
        <%  if (getRole != null && getRole.equals("lecturer")) {%>
        <div class="">
            <!-- Button HTML (to Trigger Modal) -->
            <a href="#myModal" role="button" class="btn btn-lg btn-primary" data-bs-toggle="modal">Tạo lớp học</a>

            <!-- Modal HTML -->
            <div id="myModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Tạo lớp học</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                        <form accept-charset="UTF-8" action="create-class" method="POST" enctype="multipart/form-data">

                            <div class="modal-body">
                                <div class="mb-3">
                                    <p class="text-primary mb-1">Tên lớp học</p>
                                    <input type="text" class="form-control" placeholder="ex: Mathematics" name="className"/>
                                </div>
                                <div class="mb-3">
                                    <p class="text-primary mb-1">Thumbnail</p>
                                    <input type="file" class="form-control" id="inputGroupFile01" name="thumbnail" accept="image/*">
                                </div>
                                <div class="mb-3">
                                    <p class="text-primary mb-1">Password</p>
                                    <input type="password" class="form-control" name="password" id="passwordInput"/>
                                    <input type="checkbox" onclick="myFunction()"> Show Password

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

                                </div>

                                <!--<p class="text-secondary"><small>If you don't save, your changes will be lost.</small></p>-->
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary" >Create</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
            <div>
            <%} else {
               %>
               <div class="row mt-3 mb-4">
            <%   
                List<ClassesDTO> listClass = (List<ClassesDTO>) request.getAttribute("listClass");
                if (listClass.size() > 0) {
                    for (ClassesDTO item : listClass) {


            %>
            <div class="col-lg-4">
                <div class="card rounded-4">
                    <img src="<%=item.getThumbnail()%>" class="card-img-top object-fit-cover rounded-top-4" alt="..." style="max-height: 10rem;">
                    <div class="card-body">
                        <h5 class="card-title"><%=item.getName()%></h5>
                        <p class="card-text">Giảng viên: <%= item.getLecturer().getEmail()%></p>
                    </div>
                </div>
            </div>
            <%
                        }
                    }
                }%> 
                
            <%@include file="./Components/Footer.jsp" %>
        </div>
        
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>

        <script>
                                        let editor = new FroalaEditor('#froala-editor', {
                                            // Set the image upload URL.
                                            entities: '',
                                            imageUploadURL: 'upload-image',

                                            imageUploadParams: {
                                                id: 'my_editor'
                                            }
                                        })
        </script>
    </body>
</html> 
