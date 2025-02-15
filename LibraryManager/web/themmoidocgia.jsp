<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Độc Giả</title>
    <style>
        /* Đặt font và màu nền */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        /* Container */
        .register-container {
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 500px;
            padding: 30px 40px;
            text-align: center;
        }
        /* Tiêu đề */
        h2 {
            font-size: 26px;
            color: #333;
            margin-bottom: 20px;
            font-weight: 600;
        }
        /* Nhãn và các trường nhập liệu */
        label {
            font-size: 14px;
            color: #555;
            text-align: left;
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }
        input, select {
            width: 100%;
            padding: 12px 15px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 15px;
            transition: 0.3s ease;
            box-sizing: border-box;
        }
        input:focus, select:focus {
            border-color: #4CAF50;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.3);
            outline: none;
        }
        /* Nút đăng ký */
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 0;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            width: 100%;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        /* Thông báo lỗi */
        .msg {
            color: #d9534f;
            font-size: 14px;
            margin-top: 15px;
        }
        /* Liên kết quay lại */
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #4CAF50;
            font-weight: bold;
            text-decoration: none;
            transition: color 0.3s;
        }
        .back-link:hover {
            color: #388E3C;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Đăng Ký Độc Giả</h2>
        <form action="themdocgia" method="post">
            <label for="madocgia">Mã độc giả:</label>
            <input type="text" id="madocgia" name="madocgia" placeholder="VD: DG001" required>

            <label for="name">Họ và Tên:</label>
            <input type="text" id="name" name="name" required>

            <label for="gender">Giới tính:</label>
            <select id="gender" name="gender" required>
                <option value="" disabled selected>Chọn giới tính</option>
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
                <option value="Other">Khác</option>
            </select>

            <label for="ngaysinh">Ngày sinh:</label>
            <input type="date" id="ngaysinh" name="ngaysinh" required>

            <label for="place">Địa chỉ:</label>
            <input type="text" id="place" name="place" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="sdt">Số điện thoại:</label>
            <input type="text" id="sdt" name="sdt" required>

            <input type="submit" value="Đăng Ký">
        </form>

        <div class="msg">
            <c:if test="${not empty msg}">${msg}</c:if>
        </div>
        
        <a href="quanlydocgia" class="back-link">Quay lại trang quản lý</a>
    </div>
</body>
</html>
