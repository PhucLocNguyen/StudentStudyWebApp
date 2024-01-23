<%-- 
    Document   : Login
    Created on : Jan 16, 2024, 12:10:51 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>

<body>
    <div class="position-relative" style="height: 100vh;">
        <div class="card position-absolute start-50 top-50 translate-middle shadow p-3 mb-5 bg-body-tertiary rounded"
            style="width: 30rem;">
            <div class="card-body">
                <form action="">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="formGroupExampleInput" placeholder="Email address">
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Password">
                    </div>
                    <p class="card-text text-danger">Loi roi</p>
                    <div class="d-grid gap-2 col-12 mx-auto">
                        <button type="submit" class="btn btn-primary" type="button">Sign in</button>
                        <button type="submit" class="btn btn-light" type="button">
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/LoginGoogle/LoginGoogleHandler&response_type=code
                               &client_id=984983964678-k4gqt074vh0j025du4752bdurjngalqk.apps.googleusercontent.com&approval_prompt=force"> <img style="width: 30px;" src="./img/GoogleIcon.svg" alt="">Login with Google</a>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="./Components/Footer.jsp" %>
</body>

</html>
