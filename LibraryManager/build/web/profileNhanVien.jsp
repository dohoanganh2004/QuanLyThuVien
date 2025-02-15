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
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            /* Profile Container */
            .profile-container {
                display: flex;
                max-width: 1000px;
                margin: 50px auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
            }

            /* Profile Left Section */
            .profile-left {
                flex: 1;
                padding-right: 30px;
            }

            .profile-left h2 {
                font-size: 28px;
                margin-bottom: 20px;
            }

            /* Profile Right Section */
            .profile-right {
                flex: 2;
            }

            .profile-right h2 {
                font-size: 24px;
                margin-bottom: 15px;
            }

            /* Form Styling */
            form {
                width: 100%;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            .form-group input[type="text"] {
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

            /* Submit Button */
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

            /* Links */
            .links {
                margin-top: 15px;
            }

            .links a {
                text-decoration: none;
                color: #007BFF;
            }

            .links a:hover {
                text-decoration: underline;
            }

            /* Error/Success Messages */
            p {
                font-size: 14px;
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
        <c:if test="${sessionScope.empAcc != null}">
            <div class="profile-container">
                <div class="profile-left">

                    <h2>${empAcc.hovaten}</h2>
                </div>
                <div class="profile-right">
                    <h2>Profile Settings</h2>
                    <form action="profileemp" method="post">
                        <div class="form-row">
                            <div class="form-group half-width">
                                <label for="firstName">Name</label>
                                <input type="text" id="firstName" name="name" value="${empAcc.hovaten}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone">Mobile Number</label>
                            <input type="text" id="phone" name="phone" value="${requestScope.phone != null ? requestScope.phone : empAcc.sdt}">
                        </div>
                        <div class="form-group">
                            <label for="address1">Mã nhân viên:</label>
                            <input readonly type="text" id="address1" name="id" value="${empAcc.manhanvien}">
                        </div>

                        <div class="form-group">
                            <label for="birthdate">Birth Date:</label>
                            <!-- Read-only text input displaying formatted birthdate -->
                            <input readonly type="text" id="birthdate-text" value="<fmt:formatDate pattern='dd/MM/yyyy' value='${empAcc.ngaysinh}'/>">

                            <!-- Editable date input (initially hidden) -->
                            <input type="date" id="birthdate-edit" name="bd" style="display: none;" value="${empAcc.ngaysinh}">

                            <!-- Button to toggle edit mode -->
                            <button type="button" id="edit-birthdate-btn">Edit</button>
                        </div>
                        <script>
// Toggle the visibility of the birthdate fields
                            document.getElementById('edit-birthdate-btn').addEventListener('click', function () {
                                var birthdateText = document.getElementById('birthdate-text');
                                var birthdateEdit = document.getElementById('birthdate-edit');

                                // Check if the date input is currently hidden
                                if (birthdateEdit.style.display === 'none') {
                                    // Show the editable date input and hide the read-only text
                                    birthdateEdit.style.display = 'block';
                                    birthdateText.style.display = 'none';
                                    this.textContent = 'Cancel'; // Change button text to "Cancel"
                                } else {
                                    // Revert to the read-only text and hide the date input
                                    birthdateEdit.style.display = 'none';
                                    birthdateText.style.display = 'block';
                                    this.textContent = 'Edit'; // Change button text back to "Edit"
                                }
                            });
                        </script>


                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" id="area" name="email" value="${requestScope.email != null ? requestScope.email : empAcc.email}">
                        </div>

                        <div class="form-row">
                            <div class="form-group half-width">
                                <label for="username">Username:</label>
                                <input type="text" id="username" name="username" value="${requestScope.username != null ? requestScope.phone : empAcc.taikhoan.username}">
                            </div>
                            <div class="form-group half-width">
                                <label for="password">Password:</label>
                                <input type="text" id="password" name="password" value="${empAcc.taikhoan.matkhau}">
                            </div>
                        </div>
                        <input type="hidden" id="password" name="mataikhoan" value="${empAcc.taikhoan.mataikhoan}">
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


    </body>
</html>
