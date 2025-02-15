<%@page import="model.DocGia"%>
<%@page import="model.TheThuVien"%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Settings</title>
        <style>
            /* General Styling */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .profile-container {
                display: flex;
                max-width: 1000px;
                margin: 50px auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
            }
            .profile-left {
                flex: 1;
                text-align: center;
                padding-right: 30px;
            }
            .profile-left img {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                object-fit: cover;
                margin-bottom: 15px;
            }
            .profile-left h2 {
                font-size: 24px;
                margin-bottom: 10px;
            }
            .profile-right {
                flex: 2;
            }
            .profile-right h2 {
                font-size: 24px;
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input[type="text"],
            .form-group select,
            .form-group input[type="date"] {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }
            .form-row {
                display: flex;
                justify-content: space-between;
            }
            .half-width {
                width: 48%;
            }
            .submit-button {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 15px;
            }
            .submit-button:hover {
                background-color: #45a049;
            }
            .links a {
                text-decoration: none;
                color: #007BFF;
            }
            .links a:hover {
                text-decoration: underline;
            }
            p[style*="color: red"] {
                color: red;
            }
            p[style*="color: green"] {
                color: green;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.acc != null}">
            <div class="profile-container">
                <div class="profile-left">
                    <img src="${sessionScope.acc.avartar}" alt="Profile Picture">
                    <h2>${sessionScope.acc.username}</h2>
                </div>
                <div class="profile-right">
                    <h2>Profile Settings</h2>
                    <form action="profile" method="post">
                        <div class="form-row">
                            <div class="form-group half-width">
                                <label for="firstName">Name</label>
                                <input type="text" id="firstName" name="name" value="${sessionScope.the.docgia.ten}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone">Mobile Number</label>
                            <input type="text" id="phone" name="phone" value="${requestScope.phone != null ? requestScope.phone : sessionScope.the.docgia.sdt}">
                        </div>
                        <div class="form-group">
                            <label for="cccd">CCCD:</label>
                            <input readonly type="text" id="cccd" name="cccd" value="${sessionScope.the.docgia.madocgia}">
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender:</label>
                            <select id="gender" name="gender">
                                <option value="${sessionScope.the.docgia.gioitinh}">${sessionScope.the.docgia.gioitinh}</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="birthdate">Birth Date:</label>
                            <!-- Read-only text input displaying formatted birthdate -->
                            <input readonly type="text" id="birthdate-text" value="<fmt:formatDate pattern='dd/MM/yyyy' value='${sessionScope.the.docgia.ngaysinh}'/>">
                            <!-- Editable date input (initially hidden) -->
                            <input type="date" id="birthdate-edit" name="bd" style="display: none;" value="${sessionScope.the.docgia.ngaysinh}">
                            <!-- Button to toggle edit mode -->
                            <button type="button" id="edit-birthdate-btn">Edit</button>
                        </div>
                        <div class="form-group">
                            <label for="state">Address:</label>
                            <input type="text" id="state" name="place" value="${sessionScope.the.docgia.diachi}">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" id="email" name="email" value="${requestScope.email != null ? requestScope.email : sessionScope.the.docgia.email}">
                        </div>
                        <div class="form-group">
                            <label for="card">Card Number:</label>
                            <input readonly type="text" id="card" name="card" value="${sessionScope.acc.the.sothe}">
                        </div>
                        <div class="form-group">
                            <label for="startDate">Start Date:</label>
                            <input readonly type="text" id="startDate" name="startdate" value="<fmt:formatDate pattern='dd/MM/yyyy' value='${sessionScope.acc.the.ngaycap}'/>">
                        </div>
                        <div class="form-group">
                            <label for="endDate">End Date:</label>
                            <input readonly type="text" id="endDate" name="enddate" value="<fmt:formatDate pattern='dd/MM/yyyy' value='${sessionScope.acc.the.ngayhethan}'/>">
                        </div>
                        <div class="form-row">
                            <div class="form-group half-width">
                                <label for="username">Username:</label>
                                <input type="text" id="username" name="username" value="${requestScope.username != null ? requestScope.username : sessionScope.acc.username}">
                            </div>
                            <div class="form-group half-width">
                                <label for="password">Password:</label>
                                <input type="text" id="password" name="password" value="${sessionScope.acc.matkhau}">
                            </div>
                        </div>
                        <!-- Error Message -->
                        <p style="color: red">
                            <c:if test="${requestScope.msg != null}">
                                ${requestScope.msg}
                            </c:if>
                        </p>
                        <p style="color: green">
                            <c:if test="${requestScope.msg2 != null}">
                                ${requestScope.msg2}
                            </c:if>
                        </p>
                        <input type="submit" value="Save Profile" class="submit-button">
                        <div class="links">
                            <a href="login">Back to Login</a>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        
        <script>
            // Toggle the visibility of the birthdate fields
            document.getElementById('edit-birthdate-btn').addEventListener('click', function () {
                var birthdateText = document.getElementById('birthdate-text');
                var birthdateEdit = document.getElementById('birthdate-edit');
                if (birthdateEdit.style.display === 'none') {
                    birthdateEdit.style.display = 'block';
                    birthdateText.style.display = 'none';
                    this.textContent = 'Cancel';
                } else {
                    birthdateEdit.style.display = 'none';
                    birthdateText.style.display = 'block';
                    this.textContent = 'Edit';
                }
            });
        </script>
    </body>
</html>
