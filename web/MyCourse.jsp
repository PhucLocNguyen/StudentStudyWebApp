<%-- Document : MyCourse Created on : Jan 21, 2024, 2:59:01 PM Author : ACER
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>My Courses</title>
        <link rel="stylesheet" href="./Assets/css/style.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/froala_editor/froala_editor.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./js/froala_editor/froala_editor.pkgd.min.js"></script>
        <script src="./js/froala_editor/plugins/image.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
    <%@include file="./Components/Header.jsp" %>

    <body>
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
                        Server Version: <%= application.getServerInfo()%><br>
                        Servlet Version: <%= application.getMajorVersion()%>.<%= application.getMinorVersion()%>
                        JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion()%> <br>
                        <form action="create-class" method="POST" enctype="multipart/form-data">

                            <div class="modal-body">
                                <div class="mb-3">
                                    <p class="text-primary mb-1">Tên lớp học</p>
                                    <input type="text" class="form-control" placeholder="ex: Mathematics" name="className"/>
                                </div>
                                <div class="mb-3">
                                    <p class="text-primary mb-1">Thumbnail</p>
                                    <input type="file" class="form-control" id="inputGroupFile01" name="thumbnail">
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
  <p>
  Open the console to see the <em>html.get</em> method working.
  </p>
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
            <%@include file="./Components/Footer.jsp" %>
        </div>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
        <% if (request.getAttribute("message") != null) { // đang bị lỗi này có giải pháp là bắt sự kiện submit form rồi gửi về bằng Ajax rồi trả dữ liệu json cho toastify %> 
        <script>
                                        Toastify({
                                            text: request.getAttribute("message"),
                                            duration: 3000,
                                            destination: "https://github.com/apvarun/toastify-js",
                                            newWindow: true,
                                            close: true,
                                            gravity: "top", // `top` or `bottom`
                                            position: "left", // `left`, `center` or `right`
                                            stopOnFocus: true, // Prevents dismissing of toast on hover
                                            style: {
                                                background: "linear-gradient(to right, #00b09b, #96c93d)",
                                            },
                                            onClick: function () {} // Callback after click
                                        }).showToast();
        </script>
        <%}%>
        <script>

            let editor = new FroalaEditor('#froala-editor', {
//                imageUploadURL: '/LoginGoogle/upload-image', // Đặt URL của máy chủ để xử lý tải lên hình ảnh
//                imageUploadParams: {
//                    id: 'my_editor'
//                },
//                imageManagerDeleteMethod: "POST",
                events: {

                    'image.inserted': function (response) {
                        // Do something here.
                        // this is the editor instance.
                        console.log("bat su kien");
                        console.log(response);
                        $.post("upload-image",
                                {
                                    url: response[0].currentSrc,
                                    city: "Duckburg"
                                },
                                function (data, status) {
                                    alert("Data: " + data + "\nStatus: " + status);
                                });
                    }
                    ,
                    'contentChanged': function () {
                        const editorContent = this.html.get();
                        console.log(editorContent);
                    }


                }
            }, function () {
                console.log(editor.html.get());
            });

        </script>
    </body>
</html> 
