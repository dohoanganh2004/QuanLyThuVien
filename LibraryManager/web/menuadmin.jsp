<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .navbar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            background-color: #333;
            padding: 0 20px;
            position: relative;
        }
        .nav-links {
            display: flex;
            align-items: center;
        }
        .nav-links a {
            color: #f2f2f2;
            padding: 14px 20px;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .nav-links a:hover {
            background-color: #ddd;
            color: black;
        }
        .navbar form {
            display: flex;
            margin-left: auto;
        }
        .navbar input[type="text"] {
            padding: 10px;
            border: none;
            border-radius: 5px;
            margin-right: 10px;
        }
        .navbar input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .navbar input[type="submit"]:hover {
            background-color: #45a049;
        }
        .avatar {
            cursor: pointer;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            position: relative;
        }
        .account-dropdown {
            display: none;
            position: absolute;
            top: 50px; /* Vị trí dưới ảnh đại diện */
            right: 0;
            background-color: #f9f9f9;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
            padding: 10px;
            border-radius: 5px;
            z-index: 1;
        }
        .account-dropdown a {
            display: block;
            color: #333;
            padding: 8px 16px;
            text-decoration: none;
        }
        .account-dropdown a:hover {
            background-color: #ddd;
        }
    </style>
    <script>
        function toggleDropdown() {
            var dropdown = document.getElementById("accountDropdown");
            dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
        }
        window.onclick = function(event) {
            if (!event.target.matches('.avatar')) {
                var dropdown = document.getElementById("accountDropdown");
                if (dropdown.style.display === "block") {
                    dropdown.style.display = "none";
                }
            }
        }
    </script>
</head>
<body>
    <div class="navbar">
        <div class="nav-links">
            <a href="homepage.jsp">Home</a>
            <a href="adminpage.jsp">Trang Quản Lý</a>
            
            <a href="sach">Book</a>

            <c:if test="${sessionScope.account != null}">
                <img src="${sessionScope.account.avartar}" class="avatar" onclick="toggleDropdown()">
                <div id="accountDropdown" class="account-dropdown">
                    <a href="profile">Quản lý thông tin cá nhân</a>
                    <a href="ticket">Phiếu Mượn</a>
                    <a href="logout">Log Out</a>
                </div>
            </c:if>

            <c:if test="${sessionScope.account == null}">
                <a href="loginnhanvien">Login</a>
            </c:if>
        </div>

        <form action="timkiemsach" method="post">
            <input type="text" name="txt" placeholder="Search...">
            <input type="submit" value="Search">
        </form>
    </div>
</body>
</html>
