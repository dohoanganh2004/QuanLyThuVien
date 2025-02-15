<%-- 
    Document   : updateAccount
    Created on : Nov 2, 2024, 8:41:10 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="updateAccount" method="post">
            <input type="hidden" name="user" value="${sessionScope.account.username}"/>
            <input type="hidden" name="mathe" value="${sessionScope.account.the.sothe}"/>
            <label>Old Password:</label> 
            <input type="password" name="opass" required/><br/>

            <label>New Password:</label> 
            <input type="password" name="npass" required/><br/>

            <label>Confirm Password:</label> 
            <input type="password" name="cpass" required/><br/>

            <label>Avatar:</label> 
            <input type="text" name="avatar" required/><br/>

            <input type="submit" value="Submit" />
        </form>
            <h3 style="color:red">${requestScope.error}</h3>
    </body>
</html>
