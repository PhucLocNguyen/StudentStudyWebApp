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
            String getRole = "";
            int id = 0;
            HttpSession getSession = request.getSession(false);
            if (getSession != null && session.getAttribute("user") != null) {
                getRole = (String) session.getAttribute("role");
                if (getRole.equals("lecturer")) {
                    LectureDTO user = (LectureDTO) session.getAttribute("user");
                    name = user.getName();
                    image = user.getThumbnail();
                    id = user.getId();
                } else {
                    StudentDTO user = (StudentDTO) session.getAttribute("user");
                    name = user.getName();
                    image = user.getThumbnail();
                    id = user.getId();
                }
            }

        %>
        <div class="container">
            <div class="row mt-2">
                <div class="col-sm-12 col-lg-5">
                    <nav class="navbar navbar-expand-lg bg-white justify-content-between">
                        <div class=" position-relative d-md-none" style="display: inline-block;">
                            <img style="width: 40px; height: 40px" class="rounded-circle " src="<%=image%>" alt="..."
                                 data-bs-toggle="offcanvas" data-bs-target="#mobileCanvas" aria-controls="mobileCanvas"
                                 aria-label="Toggle navigation">
                            <span
                                class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">99+</span>
                            <div class="offcanvas offcanvas-end" tabindex="-1" id="mobileCanvas"
                                 aria-labelledby="mobileCanvas">
                                <div class="offcanvas-header">
                                    <h5 class="offcanvas-title" id="mobileCanvas"><%=name%></h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                            aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                                        <li class="nav-item">
                                            <a class="nav-link active fs-5" aria-current="page" href="#">Account Information</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link fs-5" href="#">Edit Profile</a>
                                        </li>
                                    </ul>
                                    <div class="d-grid col-12 mt-5">
                                        <form action="logout">
                                            <input type="submit" value="Logout" class="btn btn-primary" width="100%">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- <div style="background-color: red;"></div> -->
                        <a class="navbar-brand px-5" href="#">
                            <img class="img-responsive" src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/FPT_logo_2010.svg/800px-FPT_logo_2010.svg.png" alt="..." width="80" style="object-fit: cover;">
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="nav_form navbar-nav mb-2 mb-lg-0 justify-content-around fw-bold fs-5 text-nowrap" style="width: 100%">
                                <li class="nav-item"><a class="nav-link" href="home">Home</a></li>
                                <li class="nav-item"><a class="nav-link" href="showdashboard">Dashboard</a></li>

                                <!-- <li class="nav-item px-4"><a class="nav-link" href="#">My courses</a></li> -->
                            </ul>
                        </div>
                    </nav>
                </div>
                <div class="col-lg-5 py-2">
                    <div style="background-color: black;"></div>
                    <form class="d-flex" action="searchingclass">
                        <div class="input-group">
                            <input type="text" class="form-control rounded-start-pill pill mt-2 bg-body-secondary"
                                   placeholder="Search" name="keyWord" value="<%= request.getParameter("keyWord") != null ? request.getParameter("keyWord") : ""%>">
                            <button class="btn btn-outline-secondary rounded-end-pill mt-2" type="submit"
                                    id="button-addon2"><i class="ti-search fw-bolder"></i></button>
                        </div>
                    </form>
                </div>
                <div class="col-sm-4 d-none d-md-block col-lg-2 py-2">
                    <nav class="navbar " style="display: inline-block;">
                        <div class=" position-relative" style="display: inline-block;">
                            <img style="width: 40px; height: 40px" class="rounded-circle " src="<%=image%>" alt="..."
                                 data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar"
                                 aria-label="Toggle navigation">
                            <span
                                class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">99+</span>
                        </div>
                        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
                             aria-labelledby="offcanvasNavbarLabel">
                            <div class="offcanvas-header">
                                <h5 class="offcanvas-title" id="offcanvasNavbarLabel"><%=name%></h5>
                                <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                        aria-label="Close"></button>
                            </div>
                            <div class="offcanvas-body">
                                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                                    <li class="nav-item">
                                        <a class="nav-link active fs-5" aria-current="page" href="viewprofile?action=showmyprofile&id=${sessionScope.user.getId()}&role=${sessionScope.role}">Account Information</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link fs-5" href="viewprofile?action=edit&id=${sessionScope.user.getId()}&role=${sessionScope.role}">Edit Profile</a>
                                    </li>
                                </ul>
                                <form action="logout" method="POST">
                                    <div class="d-grid col-12 mt-5">
                                        <button class="btn btn-primary" type="submit">Logout</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>                    
    </body>
</html>
