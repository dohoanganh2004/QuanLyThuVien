<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Check if user is logged in with JSTL --%>
<c:if test="${empty sessionScope.account}">
    <c:redirect url="home"/>  <!-- Redirect to homepage if not logged in -->
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
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
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .content {
            width: 600px;
            text-align: center;
        }
        .grid-container {
            display: flex;
            justify-content: center;
            gap: 10px;
            flex-wrap: wrap;
        }
        .grid-item {
            width: 100px;
            height: 100px;
            display: flex;
            justify-content: center;
            align-items: center;
            background: #e9ecef;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
        }
        .grid-item a {
            color: #007bff;
            text-decoration: none;
        }
        .grid-item a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <jsp:include page="menuadmin.jsp" />

    <div class="container">
        <div class="content">
            <h1>Admin Dashboard</h1>
            <div class="grid-container">
                <div class="grid-item"><a href="quanlisach">Quản Lý Sách</a></div>
                <div class="grid-item"><a href="quanlidocgia">Quản Lý Độc Giả</a></div>
                <div class="grid-item"><a href="cardRegister.jsp">Tạo thẻ</a></div>
               
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
