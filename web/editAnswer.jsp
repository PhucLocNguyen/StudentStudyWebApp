<%-- 
    Document   : editAnswer
    Created on : Mar 1, 2024, 11:27:56 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Answer Question</title>
        <link rel="stylesheet" href="./Assets/css/style.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/froala_editor/froala_editor.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="./css/froala_editor/plugins/table.min.css"/>
        <script src="./js/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="./js/froala_editor/froala_editor.pkgd.min.js"></script>
        <script src="./js/froala_editor/plugins/image.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="./Assets/themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
    <body>
        <%@include file="Components/Header.jsp" %>

        <div class="bg-body-tertiary pt-3">
            <div class="container">
                <div>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item"><a href="insideClass?class_id=${requestScope.exercise.classes.id}">Classroom</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Day la cau hoi</li>
                        </ol>
                    </nav>
                </div>
                <div class="row">
                    <div class="py-4 bg-image-full rounded-4" style="background-image: url('Assets/img/webImg/slide.jpg')">
                        <div class="text-center">
                            <h1 class="text-dark fs-3 fw-bolder">${requestScope.exercise.title}</h1>
                            <h5 class="text-dark fw-medium mb-0">${requestScope.exercise.description}</h5>
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
                                <p class="card-title text-black fs-5">${requestScope.Do.solution}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 card mt-2 rounded-2 d-none" id="arrowTransform">
                        <form action="answerquestion" method="POST">
                            <div class="row d-flex align-content-center justify-content-center">

                                <div class="col-lg-9">
                                    <h5 class="card-title fs-3 fw-bolder pt-3">Edit answer</h5>
                                </div>
                                <div class="col-lg-3 d-flex flex-column align-content-center justify-content-center flex-wrap mt-3">

                                    <button type="submit" value="Lưu" class="btn btn-primary formatBtn mt-2">Save</button>
                                </div>
                            </div>

                            <div class="card-body row mt-5 pt-0">
                                <div class="col-md-12 px-0">
                                    <textarea class="form-control" name="updateSolution" id="froala-editor" cols="30" rows="10">${requestScope.Do.solution}</textarea>
                                </div>
                            </div>     
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="exercise_id" value="${requestScope.exercise.exerciseID}">
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
                    <form accept-charset="UTF-8" action="answerquestion" method="POST" >

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
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="exercise_id" value="${requestScope.exercise.exerciseID}"> 
                    </form>

                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script>
                                    var getAnswer = document.querySelector("#originalAnswer");
                                    var getEditForm = document.querySelector("#arrowTransform");
                                    function showInputForm() {
                                        getAnswer.classList.toggle("col-lg-6");
                                        getEditForm.classList.toggle("formFadeIn");
                                        getEditForm.classList.toggle("d-none");

                                    }
        </script>
        <<script >
            let editor = new FroalaEditor('#froala-editor', {
                // Set the image upload URL.
                entities: '',
                imageUploadURL: 'upload-image',
                imageUploadParams: {
                    id: 'my_editor'
                },
                events: {
                    'image.removed': function ($img) {
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            // Image was removed.
                            if (this.readyState == 4 && this.status == 200) {
                                console.log('image was deleted');
                            }
                        };
                        xhttp.open("POST", "http://localhost:8080/LoginGoogle/imageUploadRemove", true);
                        console.log($img);
                        xhttp.send(JSON.stringify({
                            src: $img[0].currentSrc
                        }));
                    }
                }
            });
        </script>
        <%@include file="Components/Footer.jsp" %>
    </body>
</html>
