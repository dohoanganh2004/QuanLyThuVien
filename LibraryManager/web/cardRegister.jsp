<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .register-container {
        background-color: #ffffff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        width: 100%;
        max-width: 600px;
        margin: 0 auto;
    }

    .register-container h2 {
        text-align: left;
        margin-bottom: 20px;
    }

    .register-container label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .register-container input[type="text"],
    .register-container input[type="date"],
    .register-container select {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    .register-container input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        border: none;
        color: white;
        border-radius: 5px;
        cursor: pointer;
    }

    .register-container input[type="submit"]:hover {
        background-color: #45a049;
    }

    .register-container p {
        margin-top: 10px;
    }
</style>

<div class="register-container">
    <h2>Làm Thẻ Thư Viện</h2>
    <form action="taothe" method="post">
        <label for="name">Họ và Tên:</label>
        <input type="text" id="name" name="name" value="${requestScope.name}" required>

        <label for="sdt">SĐT:</label>
        <input type="text" id="sdt" name="sdt" value="${requestScope.sdt}">

        <label for="CCCD">Mã độc giả (CCCD):</label>
        <input type="text" id="CCCD" name="CCCD" value="${requestScope.CCCD}" required>

        <label for="gender">Giới tính:</label>
        <select id="gender" name="gender" required>
            <option value="" ${requestScope.gender == null ? "selected" : ""}>Chọn giới tính</option>
            <option value="Nam" ${requestScope.gender == "Nam" ? "selected" : ""}>Nam</option>
            <option value="Nữ" ${requestScope.gender == "Nữ" ? "selected" : ""}>Nữ</option>
            <option value="Khác" ${requestScope.gender == "Khác" ? "selected" : ""}>Khác</option>
        </select>

        <label for="ngaysinh">Ngày sinh:</label>
        <input type="date" id="ngaysinh" name="ngaysinh" >

        <label for="place">Địa chỉ:</label>
        <input type="text" id="place" name="place" value="${requestScope.place}" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${requestScope.email}">

        <label for="card">Mã thẻ:</label>
        <input type="text" id="card" name="card" value="${requestScope.card}">

        <label for="ngayhet">Ngày hết hạn:</label>
        <input type="date" id="ngayhet" name="ngayhet" >

        <!-- Thêm trường chọn Trạng Thái -->
        <label for="trangthai">Trạng Thái:</label>
        <select id="trangthai" name="trangthai" required>
            <option value="0" ${requestScope.trangthai == "0" ? "selected" : ""}>Inactive</option>
            <option value="1" ${requestScope.trangthai == "1" ? "selected" : ""}>Active</option>
        </select>

        <button type="submit">Đăng ký</button>
       
    </form>
            <h3 style="color:red">${requestScope.msg}</h3>
            
        <a href="quanlithe">Back</a>
</div>
