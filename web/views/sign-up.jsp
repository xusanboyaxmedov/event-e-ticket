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
    <title>Log In</title>
    <link rel="stylesheet" href="../static/sign-in-up.css">
</head>
<body>
<div class="container">
    <h1>Sign Up</h1>
    <form action="${pageContext.request.contextPath}/auth/sign-up" method="post">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Enter your name" required>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" required>
        <label for="role">Role</label>
        <select id="role" name="role" required>
            <option value="" disabled selected>Select your role</option>
            <option value="USER">USER</option>
            <option value="ORGANIZER">ORGANIZER</option>
        </select>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Create a password" required>

        <button type="submit" class="btn blue-btn">Create an account</button>
    </form>
    <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/sign-in">Log In</a></p>
</div>
</body>
</html>
