<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Sách</title>
        <script type="text/javascript">
            function doDelete(masach) {
                if (confirm("Bạn có muốn xóa sách này không?")) {
                    window.location = "xoasach?masach=" + masach;
                }
            }
        </script>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            .container {
                width: 90%; /* Chiều rộng tổng thể */
                max-width: 1200px; /* Chiều rộng tối đa */
                margin: 0 auto; /* Tự động căn giữa */
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            .add-book-btn {
                float: right;
                margin-bottom: 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .add-book-btn:hover {
                background-color: #45a049;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
                color: #333;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            img {
                width: 100px;
                height: auto;
                border-radius: 5px;
            }
            button {
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                padding: 5px 10px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            button:hover {
                background-color: #0056b3;
            }
            a {
                text-decoration: none;
                color: inherit;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="left.jsp" />
        <div class="container">
            <h1>Quản Lý Sách</h1>
            <form action="timkiemsach" method="get">
                <input type="text" name="txt" placeholder="Search..." style="padding: 10px; width: 70%;">
                <input type="submit" value="Search" style="padding: 10px;">
            </form>
            <button class="add-book-btn"><a href="themmoisach.jsp" style="color: white;">Thêm Sách</a></button>
            <table>
                <tr>
                    <th>Mã Sách</th>
                    <th>Tên Sách</th>
                    <th>Tác Giả</th>
                    <th>Thể Loại</th>
                    <th>Ảnh Bìa</th>
                    <th>Hành Động</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty requestScope.listS}">
                        <c:forEach items="${requestScope.listS}" var="s">
                            <tr>
                                <td>${s.masach}</td>
                                <td>${s.tensach}</td>
                                <td>${s.tacgia}</td>
                                <td>${s.theloai.tentheloai}</td>
                                <td><img src="${s.anhbia}" alt="Bìa sách: ${s.tensach}"/></td>
                                <td>
                                    <button onclick="doDelete('${s.masach}')">Xóa</button>
                                    <button><a href="chinhsuasach?masach=${s.masach}" style="color: white;">Chỉnh Sửa</a></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${requestScope.listsach}" var="s">
                            <tr>
                                <td>${s.masach}</td>
                                <td>${s.tensach}</td>
                                <td>${s.tacgia}</td>
                                <td>${s.theloai.tentheloai}</td>
                                <td><img src="${s.anhbia}" alt="Bìa sách: ${s.tensach}"/></td>
                                <td>
                                    <button onclick="doDelete('${s.masach}')">Xóa</button>
                                    <button><a href="chinhsuasach?masach=${s.masach}" style="color: white;">Chỉnh Sửa</a></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
