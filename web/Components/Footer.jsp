<%-- 
    Document   : Footer
    Created on : Jan 21, 2024, 11:33:50 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body> 
        <div class="bg-body-tertiary">
            <footer class="container py-4 " style="height: 5rem;">
                <div class="row">
                    <div class="col-lg-12 fs-2 text-center">
                        <a class="text-decoration-none text-dark px-2" href=""><i class="ti-facebook"></i></a>
                        <a class="text-decoration-none text-dark px-2" href=""><i class="ti-twitter"></i></a>
                        <a class="text-decoration-none text-dark px-2" href=""><i class="ti-youtube"></i></a>
                        <a class="text-decoration-none text-dark px-2" href=""><i class="ti-instagram"></i></a>

                    </div>
                </div>
            </footer>
        </div>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
        <script>
            var getNotification = document.querySelector("#myModalNotification #notificationContainer");
            var numberNotification = document.querySelector("#numberNotification");
            $.ajax({
                url: "notificationController",
                method: "GET",
                data: {
                    action: "loadByID"
                }, success: function (data) {
                    var structureNotification = "";
                    var lengthData = 0;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].isRead) {
                            structureNotification += '<a href="' + data[i].link + '" class="card-body text-left notificationReceive position-relative" notification-id="' + data[i].notification.id + '" style="text-decoration: none;" onclick="viewToPage(this)"><p><div><img src="./Assets/img/webImg/messageRead.png" class="imgNotification"></div><div><p>' + data[i].notification.message + '</p> <p> <i class="ti-calendar"></i> at ' + data[i].notification.create_date + '</p><i class="ti-close formatCloseNotification img-rounded text-bg-danger" notification-id="' + data[i].notification.id + '" onclick="deleteNotification(this)"></i></div></a>';
                        } else {
                            lengthData++;
                            structureNotification += '<a href="' + data[i].link + '" class="card-body text-left notificationReceive position-relative" notification-id="' + data[i].notification.id + '" style="text-decoration: none;" onclick="viewToPage(this)"><div><img src="./Assets/img/webImg/waitingIconReceive.png" class="imgNotification"></div><div><p>' + data[i].notification.message + '</p> <p> <i class="ti-calendar"></i> at ' + data[i].notification.create_date + '</p><i class="ti-close formatCloseNotification img-rounded text-bg-danger" notification-id="' + data[i].notification.id + '" onclick="deleteNotification(this)"></i></div></a>';
                        }
                        numberNotification.innerText = lengthData;
                        getNotification.innerHTML = structureNotification;
                    }
                }
                , error: function (xhr, status, error) {
                    $("#messageError").html("Wrong password, please try another password!");
                }
            })
            setInterval(function () {
                $.ajax({
                    url: "notificationController",
                    method: "GET",
                    data: {
                        action: "loadByID"
                    }, success: function (data) {
                        var structureNotification = "";
                        var lengthData = 0;
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].isRead) {
                                structureNotification += '<a href="' + data[i].link + '" class="card-body text-left notificationReceive position-relative mb-3" notification-id="' + data[i].notification.id + '" style="text-decoration: none;" onclick="viewToPage(this)"><p><div><img src="./Assets/img/webImg/messageRead.png" class="imgNotification"></div><div><p>' + data[i].notification.message + '</p> <p> <i class="ti-calendar"></i> at ' + data[i].notification.create_date + '</p><i class="ti-close formatCloseNotification img-rounded text-bg-danger" notification-id="' + data[i].notification.id + '" onclick="deleteNotification(this)"></i></div></a>';
                            } else {
                                lengthData++;
                                structureNotification += '<a href="' + data[i].link + '" class="card-body text-left notificationReceive position-relative mb-3" notification-id="' + data[i].notification.id + '" style="text-decoration: none;" onclick="viewToPage(this)"><div><img src="./Assets/img/webImg/waitingIconReceive.png" class="imgNotification"></div><div><p>' + data[i].notification.message + '</p> <p> <i class="ti-calendar"></i> at ' + data[i].notification.create_date + '</p><i class="ti-close formatCloseNotification img-rounded text-bg-danger" notification-id="' + data[i].notification.id + '" onclick="deleteNotification(this)"></i></div></a>';
                            }
                            numberNotification.innerText = lengthData;
                            getNotification.innerHTML = structureNotification;
                        }
                    }
                    , error: function (xhr, status, error) {
                        $("#messageError").html("Wrong password, please try another password!");
                    }
                })
            }, 3000);

        </script>
        <script>
            function viewToPage(element) {
                var getNotificationID = element.getAttribute("notification-id");
                $.ajax({
                    url: "notificationController",
                    method: "POST",
                    data: {
                        notificationID: getNotificationID,
                        action: "seenReceive"
                    }, success: function (data) {
                    }, error: function (xhr, status, error) {
                    }
                })
            }
            function deleteNotification(element) {
                var getNotificationID = element.getAttribute("notification-id");
                $.ajax({
                    url: "notificationController",
                    method: "POST",
                    data: {
                        notificationID: getNotificationID,
                        action: "delete"
                    }, success: function (data) {
                        sessionStorage.setItem("toastMessage", "Delete notification success!!!");
                    }, error: function (xhr, status, error) {
                    }
                })
            }
        </script>
        <script>
            function showToast(message) {
                Toastify({
                    text: message,
                    duration: 3000, // 3 seconds
                    close: true,
                    gravity: "top", // Position of toast message
                    position: "right", // Position of toast message
                    backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)" // Background color of toast message
                }).showToast();
            }
            var toastMessage = sessionStorage.getItem("toastMessage");

// Kiểm tra nếu có thông điệp, thì hiển thị toastify
            if (toastMessage) {
                showToast(toastMessage);
                sessionStorage.removeItem("toastMessage"); // Xóa thông điệp sau khi hiển thị
            }
        </script>
    </body>
</html>
