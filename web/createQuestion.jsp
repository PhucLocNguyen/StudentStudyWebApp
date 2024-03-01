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
                                        <h1 class="card-title fw-bolder fs-2">Tao cau hoi moi</h1>

                                            <label class="form-label fs-5 fw-medium">Tieu de cau hoi</label>
                                            <input type="text" class="form-control" placeholder="Nhap ten cua lop hoc" name="title">
                                            <div>
                                            <p class="form-label fs-5 fw-medium">Mo ta</p>
                                            <textarea id="froala-editor" class="form-control" rows="3"
                                                      placeholder="Them mo ta cho lop hoc cua ban" name="description"></textarea>
                                            </div>

                                            <div>
                                                <p class="form-label fs-5 fw-medium">Tệp đính kèm</p>
                                                <input type="file" class="form-control" id="inputGroupFile01" name="thumbnail">
                                            </div>

    <!--                                        <label class="form-label fs-5 fw-medium">Tệp đính kèm</label>
                                            <input type="file" class="form-control" id="inputGroupFile01" name="thumbnail">-->

                                            <input type="hidden" name="classId" value="<%=classId%>">

                                            <label class="form-label fs-5 fw-medium">Status: </label>
                                            <select class="form-label fs-5 fw-medium" name="status" style="margin-top: 8px;">
                                            <option value="active">Active</option>
                                            <option value="spending">Spending</option>
                                            <option value="inactive">Inactive</option>
                                            </select>
                                            </br>
                                            <label class="form-label fs-5 fw-medium">Thoi han</label>
                                            <input type="text" class="form-control">
                                            <input class="btn btn-primary my-3" type="submit" value="Luu cau hoi">

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
            </script>
    </body>
</html>
