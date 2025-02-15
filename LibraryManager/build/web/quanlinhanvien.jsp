<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quản Lý Nhân Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }
        /* Đảm bảo header, menu, footer có chiều rộng bằng màn hình */
        .header, .menu, .footer {
            width: 100%;
            padding: 10px 0;
            text-align: center;
            background-color: #4CAF50;
            color: white;
            font-size: 18px;
        }
        .container {
            width: 90%; /* Chiều rộng gần bằng màn hình */
            max-width: 1200px; /* Giới hạn chiều rộng tối đa */
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        button, a.button {
            text-decoration: none;
            color: white;
            background-color: #4CAF50;
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .actions {
            display: flex;
            gap: 8px;
            justify-content: center;
        }
        .header-link {
            display: flex;
            justify-content: flex-end;
            margin-top: 10px;
        }
    </style>
    <script type="text/javascript">
        function doDelete(manhanvien) {
            if (confirm("Bạn có muốn xóa nhân viên này không?")) {
                window.location = "xoanhanvien?manhanvien=" + manhanvien;
            }
        }
    </script>
</head>
<body>
    <div class="header">
        <jsp:include page="header.jsp" />
         
    </div>
    <div class="menu">
        <jsp:include page="menu.jsp" />
    </div>

    <!-- Chỉ giữ bảng trong hộp -->
    <div class="container">
        <h2>Quản Lý Nhân Viên</h2>
        <table>
            <tr>
                <th>Mã Nhân Viên</th>
                <th>Họ Tên Nhân Viên</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Ngày Sinh</th>
                <th>Hành Động</th>
            </tr>
            <c:forEach items="${requestScope.dsNhanVien}" var="nv">
                <tr>
                    <td>${nv.manhanvien}</td>
                    <td>${nv.hovaten}</td>
                    <td>${nv.email}</td>
                    <td>${nv.sdt}</td>
                    <td>${nv.ngaysinh}</td>
                    <td class="actions">
                        <button onclick="doDelete('${nv.manhanvien}')">Xóa</button>
                        <a class="button" href="updatenhanvien?manhanvien=${nv.manhanvien}">Chỉnh sửa</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="header-link">
            <a class="button" href="themnhanvien.jsp">Thêm Nhân Viên</a>
        </div>
    </div>
    
    <div class="footer">
        <jsp:include page="footer.jsp" />
    </div>
</body>
</html>
