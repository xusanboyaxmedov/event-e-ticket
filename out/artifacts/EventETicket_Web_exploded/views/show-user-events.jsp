<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events</title>
    <link rel="stylesheet" href="../static/show-user-events.css">
</head>
<body>
<nav class="navbar">
    <div class="nav-container">
        <div class="nav-left">
            <a href="${pageContext.request.contextPath}/user" class="nav-btn">Back</a>
            <a href="${pageContext.request.contextPath}/user/history" class="nav-btn">My Tickets</a>
        </div>
        <div class="nav-right">
            <button class="balance-btn">Balance: $<span id="balanceDisplay">${balance}</span></button>
        </div>
    </div>
</nav>

<div class="container">
    <div id="notification-container"></div>
    <c:if test="${not empty errorMessage}">
        <div id="alertMessage" class="alert alert-danger alert-bottom-right" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <h1>Events</h1>
    <div class="events-grid">
        <c:choose>
            <c:when test="${not empty events}">
                <c:forEach var="event" items="${events}">
                    <div class="event-card">
                        <img src="../pictures/${event.picture}" alt="Event Thumbnail">
                        <div class="event-details">
                            <h3>${event.type}</h3>
                            <p><strong>Venue:</strong> ${event.locationName}</p>
                            <p><strong>Date:</strong> ${event.startTime}</p>
                            <p><strong>Available tickets:</strong> ${event.availableSeats}</p>
                            <p><strong>Price:</strong> $${event.ticketPrice}</p>
                        </div>
                        <div class="event-actions">
                            <button type="button" class="btn buy-btn"
                                    onclick="showBuyPopup('${event.id}', '${fn:escapeXml(event.type)}')">Buy</button>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h3>No events available at the moment</h3>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Buy Confirmation Popup -->
    <div id="buyPopup" class="popup">
        <div class="popup-content">
            <p>Are you sure you want to buy tickets for this event: <span id="eventType"></span>?</p>
            <div class="popup-actions">
                <form id="confirmBuyForm" action="${pageContext.request.contextPath}/events/buy-event" method="post">
                    <input type="hidden" id="buyEventId" name="eventId">
                    <button type="submit" class="btn confirm-btn">Yes</button>
                </form>
                <button id="cancelBuy" class="btn cancel-btn">No</button>
            </div>
        </div>
    </div>
</div>

<script>
    function showBuyPopup(eventId, eventType) {
        document.getElementById('eventType').innerText = eventType;
        document.getElementById('buyEventId').value = eventId;
        document.getElementById('buyPopup').style.display = 'flex';

        document.getElementById('cancelBuy').onclick = function() {
            document.getElementById('buyPopup').style.display = 'none';
        };
    }
</script>
</body>
</html>
