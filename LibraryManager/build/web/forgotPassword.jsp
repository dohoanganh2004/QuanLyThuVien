<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Forgot Password</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .forgot-password-container {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
                width: 350px;
                margin: 0 20px;
            }
            .forgot-password-container h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .forgot-password-container label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .forgot-password-container input[type="text"] {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .forgot-password-container input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                border: none;
                color: white;
                border-radius: 5px;
                cursor: pointer;
            }
            .forgot-password-container input[type="submit"]:hover {
                background-color: #45a049;
            }
            .forgot-password-container .links {
                display: flex;
                justify-content: space-between;
                margin-top: 10px;
            }
            .forgot-password-container .links a {
                text-decoration: none;
                color: #4CAF50;
                font-size: 14px;
            }
            .forgot-password-container .links a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="forgot-password-container">
            <h2>Forgot Password</h2>
            <form action="forget" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>

                <label for="card">Library Card ID:</label>
                <input type="text" id="card" name="card" required>
               
                <c:if test="${requestScope.msg!=null}">
                    ${requestScope.msg}
                </c:if>
                <input type="submit" value="Submit">
            </form>

            <div class="links">
                <a href="login">Back to Login</a>
            </div>
        </div>
    </body>
</html>
