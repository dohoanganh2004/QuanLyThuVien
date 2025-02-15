<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Tài Khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        a {
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
            transition: color 0.3s;
        }
        a:hover {
            color: #45a049;
        }
        img {
            border-radius: 50%; /* Làm cho avatar tròn */
        }
        .back-button {
            display: inline-block;
            margin: 20px 0;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .back-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <a href="adminpage.jsp" class="back-button">Trở về</a>
    <h2>Danh Sách Tài Khoản</h2>
    <table>
        <thead>
            <tr>
                <th>Mã Tài Khoản</th>
                <th>Tên Đăng Nhập</th>
                 <th>Mật Khẩu</th>
                <th>Avatar</th>
                <th>Quyền Hạn</th>
                <th>Mã Thẻ</th>
                
                <th>Mã Nhân Viên</th>
                <th>Thao Tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tk" items="${dsTaikhoan}">
                <tr>
                    <td>${tk.mataikhoan}</td>
                    <td>${tk.username}</td>
                     <td>${tk.matkhau}</td>
                    <td><img src="${tk.avartar}" alt="Avatar" width="50" height="50"></td>
                    <td>${tk.quenhan.tenquyen}</td>
                    <td>
                        <c:choose>
                            <c:when test="${tk.the != null}">
                                ${tk.the.sothe}
                            </c:when>
                            <c:otherwise>
                                Không có
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${tk.nhanvien != null}">
                                ${tk.nhanvien.manhanvien}
                            </c:when>
                            <c:otherwise>
                                Không có
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="capnhattaikhoan?mataikhoan=${tk.mataikhoan}">Cập Nhật Tài Khoản</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
