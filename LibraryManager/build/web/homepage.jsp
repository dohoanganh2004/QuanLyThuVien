<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Homepage</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-image: url('image/anhnen.jpg'); /* Thay 'your-image.jpg' bằng tên file của bạn */
                background-size: cover; /* Để hình ảnh bao phủ toàn bộ trang */
                background-position: center; /* Để hình ảnh căn giữa */
            }
            .header {
                background-color: rgba(76, 175, 80, 0.8); /* Thay đổi màu nền với độ trong suốt */
                color: white;
                padding: 15px 20px;
                text-align: center;
                font-size: 24px;
            }
            .navbar {
                overflow: hidden;
                background-color: rgba(51, 51, 51, 0.8); /* Thay đổi màu nền với độ trong suốt */
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 0 20px;
            }
            .nav-links {
                display: flex; /* Đặt các liên kết theo hàng ngang */
            }
            .nav-links a {
                color: #f2f2f2;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
            }
            .nav-links a:hover {
                background-color: #ddd;
                color: black;
            }
            .navbar form {
                display: flex; /* Đặt form tìm kiếm theo hàng ngang */
                margin-left: auto; /* Đẩy form về phía bên phải */
            }
            .navbar input[type="text"] {
                padding: 10px;
                border: none;
                border-radius: 5px;
                margin-right: 10px;
            }
            .navbar input[type="submit"] {
                padding: 10px 20px;
                background-color: #4CAF50;
                border: none;
                color: white;
                border-radius: 5px;
                cursor: pointer;
            }
            .navbar input[type="submit"]:hover {
                background-color: #45a049;
            }
            .content {
                padding: 20px;
                color: white; /* Đổi màu chữ cho dễ nhìn trên nền tối */
            }
            .footer {
                background-color: rgba(51, 51, 51, 0.8); /* Thay đổi màu nền với độ trong suốt */
                color: white;
                text-align: center;
                padding: 10px;
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
            }
        </style>
    </head>
    <body>

        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />

        <!-- Content Section -->
        <div class="content">
            <h2>Welcome!</h2>
            <p>This is the homepage of our website. You can navigate to different sections using the menu above.</p>
            <p>Feel free to explore and learn more about what we have to offer.</p>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
