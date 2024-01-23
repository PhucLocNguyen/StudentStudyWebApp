<%-- 
    Document   : Home
    Created on : Jan 21, 2024, 11:34:32 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <%@include file="./Components/Header.jsp" %>
        
    <div class="bg-body-tertiary" style="height: 3000px;">
        <div id="carouselExampleInterval" class="carousel slide container mb-2 mt-2" data-bs-ride="carousel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0"
                            class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                            aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                            aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner " style="height: 270px;">
                        <div class="carousel-item active rounded-4" data-bs-interval="10000">
                            <img src="./Assets/img/slide.jpg" class="d-block w-100 rounded-4 img-fluid" alt="...">
                        </div>
                        <div class="carousel-item" data-bs-interval="10000">
                            <img src="./Assets/img/slide.jpg" class="d-block w-100 rounded-4 img-fluid" alt="...">
                        </div>
                        <div class="carousel-item" data-bs-interval="10000">
                            <img src="./Assets/img/slide.jpg" class="d-block w-100 rounded-4 img-fluid" alt="...">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval"
                        data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval"
                        data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
        <!-- Ket thuc slide -->


        <div class="container mt-5">

            <div class="row">
                <h3 class="fw-medium mb-1">Kham pha cac lop hoc</h3>
            </div>
            <div class="row mt-3 mb-4">
                <div class="col-lg-4">
                    <div class="card rounded-4">
                        <img src="./Assets/img/slide.jpg" class="card-img-top object-fit-cover rounded-top-4" alt="..."
                            style="max-height: 10rem;">
                        <div class="card-body">
                            <h5 class="card-title">Ten lop hoc</h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card rounded-4">
                        <img src="./Assets/img/slide.jpg" class="card-img-top object-fit-cover rounded-top-4" alt="..."
                            style="max-height: 10rem;">
                        <div class="card-body">
                            <h5 class="card-title">Ten lop hoc</h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card rounded-4">
                        <img src="./Assets/img/slide.jpg" class="card-img-top object-fit-cover rounded-top-4" alt="..."
                            style="max-height: 10rem;">
                        <div class="card-body">
                            <h5 class="card-title">Ten lop hoc</h5>
                            <p class="card-text">This is a longer card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Ket thuc kham pha hoc phan -->

            <div class="row">
                <h3 class="fw-medium mb-3">Lop hoc cua ban</h3>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Ten lop hoc</h5>
                            <p class="card-text">Fpt, VietNam</p>
                            <span class="badge rounded-pill text-bg-secondary">19 cau hoi</span>
                            <span class="badge rounded-pill text-bg-secondary">2 thanh vien</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Ten lop hoc</h5>
                            <p class="card-text">Fpt, VietNam</p>
                            <span class="badge rounded-pill text-bg-secondary">19 cau hoi</span>
                            <span class="badge rounded-pill text-bg-secondary">2 thanh vien</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Ten lop hoc</h5>
                            <p class="card-text">Fpt, VietNam</p>
                            <span class="badge rounded-pill text-bg-secondary">19 cau hoi</span>
                            <span class="badge rounded-pill text-bg-secondary">2 thanh vien</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Ten lop hoc</h5>
                            <p class="card-text">Fpt, VietNam</p>
                            <span class="badge rounded-pill text-bg-secondary">19 cau hoi</span>
                            <span class="badge rounded-pill text-bg-secondary">2 thanh vien</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
         <%@include file="./Components/Footer.jsp" %>
    </body>
</html>
