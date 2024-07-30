<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: xusan
  Date: 28/07/2024
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events</title>
    <link rel="stylesheet" href="../static/show-events.css">
</head>
<style>
    .alert-bottom-right {
        position: absolute;
        bottom: 20px;
        right: 20px;
        z-index: 1050; /* Ensure it's above other content */
        transition: opacity 0.5s ease-out, transform 0.5s ease-out;
    }
    .alert-bottom-right.fade-out {
        opacity: 0;
        transform: translateY(20px);
    }
</style>
<body>
<nav>
    <div class="nav-container">
        <div class="nav-left">
            <a href="${pageContext.request.contextPath}/events/add-event" class="btn">Back</a>
            <a href="${pageContext.request.contextPath}/organizer" class="btn">Main Menu</a>
        </div>
        <div class="nav-right">
            <span class="balance">Balance: $<span id="balance">500</span></span>
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
                            <p><strong>Type:</strong> ${event.type}</p>
                            <p><strong>Venue:</strong> ${event.locationName}</p>
                            <p><strong>Date:</strong> ${event.startTime}</p>
                            <p><strong>Available tickets:</strong> ${event.availableSeats}</p>
                            <p><strong>Price:</strong> $${event.ticketPrice}</p>
                        </div>
                        <div class="event-actions">
                            <button type="button" class="btn delete-btn"
                                    onclick="showDeletePopup('${event.id}', '${fn:escapeXml(event.type)}')">BUY</button>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h3>You don't have any events yet</h3>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Delete Confirmation Popup -->
    <div id="deletePopup" class="popup">
        <div class="popup-content">
            <p>Are you sure you want to buy this event: <span id="eventType"></span>?</p>
            <div class="popup-actions">
                <form id="confirmDeleteForm" action="${pageContext.request.contextPath}/events/buy-event" method="post">
                    <input type="hidden" id="deleteEventId" name="eventId">
                    <button type="submit" class="btn confirm-btn">Yes</button>
                </form>
                <button id="cancelDelete" class="btn cancel-btn">No</button>
            </div>
        </div>
    </div>
</div>

<script>
    function showDeletePopup(eventId, eventType) {
        // Display the event type in the popup message
        document.getElementById('eventType').innerText = eventType;
        // Set the event ID in the hidden input of the confirmation form
        document.getElementById('deleteEventId').value = eventId;
        // Show the popup
        document.getElementById('deletePopup').style.display = 'flex';

        // Set event listener for the "No" button
        document.getElementById('cancelDelete').onclick = function() {
            document.getElementById('deletePopup').style.display = 'none';
        };
    }
</script>

</body>
</html>
