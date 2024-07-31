<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xusan
  Date: 31/07/2024
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/user-attendance.css">
</head>
<body>
<!-- Navbar -->
<nav class="navbar">
    <div class="nav-container">
        <div class="nav-left">
            <a href="${pageContext.request.contextPath}/user" class="nav-btn">Back</a>
            <a href="${pageContext.request.contextPath}/events/show-user-events" class="nav-btn">Show Events</a>
        </div>
        <div class="nav-right">
            <button class="balance-btn">Balance: $<span id="balanceDisplay">${balance}</span></button>
        </div>
    </div>
</nav>

<!-- Content -->
<div class="container">
    <h1>Attendance History</h1>
    <div class="events-grid">
        <!-- Example of a ticket card -->
        <c:choose>
            <c:when test="${not empty tickets}">
                <c:forEach var="ticket" items="${tickets}">
                    <div class="event-card">
                        <div class="event-details">
                            <h3>${ticket.eventType}</h3>
                            <p><strong>Venue:</strong> ${ticket.locationName}</p>
                            <p><strong>Start Time:</strong> ${ticket.ticketDate}</p>
                        </div>
                        <div class="event-actions">
                            <img src="../pictures/qr_code.png" alt="Barcode" class="barcode-img">
                            <!-- Placeholder for barcode image -->
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h3>You don't have any tickets yet</h3>
            </c:otherwise>
        </c:choose>


        <!-- Additional tickets here -->
    </div>
</div>
</body>
</html>
