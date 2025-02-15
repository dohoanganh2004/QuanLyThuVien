<%-- 
    Document   : chinhsuanhanvien
    Created on : Oct 28, 2024, 5:22:25 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Thông Tin Nhân Viên</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            border: 1px solid #ced4da;
            border-radius: 8px;
            background-color: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h3 {
            margin-bottom: 20px;
            color: #495057;
        }
        label {
            font-weight: bold;
            color: #495057;
        }
        .form-control {
            margin-bottom: 15px;
        }
        .error-message {
            color: red;
            font-weight: bold;
            margin-top: 10px;
        }
        .btn-primary, .btn-secondary {
            width: 100px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <form action="updatenhanvien" method="post">
            <h3>Thông Tin Nhân Viên</h3>

            <div class="form-group">
                <label for="maNhanVien">Mã Nhân Viên:</label>
                <input type="text" id="maNhanVien" name="maNhanVien" class="form-control" value="${nv.manhanvien}" readonly>

                <label for="hoVaTen">Họ và Tên:</label>
                <input type="text" id="hoVaTen" name="hoVaTen" class="form-control" value="${nv.hovaten}">

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" class="form-control" value="${nv.email}">

                <label for="soDienThoai">Số Điện Thoại:</label>
                <input type="text" id="soDienThoai" name="soDienThoai" class="form-control" value="${nv.sdt}">

                <label for="ngaySinh">Ngày Sinh:</label>
                <input type="date" id="ngaySinh" name="ngaySinh" class="form-control" value="${nv.ngaysinh}">
            </div>

            <c:if test="${requestScope.error != null}">
                <div class="error-message">${requestScope.error}</div>
            </c:if>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Lưu</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
