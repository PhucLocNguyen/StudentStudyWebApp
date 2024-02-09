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
        <% if (request.getAttribute(
                    "message") != null) { // đang bị lỗi này có giải pháp là bắt sự kiện submit form rồi gửi về bằng Ajax rồi trả dữ liệu json cho toastify %> 
        <script>
            Toastify({
                text: "<%= request.getAttribute("message")%>",
                duration: 3000,
                destination: "https://github.com/apvarun/toastify-js",
                newWindow: true,
                close: true,
                gravity: "top", // `top` or `bottom`
                position: "left", // `left`, `center` or `right`
                stopOnFocus: true, // Prevents dismissing of toast on hover
                style: {
                    background: "linear-gradient(to right, #00b09b, #96c93d)",
                },
                onClick: function () {} // Callback after click
            }).showToast();
        </script>
        <%}%>
    </body>
</html>
