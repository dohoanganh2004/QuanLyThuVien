<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password Result</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                height: 100vh;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .result-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
                width: 100%;
                max-width: 350px;
                box-sizing: border-box;
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            p {
                font-size: 16px;
                text-align: center;
            }
            a {
                display: block;
                text-align: center;
                margin-top: 20px;
                color: #4CAF50;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="result-container">
            <h2>Forgot Password Result</h2>

            <p><c:if test="${requestScope.pass != null}">
                    Mật khẩu của bạn là: ${requestScope.pass.taikhoan.matkhau}
                </c:if></p>
            <p><a href="login.jsp">Back to Login</a></p>
        </div>
    </body>
</html>
