<%--
  Created by IntelliJ IDEA.
  User: xusan
  Date: 12/07/2024
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="../static/users.css">
</head>
<body>
<nav>
    <div class="nav-container">
        <span class="nav-title">User Dashboard</span>
        <div class="nav-buttons">
            <button class="balance-btn" onclick="showBalancePopup()">Balance: $<span
                    id="balanceDisplay">${session.balance}</span>
            </button>
            <a href="${pageContext.request.contextPath}/auth/sign-in" class="logout-btn">Log Out</a>
        </div>
    </div>
</nav>

<form action="${pageContext.request.contextPath}/user">
    <div class="container">
        <h1>Welcome, ${session.username}!</h1>
        <div class="button-container">
            <a href="${pageContext.request.contextPath}/events/show-user-events" class="btn">Events</a>
            <a href="${pageContext.request.contextPath}/user/history" class="btn">Attendance History</a>
        </div>
    </div>

    <!-- Balance Popup -->
    <div id="balancePopup" class="popup">
        <div class="popup-content">
            <span class="close" onclick="closeBalancePopup()">&times;</span>
            <h2>Update Balance</h2>
            <form id="balanceForm">
                <label for="balance">Enter additional balance:</label>
                <input type="number" id="balance" name="balance" placeholder="$0.00" min="0" step="0.01" required>
                <button type="button" onclick="updateBalance()">Update</button>
            </form>
        </div>
    </div>
</form>

<script src="../static/scripts-user.js"></script>
</body>
</html>
