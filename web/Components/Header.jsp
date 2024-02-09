<%-- 
    Document   : Header
    Created on : Jan 21, 2024, 11:33:40 AM
    Author     : ACER
--%>

<%@page import="Model.LectureDTO"%>
<%@page import="Model.StudentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <%

            String name = ""; // Khai báo và khởi tạo biến name trước khi sử dụng
            String image = "";
            String getRole = (String) session.getAttribute("role");
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            } else {
                if (getRole.equals("lecturer")) {
                    LectureDTO user = (LectureDTO) session.getAttribute("user");
                    name = user.getName();
                    image = user.getThumbnail();
                } else {
                    StudentDTO user = (StudentDTO) session.getAttribute("user");
                    name = user.getName();
                    image = user.getThumbnail();
                }

            }

//            String name = "";
//            if (session.getAttribute("user") != null) {
//                UserGoogleDTO user = (UserGoogleDTO) session.getAttribute("user");
//                name = user.getName();
//            } else {
//                response.sendRedirect("login.jsp");
//                name = "Anonymous";
//            }
%>
        <div class="container">
            <div class="row mt-2">
                <div class="col-lg-5">
                    <nav class="navbar navbar-expand-lg bg-white justify-content-center">
                        <div style="background-color: red;"></div>
                        <a class="navbar-brand px-4" href="#">
                            <img src=<%= image%> alt="..." width="30" height="24">
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>   
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav mb-2 mb-lg-0 justify-content-center fw-bold fs-5">
                                <li class="nav-item text-nowrap px-4"><a class="nav-link" href="home">Home</a></li>
                                <li class="nav-item text-nowrap px-4"><a class="nav-link " href="MyCourse.jsp">My Courses</a></li>
                                <li class="nav-item text-nowrap px-4"><a class="nav-link" href="#">Dashboard</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
                <div class="col-lg-5 py-2">
                    <div style="background-color: black;"></div>
                    <form class="d-flex" action="searchingclass">
                        <div class="input-group">
                            <input type="text" class="form-control rounded-pill pill mt-2 bg-body-secondary"
                                   placeholder="Tim kiem" name="keyWord" value="<%= request.getParameter("keyWord")!=null?request.getParameter("keyWord"):"" %>">
                        </div>
                    </form>
                </div>
                <div class="col-lg-2 py-2">
                    <nav class="navbar " style="display: inline-block;">
                        <div class=" position-relative" style="display: inline-block;">
                            <img style="width: 40px;" class="rounded-circle " src=<%=image%> alt="..."
                                 data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar"
                                 aria-label="Toggle navigation">
                            <span
                                class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">99+</span>
                        </div>
                        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
                             aria-labelledby="offcanvasNavbarLabel">
                            <div class="offcanvas-header">
                                <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
                                    <%=name%>
                                </h5>
                                <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                        aria-label="Close"></button>
                            </div>
                            <div class="offcanvas-body">
                                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                                    <li class="nav-item">
                                        <a class="nav-link active fs-5" aria-current="page" href="#">Thong tin tai khoan</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link fs-5" href="#">Thong tin tai khoan</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link fs-5" href="#">Thong tin tai khoan</a>
                                    </li>
                                    <!-- <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" role="button"
                                                data-bs-toggle="dropdown" aria-expanded="false">
                                                Dropdown
                                            </a>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" href="#">Action</a></li>
                                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>
                                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                                            </ul>
                                        </li> -->
                                </ul>
                                <!-- <form class="d-flex mt-3" role="search">
                                        <input class="form-control me-2" type="search" placeholder="Search"
                                            aria-label="Search">
                                        <button class="btn btn-outline-success" type="submit">Search</button>
                                    </form> -->
                            </div>
                        </div>
                    </nav>

                </div>
            </div>
        </div>
    </body>
</html>
