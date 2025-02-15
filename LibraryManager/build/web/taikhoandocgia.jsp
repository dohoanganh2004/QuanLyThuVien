<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng Ký Độc Giả</title>
        <style>
            /* Đặt CSS như trong mã gốc */
            body {
                font-family: Arial, sans-serif;
                background-color: #eaeaea;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .register-container {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                width: 400px;
                max-height: 90vh;
                overflow-y: auto;
            }
            .register-container h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }
            .register-container label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #555;
            }
            .register-container input[type="text"],
            .register-container input[type="password"] {
                width: 100%;
                padding: 12px;
                margin: 10px 0;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
                transition: border-color 0.3s;
            }
            .register-container input[type="text"]:focus,
            .register-container input[type="password"]:focus {
                border-color: #4CAF50;
                outline: none;
            }
            .register-container input[type="submit"] {
                width: 100%;
                padding: 12px;
                background-color: #4CAF50;
                border: none;
                color: white;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s;
            }
            .register-container input[type="submit"]:hover {
                background-color: #45a049;
            }
            .message {
                color: red;
                text-align: center;
                margin: 10px 0;
            }
            .success-message {
                color: green;
                text-align: center;
                margin: 10px 0;
            }
        </style>
    </head>
    <body>
        <div class="register-container">
            <h2>Đăng Ký Độc Giả</h2>



            <form action="dangkitaikhoandocgia" method="post">
                <label for="username">Tên Đăng Nhập:</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Mật Khẩu:</label>
                <input type="password" id="password" name="password" required>

                <label for="cpassword">Xác Nhận Mật Khẩu:</label>
                <input type="password" id="cpassword" name="cpassword" required>

                <label for="maTheThuVien">Mã Thẻ Thư Viện:</label>
                <input type="text" id="maTheThuVien" name="maTheThuVien" required>

                <label for="avatar">Avatar:</label>
                <input type="text" id="avatar" name="avatar" required>

                <p class="message">
                    <c:if test="${requestScope.msg != null}">${requestScope.msg}</c:if>
                </p>
                <input type="submit" value="Đăng Ký">
            </form>

            <div class="links">
                <a href="loginnhanvien">Quay lại Đăng Nhập</a>
            </div>
        </div>
    </body>
</html>
