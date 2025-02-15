<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .navbar {
                overflow: hidden;
                background-color: #333;
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 0 20px;
            }
            .nav-links {
                display: flex;
            }
            .nav-links a {
                color: #f2f2f2;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
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
                top: 50px; /* Điều chỉnh để đặt vị trí dưới ảnh đại diện */
                right: 0;
                background-color: #f9f9f9;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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
            window.onclick = function (event) {
                if (!event.target.matches('.avatar')) {
                    var dropdowns = document.getElementsByClassName("account-dropdown");
                    for (var i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.style.display === "block") {
                            openDropdown.style.display = "none";
                        }
                    }
                }
            }
        </script>
    </head>
    <body>
        <div class="navbar">
            <div class="nav-links">
                <a href="homepage.jsp">Home</a>
                  <a href="aboutus">About us</a>
                <a href="sach">Book</a>
                

                <c:if test="${sessionScope.account != null}">
                    <!-- Avatar Image -->
                    <img src="${sessionScope.account.avartar}" class="avatar" onclick="toggleDropdown()">
                    <!-- Account Dropdown positioned directly below the avatar -->
                    <div id="accountDropdown" class="account-dropdown">
                        <a href="profile">Quản lý thông tin cá nhân</a>
                        <a href="ticket">Phiếu Mượn</a>
                        
                        <!-- Hiển thị liên kết Quản lý chỉ nếu role = 1 -->
                        <c:if test="${sessionScope.account.quenhan.id == 1}">
                            <a href="adminpage.jsp">Quản lý</a>
                        </c:if>

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
