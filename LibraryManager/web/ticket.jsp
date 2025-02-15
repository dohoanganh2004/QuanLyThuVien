<%--      Document   : ticket     Created on : Oct 21, 2024, 5:13:31 PM     Author     : thang --%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html> 
<html lang="vi"> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Danh Sách Sách Mượn</title> 
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                color: #333;
                margin: 0;
                padding: 20px;
            }
            h1 {
                text-align: center;
                color: #4CAF50;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            a {
                color: #4CAF50;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            .total {
                font-weight: bold;
                text-align: center;
                margin-top: 20px;
            }
            .back-link {
                display: block;
                text-align: center;
                margin-top: 20px;
            }
        </style>
        <script type="text/javascript"> 
            function doTraSach(id, soluong) { 
                if (confirm("Bạn có muốn trả sách với ID = " + id + "?")) { 
                    window.location = "trasach?id=" + id + "&soluong=" + soluong; 
                } 
            } 
        </script> 
    </head> 
    <body> 
        <center> 
            <h1>Danh Sách Sách Mượn</h1> 
            <table border="1"> 
                <thead> 
                    <tr> 
                        <th>Mã Sách</th> 
                        <th>Tên Sách</th> 
                        <th>Tác Giả</th> 
                        <th>Ngày Mượn</th> 
                        <th>Ngày Hết Hạn</th> 
                        <th>Số Lượng</th> 
                        <th>Trả Sách</th> 
                    </tr> 
                </thead> 
                <tbody> 
                    <!-- Check if the list is empty --> 
                    <c:if test="${mtsct == null || mtsct.isEmpty()}"> 
                        <tr> 
                            <td colspan="7">Chưa Mượn Sách</td> 
                        </tr> 
                    </c:if> 

                    <!-- If list is not empty, iterate and display books --> 
                    <c:forEach items="${requestScope.mtsct}" var="mtsct"> 
                        <tr> 
                            <td>${mtsct.sach.masach}</td> 
                            <td>${mtsct.sach.tensach}</td> 
                            <td>${mtsct.sach.tacgia}</td> 
                            <td>${mtsct.ngaymuon}</td> 
                            <td>${mtsct.ngayhentra}</td> 
                            <td>${mtsct.soluong}</td> 
                            <td><a href="#" onclick="doTraSach('${mtsct.sach.masach}', ${mtsct.soluong})">Nhấn Để Trả Sách</a></td> 
                        </tr> 
                    </c:forEach> 

                    <!-- Calculate total number of books borrowed --> 
                    <c:set var="totalBooks" value="0" scope="page"/> 
                    <c:forEach items="${requestScope.mtsct}" var="mtsct"> 
                        <c:set var="totalBooks" value="${totalBooks + mtsct.soluong}" /> 
                    </c:forEach> 
                    <tr> 
                        <td colspan="7" class="total">Tổng số sách đang mượn: ${totalBooks}</td> 
                    </tr> 
                </tbody> 
            </table> 
            <div class="back-link">
                <a href="homepage.jsp">Quay lại Trang Chính</a> 
            </div>
        </center> 
    </body> 
</html>
