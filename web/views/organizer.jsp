<%--
  Created by IntelliJ IDEA.
  User: xusan
  Date: 12/07/2024
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/organizer.css">
</head>
<body>
<nav>
    <div class="nav-container">
        <span class="nav-title">Organizer Dashboard</span>
        <div class="nav-buttons">
            <button class="balance-btn" onclick="showBalancePopup()">Balance: $<span id="balanceDisplay">${session.balance}</span></button>
            <a href="sign-in" class="logout-btn">Log Out</a>
        </div>
    </div>
</nav>
<div class="container">
    <h1>Welcome, ${session.username}!</h1>
    <div class="button-container">
        <a href="${pageContext.request.contextPath}/events/add-event" class="btn">Create Event</a>
        <a href="${pageContext.request.contextPath}/events/show-events" class="btn">Show Events</a>
    </div>
</div>

<!-- Balance Popup -->
<div id="balancePopup" class="popup">
    <div class="popup-content">
        <span class="close" onclick="closeBalancePopup()">&times;</span>
        <h2>Update Balance</h2>
        <form id="balanceForm">
            <label for="balance">Enter additional balance:</label>
            <input type="text" id="balance" name="balance" placeholder="$0.00">
            <button type="button" onclick="updateBalance()">Update</button>
        </form>
    </div>
</div>

<script src="../static/organizer.js"></script>
</body>
</html>
