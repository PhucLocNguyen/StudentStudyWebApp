<%-- Document : MyCourse Created on : Jan 21, 2024, 2:59:01 PM Author : ACER
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>JSP Page</title>
        <script
            src="./Assets/tinymce/js/tinymce/tinymce.min.js"
            referrerpolicy="origin"
        ></script>
    </head>
    <body>
        <%@include file="./Components/Header.jsp" %>
        <div class="container">
            <form method="AddCourse" method="POST">
                <div class="input-group mb-3 row">
                    <label for="title" class="form-label"></label>
                    <input name="title" class="form-control" type="text" />
                    <input type="file" class="form-control col-9" id="inputGroupFile02" />
                    <input type="" />
                </div>
                <!-- Place the first <script> tag in your HTML's <head> -->

                <!-- Place the following <script> and <textarea> tags your HTML's <body> -->
                <textarea id="myTextarea"></textarea>
            </form>
        </div>
        <%@include file="./Components/Footer.jsp" %>
        <script>
            tinymce.init({
                selector: 'textarea',
                plugins: 'ai tinycomments mentions anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed permanentpen footnotes advtemplate advtable advcode editimage tableofcontents mergetags powerpaste tinymcespellchecker autocorrect a11ychecker typography inlinecss',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                tinycomments_mode: 'embedded',
                tinycomments_author: 'Author name',
                mergetags_list: [
                    {value: 'First.Name', title: 'First Name'},
                    {value: 'Email', title: 'Email'},
                ],
                ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
            });
        </script>
    </body>
</html>
