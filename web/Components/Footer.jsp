<%-- 
    Document   : Footer
    Created on : Jan 21, 2024, 11:33:50 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body> 
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
        <script>
            var getNotification = document.querySelector("#myModalNotification #notificationContainer");
            var numberNotification = document.querySelector("#numberNotification");
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
                                structureNotification += '<a href="' + data[i].link + '" class="card-body text-left notificationReceive" notification-id="' + data[i].notification.id + '" style="text-decoration: none;" onclick="viewToPage(this)"><p><div><img src="./Assets/img/webImg/messageRead.png" class="imgNotification"></div><div><p>' + data[i].notification.message + '</p> <p> <i class="ti-calendar"></i> at ' + data[i].notification.create_date + '</p></div></a>';
                            } else {
                                lengthData++;
                                structureNotification += '<a href="' + data[i].link + '" class="card-body text-left notificationReceive" notification-id="' + data[i].notification.id + '" style="text-decoration: none;" onclick="viewToPage(this)"><div><img src="./Assets/img/webImg/waitingIconReceive.png" class="imgNotification"></div><div><p>' + data[i].notification.message + '</p> <p> <i class="ti-calendar"></i> at ' + data[i].notification.create_date + '</p></div></a>';
                            }
                            numberNotification.innerText = lengthData;
                            getNotification.innerHTML = structureNotification;
                        }
                    }
                    , error: function (xhr, status, error) {
                        $("#messageError").html("Wrong password, please try another password!");
                    }
                })
            }, 500);

        </script>
        <script>
            function viewToPage(element) {
                var getNotificationID = element.getAttribute("notification-id");
                console.log(getNotificationID);

                $.ajax({
                    url: "notificationController",
                    method: "POST",
                    data: {
                        notificationID: getNotificationID,
                        action: "seenReceive"
                    }, success: function (data) {
                    }, error: function (xhr, status, error) {
                        $("#messageError").html("Wrong password, please try another password!");
                    }
                })
            }
        </script>
    </body>
</html>
