<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý thẻ</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                color: #333;
                margin: 0;
                padding: 0;
            }
            h2 {
                text-align: center;
                color: #4CAF50;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            a {
                color: #4CAF50;
                text-decoration: none;
                font-weight: bold;
            }
            a:hover {
                text-decoration: underline;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 12px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .action-links {
                color: #4CAF50;
                font-weight: bold;
                font-size: 14px;
            }
            .action-links a {
                color: #4CAF50;
                margin-right: 8px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Danh sách thẻ thư viện</h2>
            <p><a href="cardRegister.jsp">Thêm Thẻ</a></p>
            <p><a href="adminpage.jsp">Back</a></p>
            <table>
                <tr>
                    <th>Mã Độc Giả</th>
                    <th>Mã Thẻ</th>
                    <th>Tên Độc Giả</th>
                    <th>Giới Tính</th>
                    <th>Địa Chỉ</th>
                    <th>Số Sách Đang Mượn</th>
                    <th>Ngày Cấp</th>
                    <th>Ngày Hết Hạn</th>
                    <th>Trạng Thái</th>
                    <th>Hành Động</th>
                </tr>
                <c:forEach items="${requestScope.dsThe}" var="the">
                    <tr>
                        <td>${the.docgia.madocgia}</td>
                        <td>${the.sothe}</td>
                        <td>${the.docgia.ten}</td>
                        <td>${the.docgia.gioitinh}</td>
                        <td>${the.docgia.diachi}</td>
                        <td>${the.sosachdangmuon}</td>
                        <td>${the.ngaycap}</td>
                        <td>${the.ngayhethan}</td>
                        <td>
                            <c:choose>
                                <c:when test="${the.trangthai == 0}">Inactive</c:when>
                                <c:when test="${the.trangthai == 1}">Active</c:when>
                                <c:otherwise>Unknown</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="action-links">
                            <a href="capnhatthe?sothe=${the.sothe}">Cập nhật</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
