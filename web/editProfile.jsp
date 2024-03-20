<%-- 
    Document   : editProfile
    Created on : Mar 14, 2024, 9:26:41 PM
    Author     : hn190
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <link rel="stylesheet" href="./Assets/css/style.css">
        <link rel="stylesheet" href="./themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="./Assets/themify-icons/themify-icons.css">
    </head>
    <body>

        <%@include file="./Components/Header.jsp" %>

        <div class="container">
            <div class="row mt-3">
                <div class="col-lg-8 mx-auto mt-5 border">

                    <%                        if (request.getAttribute("error") != null) {
                    %>

                    <div class="alert alert-danger mt-3 fw-medium" role="alert">
                        <%= request.getAttribute("error")%>
                    </div>

                    <%
                        }
                    %>

                    <form accept-charset="UTF-8" action="viewprofile" method="POST" enctype="multipart/form-data" >
                        <div class="input-group pt-3">
                            <span style="min-width: 9rem;" class="input-group-text">Student name</span>
                            <input name="name" value="${requestScope.object.getName()}" type="text" aria-label="First name" class="form-control">
                        </div>
                        <div class="input-group mt-3">
                            <span style="min-width: 9rem;" class="input-group-text">Thumbnail</span>
                            <input type="file" name="thumbnail" class="form-control" accept="image/*">
                        </div>
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${requestScope.object.getId()}">
                        <input class="btn btn-primary my-3" type="submit" value="Save">
                    </form>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    </body>
</html>
