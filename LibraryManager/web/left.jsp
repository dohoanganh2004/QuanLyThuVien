<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Sách</title>
        <style>
            .left-menu {
                position: fixed; /* Đặt vị trí cố định */
                left: 0; /* Đưa menu sang bên trái */
                top: 0; /* Đặt từ đầu trang */
                width: 250px; /* Chiều rộng của menu */
                height: 100%; /* Đặt chiều cao 100% để kéo dài xuống */
                background-color: #f4f4f4; /* Màu nền cho menu */
                padding: 10px; /* Khoảng cách bên trong menu */
                box-shadow: 2px 0 5px rgba(0,0,0,0.1); /* Đổ bóng bên phải */
                transition: transform 0.3s ease; /* Thêm hiệu ứng trượt */
                transform: translateX(-100%); /* Khởi tạo menu bên ngoài màn hình */
                z-index: 1000; /* Đặt menu lên trên các phần khác */
            }

            /* Khi menu được hiển thị */
            .left-menu.show {
                transform: translateX(0); /* Đưa menu vào trong màn hình */
            }

            .menu-item {
                margin: 10px 0; /* Khoảng cách giữa các mục */
            }

            .menu-item a {
                display: block; /* Đưa đường dẫn thành ô có thể nhấn được */
                padding: 10px; /* Khoảng cách bên trong ô */
                background-color: #eaeaea; /* Màu nền cho mục */
                text-decoration: none; /* Bỏ gạch chân */
                color: #333; /* Màu chữ */
                border-radius: 5px; /* Bo góc */
                transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
            }

            .menu-item a:hover {
                background-color: #d4d4d4; /* Màu nền khi hover */
            }

            .container {
                margin-left: 260px; /* Để nội dung chính không bị che bởi menu */
            }

            .toggle-button {
                position: fixed; /* Đặt nút cố định */
                left: 10px; /* Đặt bên trái */
                top: 10px; /* Đặt từ đầu trang */
                padding: 10px 15px; /* Khoảng cách bên trong nút */
                background-color: #007BFF; /* Màu nền nút */
                color: white; /* Màu chữ */
                border: none; /* Bỏ viền */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ chuột khi hover */
            }

            .hide-menu-button {
                background-color: #dc3545; /* Màu nền cho nút ẩn menu */
                color: white; /* Màu chữ */
                border: none; /* Bỏ viền */
                padding: 5px 10px; /* Khoảng cách bên trong nút */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ chuột khi hover */
                margin-bottom: 10px; /* Khoảng cách dưới nút */
            }
        </style>
        <script type="text/javascript">
            function toggleMenu() {
                var menu = document.getElementById("left-menu");
                menu.classList.toggle("show");
                var button = document.getElementById("toggle-button");
                button.innerHTML = menu.classList.contains("show") ? "Ẩn Menu" : "Hiện Menu";
            }

            function hideMenu() {
                var menu = document.getElementById("left-menu");
                menu.classList.remove("show");
                document.getElementById("toggle-button").innerHTML = "Hiện Menu"; // Cập nhật văn bản nút
            }
        </script>
    </head>
    <body>
        <button id="toggle-button" class="toggle-button" onclick="toggleMenu()">Hiện Menu</button>
        <div id="left-menu" class="left-menu">
            <button class="hide-menu-button" onclick="hideMenu()">Ẩn Menu</button> <!-- Nút ẩn menu -->
            <div class="menu-item">

                <a href="quanlinhanvien">Quản Lý Nhân Viên</a>

            </div>
            <div class="menu-item">

                <a href="quanlidocgia">Quản Lý Độc Giả</a>

            </div>
            <div class="menu-item">

                <a href="quanlisach">Quản Lý Sách</a>

            </div>
            <div class="menu-item">

                <a href="quanlithe">Quản Lý The</a>

            </div>
             <div class="menu-item">

                <a href="quanlitaikhoan">Quản Lý Tai Khoan</a>

            </div>
           
        </div>
        <div class="container">
            <!-- Nội dung chính của bạn ở đây -->
        </div>
    </body>
</html>
