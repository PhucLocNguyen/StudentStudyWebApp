<%-- 
    Document   : newjsp
    Created on : Feb 25, 2024, 9:45:13 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inside class</title>
    <link rel="stylesheet" href="./Assets/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="./Assets/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>

<body>
    <div class="container">
        <%@include file="./Components/Header.jsp" %>
<!-- Ket thuc nav -->

    <div class="bg-body-tertiary pt-3">
        <div class="container">
            <div class="row">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="#">Home</a></li>
                      <li class="breadcrumb-item"><a href="#">Classroom</a></li>
                      <li class="breadcrumb-item"><a href="#">WORKSHOP PROJECT</a></li>
                      <li class="breadcrumb-item active" aria-current="page">Bài làm đã nộp</li>
                    </ol>
                  </nav>
                <div class="py-4 bg-image-full rounded-4" style="background-image: url('./img/background.jpg')">
                    <div class="text-center">
                        <h1 class="text-white fs-3 fw-bolder">WORKSHOP PROJECT</h1>
                        <h5 class="text-white fw-medium mb-0">Hãy viết ra những yêu cầu khách hàng chi tiết sau khi phỏng vấn (Dài hơn 300 chữ)</h5>
                    </div>
                </div>
            </div>
            
            <div class="row my-3">
                <!-- Cac cau hoi trong lop -->
                <div class="col-sm-12 card mt-2 rounded-2 transitionEaseInOutFlex" id="originalAnswer">
                    <div class="row d-flex align-content-center justify-content-center">
                        <div class="col-lg-8">
                            <h5 class="card-title fs-3 fw-bolder pt-3">Câu trả lời đã nộp</h5>
                            <div class="d-flex px-2">
                                <span class="badge rounded-pill bg-success text-bg-secondary my-1 me-3 fs-6">đã nộp vào lúc 5:30 19/09/2023</span>
                            </div>
                            
                        </div>
                        <div class="col-lg-4 d-flex align-content-center flex-wrap justify-content-center gap-2">
                                    <button class="btn btn-outline-primary formatBtn" onclick="showInputForm()">Chỉnh sửa</button>
                                    <a href="#myModal" role="button" class="btn btn-danger text-center formatBtn" data-bs-toggle="modal">Xoá</a>
                        </div>
                    </div>
               
                
                    <div class="card-body row">
                        <div class="col-lg-12">
                        <p class="card-title text-black fs-5">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 card mt-2 rounded-2 d-none" id="arrowTransform">
                    <form action="">
                        <div class="row d-flex align-content-center justify-content-center">
                        
                            <div class="col-lg-9">
                                <h5 class="card-title fs-3 fw-bolder pt-3">Chỉnh sửa câu trả lời</h5>
                            </div>
                            <div class="col-lg-3 d-flex flex-column align-content-center justify-content-center flex-wrap mt-3">
                                
                               <button type="submit" value="Lưu" class="btn btn-primary formatBtn mt-2">Lưu</button>
                            </div>
                        </div>
                    
                        <div class="card-body row mt-5 pt-0">
                            <div class="col-md-12 px-0">
                                <textarea class="form-control" name="answer" id="" cols="30" rows="10">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</textarea>
                            </div>
                        </div>     
                    </form>
                    
                </div>
            </div>
                <!-- Ket thuc cua cau hoi trong lop hoc -->

        </div>
    </div>
  
  <!-- Modal -->
  <div id="myModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xác nhận</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form accept-charset="UTF-8" action="create-class" method="POST" enctype="multipart/form-data">

                <div class="modal-body">
                    <div class="mb-3">
                        <p class="mb-1 text-danger fs-5">Hành động này sẽ tiến hành xoá vĩnh viễn câu trả lời của bạn ở câu hỏi này</p>
                    </div>

                    <!--<p class="text-secondary"><small>If you don't save, your changes will be lost.</small></p>-->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger" >Xoá</button>
                </div>
            </form>

        </div>
    </div>
</div>
        </div>

        <div class="border-top border-secondary-subtle mt-5 mb-2"></div>
        <footer class="container my-3 " style="height: 5rem;">
            <div class="row">
                <div class="col-lg-12 fs-2 text-center">
                    <a class="text-decoration-none text-dark px-2" href=""><i class="ti-facebook"></i></a>
                    <a class="text-decoration-none text-dark px-2" href=""><i class="ti-twitter"></i></a>
                    <a class="text-decoration-none text-dark px-2" href=""><i class="ti-youtube"></i></a>
                    <a class="text-decoration-none text-dark px-2" href=""><i class="ti-instagram"></i></a>
                    <p class=" text-center fs-4 text-muted ">Copyright &copy; Bai tap nhom</p>
                </div>
            </div>
        </footer>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script>
            var getAnswer = document.querySelector("#originalAnswer");
            var getEditForm = document.querySelector("#arrowTransform");
            function showInputForm(){
                getAnswer.classList.toggle("col-lg-6");
                getEditForm.classList.toggle("formFadeIn");
                getEditForm.classList.toggle("d-none");

            }
        </script>
</body>

</html>