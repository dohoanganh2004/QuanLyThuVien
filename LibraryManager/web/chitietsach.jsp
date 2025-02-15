<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Detail</title>
    <style>
        /* CSS cho bố cục */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .book-detail {
            display: flex;
            width: 100%;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .left {
            width: 35%;
            padding: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f5f5f5;
        }
        .left img {
            max-width: 100%;
            border-radius: 8px;
            border: 1px solid #ddd;
        }
        .right {
            width: 65%;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: none;
        }
        th {
            color: #333;
            font-weight: bold;
            text-align: right;
            width: 40%;
            padding-right: 20px;
            white-space: nowrap;
        }
        td {
            color: #555;
        }
        .footer {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 10px;
            margin-top: auto;
            width: 100%;
        }
        .borrow-form {
            margin-top: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .borrow-form input[type="text"] {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 80px;
        }
        .borrow-form input[type="submit"] {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        .borrow-form input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${not empty sessionScope.sachchitiet}">
                <div class="book-detail">
                    <div class="left">
                        <img src="${sachchitiet.anhbia}" alt="${sachchitiet.tensach}">
                    </div>
                    <div class="right">
                        <table>
                            <tr><th>Mã Sách</th><td>${sachchitiet.masach}</td></tr>
                            <tr><th>Tên Sách</th><td>${sachchitiet.tensach}</td></tr>
                            <tr><th>Mô Tả</th><td>${sachchitiet.mota}</td></tr>
                            <tr><th>Tác Giả</th><td>${sachchitiet.tacgia}</td></tr>
                            <tr><th>Thể Loại</th><td>${sachchitiet.theloai.tentheloai}</td></tr>
                            <tr><th>Nhà Xuất Bản</th><td>${sachchitiet.nxb}</td></tr>
                            <tr><th>Năm Xuất Bản</th><td>${sachchitiet.namxb}</td></tr>
                            <tr><th>Ngôn Ngữ</th><td>${sachchitiet.ngonngu}</td></tr>
                            <tr><th>Giá</th><td>${sachchitiet.gia}</td></tr>
                            <tr><th>Số Lượng</th><td>${sachchitiet.soluong}</td></tr>
                        </table>
                    </div>
                </div>
                <form action="ticket" method="POST" class="borrow-form">
                    <label>Số lượng mượn: 1</label>
                    <input type="hidden" name="amount" value="1">
                    <input type="submit" value="Mượn">
                    <c:if test="${requestScope.msg != null}">
                        <p class="error-message">${requestScope.msg}</p>
                    </c:if>
                </form>
            </c:when>
            <c:otherwise>
                <p>Không tìm thấy sách này.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
