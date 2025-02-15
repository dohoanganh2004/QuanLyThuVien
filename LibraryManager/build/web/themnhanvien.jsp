<%-- 
    Document   : themnhanvien
    Created on : Oct 21, 2024, 9:29:44 PM
    Author     : PC
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Nhân Viên</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            .container {
                display: flex;
                justify-content: center;
                align-items: flex-start;
                margin: 20px auto;
                background: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 20px;
                max-width: 800px;
            }

            .form-group {
                flex: 1;
                margin: 10px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #555;
            }

            input, select {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
            }

            input[type="file"] {
                padding: 5px;
            }

            input[type="submit"] {
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover {
                background-color: #218838;
            }

            .error-message {
                color: red;
                text-align: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="left.jsp" />
        <h2>Thêm Nhân Viên</h2>
        <form action="themnhanvien" method="post" >
           
                <div class="form-group">
                    <h3>Thông Tin Nhân Viên</h3>
                    <label for="maNhanVien">Mã Nhân Viên:</label>
                    <input type="text" id="maNhanVien" name="maNhanVien" required>

                    <label for="hoVaTen">Họ và Tên:</label>
                    <input type="text" id="hoVaTen" name="hoVaTen" required>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" >

                    <label for="soDienThoai">Số Điện Thoại:</label>
                    <input type="text" id="soDienThoai" name="soDienThoai">

                    <label for="ngaySinh">Ngày Sinh:</label>
                    <input type="date" id="ngaySinh" name="ngaySinh" >


                   
            </div>
                <c:if test="${requestScope.error != null}">
                ${requestScope.error}
            </c:if>
            <input type="submit" value="Thêm Nhân Viên">
            
        </form>
        <h3 class="error-message">${requestScope.error}</h3>
    </body>
    <jsp:include page="footer.jsp" />
</html>
