<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
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
        .login-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 350px;
            margin: 0 20px;
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-container label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .login-container .links {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }
        .login-container .links a {
            text-decoration: none;
            color: #4CAF50;
            font-size: 14px;
        }
        .login-container .links a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>User Login</h2>
        <form action="logindocgia" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${cookie.cusername.value}" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${cookie.cpassword.value}" required>

            <label for="mathe">Ma The Thu Vien:</label>
            <input type="text" id="mathe" name="mathe" value="${cookie.cmathe.value}" required>
            
            <p class="error-message"><c:if test="${requestScope.error != null}">${requestScope.error}</c:if></p>

            <label>
                <input type="checkbox" name="rememberMe" ${(cookie.cremember != null ? 'checked' : '')}>
                Remember me
            </label>
            <input type="submit" value="Login">
        </form>

        <div class="links">
               <a href="#">Fogot password?</a>
            <a href="taikhoandocgia.jsp">Dang ky</a>
        </div>
    </div>

    <div class="login-container">
        <h2>Employee Login</h2>
        <form action="loginnhanvien" method="post">
            <label for="empUsername">Username:</label>
            <input type="text" id="empUsername" name="username" value="${cookie.cusername.value}" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${cookie.cpassword.value}" required>

            <label for="manhanvien">Employee Card ID:</label>
            <input type="text" id="manhanvien" name="manhanvien" value="${cookie.cmanhanvien.value}" required>
            
            <p class="error-message"><c:if test="${requestScope.error1 != null}">${requestScope.error1}</c:if></p>

            <label>
                <input type="checkbox" name="rememberMe" ${(cookie.cremember != null ? 'checked' : '')}>
                Remember me
            </label>
            <input type="submit" value="Login">
        </form>

        <div class="links">
            <a href="#">Fogot password?</a>
            <a href="register.jsp">Dang ky</a>
        </div>
    </div>
</body>
</html>
