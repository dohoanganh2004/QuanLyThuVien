<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Tài Khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f3; /* Màu nền nhạt */
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
        }
        .form-container {
            max-width: 600px;
            margin: auto;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
            transition: transform 0.3s;
        }
        .form-container:hover {
            transform: scale(1.02); /* Hiệu ứng phóng to nhẹ khi di chuột */
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }
        input[type="text"]:focus, input[type="password"]:focus, select:focus {
            border-color: #4CAF50; /* Đổi màu biên khi focus */
            outline: none;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
            transform: translateY(-2px); /* Hiệu ứng nổi lên khi di chuột */
        }
        .back-button {
            display: inline-block;
            margin-bottom: 20px;
            color: green;
            text-decoration: none;
            font-size: 16px;
            border: 1px solid green;
            padding: 8px 12px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .back-button:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
    <a href="quanlitaikhoan" class="back-button">Trở về</a>
    <div class="form-container">
        <h2>Cập Nhật Tài Khoản</h2>
        <form action="capnhattaikhoan" method="post">
            <input type="hidden" name="mataikhoan" value="${taiKhoan.mataikhoan}"/>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${taiKhoan.username}" required/>

            <label for="matkhau">Mật khẩu:</label>
            <input type="password" id="matkhau" name="matkhau" value="${taiKhoan.matkhau}" required/>

            <label for="avatar">Avatar:</label>
            <input type="text" id="avatar" name="avatar" value="${taiKhoan.avartar}" />

            <label for="quyenhan">Quyền hạn:</label>
            <select id="quyenhan" name="quyenhan">
                <option value="1" <c:if test="${taiKhoan.quenhan.id == 1}">selected</c:if>>Admin</option>
                <option value="2" <c:if test="${taiKhoan.quenhan.id == 2}">selected</c:if>>Nhân Viên</option>
                <option value="3" <c:if test="${taiKhoan.quenhan.id == 3}">selected</c:if>>Độc Giả</option>
            </select>

            <label for="manhanvien">Mã Nhân Viên:</label>
            <input type="text" id="manhanvien" name="manhanvien" value="${taiKhoan.nhanvien != null ? taiKhoan.nhanvien.manhanvien : ''}" />

            <label for="the">Thẻ Thư Viện:</label>
            <input type="text" id="the" name="the" value="${taiKhoan.the != null ? taiKhoan.the.sothe : ''}" />

            <input type="submit" value="Cập nhật"/>
        </form>
    </div>
</body>
</html>
