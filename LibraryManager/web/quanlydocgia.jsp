<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quản lý Độc Giả</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: center;
        }
        td {
            text-align: center;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        button {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            margin-right: 5px;
        }
        button a {
            color: white;
            text-decoration: none;
        }
        button:hover {
            background-color: #d32f2f;
        }
        .add-button {
            display: inline-block;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            margin-top: 10px;
        }
        .add-button:hover {
            background-color: #45a049;
        }
    </style>
    <script type="text/javascript">
        function doDelete(madocgia) {
            if (confirm("Bạn có muốn xóa độc giả này không?")) {
                window.location = "xoadocgia?madocgia=" + madocgia;
            }
        }
    </script>
</head>
<body>
    <h2>Danh Sách Độc Giả</h2>
    
    <!-- Nút thêm mới độc giả -->
    <a href="themmoidocgia.jsp" class="add-button">Thêm Mới Độc Giả</a>
    <a href="adminpage.jsp" class="add-button">Back</a>
    <table>
        <tr>
            <th>Mã Độc Giả</th>
            <th>Tên Độc Giả</th>
            <th>Giới Tính</th>
            <th>Địa Chỉ</th>
            <th>Email</th>
            <th>SDT</th>
            <th>Ngày Sinh</th>
            <th>Thao Tác</th>
        </tr>
        
        <c:forEach items="${requestScope.listDg}" var="docgia">
            <tr>
                <td>${docgia.madocgia}</td>
                <td>${docgia.ten}</td>
                <td>${docgia.gioitinh}</td>
                <td>${docgia.diachi}</td>
                <td>${docgia.email}</td>
                <td>${docgia.sdt}</td>
                <td>${docgia.ngaysinh}</td>
                
                <!-- Cột chứa các nút Thao Tác -->
                <td>
                    <button onclick="doDelete('${docgia.madocgia}')">Xóa</button>
                    <button><a href="chinhsuadocgia?madocgia=${docgia.madocgia}">Chỉnh Sửa</a></button>
                    
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
