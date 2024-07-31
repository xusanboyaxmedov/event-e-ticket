<%--
  Created by IntelliJ IDEA.
  User: xusan
  Date: 11/07/2024
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href="../static/sign-in-up.css">
</head>
<body>
<div class="container">
    <h1>Sign In</h1>
    <form action="${pageContext.request.contextPath}/auth/sign-in" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>

        <button type="submit" class="btn">Sign In</button>
    </form>
    <p>Don't have an account? <a href="${pageContext.request.contextPath}/auth/sign-up">Sign Up</a></p>
</div>
</body>
</html>
