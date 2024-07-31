<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Event</title>
    <link rel="stylesheet" href="../static/add-event.css">
</head>
<body>
<nav>
    <div class="nav-container">
        <div class="nav-left">
            <a href="${pageContext.request.contextPath}/organizer" class="btn">Back</a>
            <a href="${pageContext.request.contextPath}/events/show-events" class="btn">Show Events</a>
        </div>
        <div class="nav-right">
            <button class="balance-btn" onclick="showBalancePopup()">Balance: $<span id="balanceDisplay">${session.balance}</span>
            </button>
            <a href="${pageContext.request.contextPath}/auth/sign-in" class="logout-btn">Log Out</a>
        </div>
    </div>
</nav>

<!-- Balance Popup -->
<form action="">
    <div id="balancePopup" class="popup">
        <div class="popup-content">
            <span class="close" onclick="closeBalancePopup()">&times;</span>
            <h2>Update Balance</h2>
            <form id="balanceForm">
                <label for="balance">Enter additional balance:</label>
                <input type="number" id="balance" name="balance" placeholder="$0.00" min="0" step="0.01" required>
                <button type="submit" onclick="updateBalance()">Update</button>
            </form>
        </div>
    </div>
</form>



<div class="container">
    <c:if test="${not empty errorMessage}">
        <div id="alertMessage" class="alert alert-danger alert-bottom-right" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <h1>Create Event</h1>
    <form id="createEventForm" action="${pageContext.request.contextPath}/events/add-event" method="post">
        <!-- Event Name Section -->

        <div class="form-section">
            <label for="eventType">Event Type ($500 to create)</label>
            <select id="eventType" name="eventType" required>
                <option value="" disabled selected>Select event type</option>
                <option value="CONCERT">CONCERT</option>
                <option value="MOVIE">MOVIE</option>
                <option value="SHOW">SHOW</option>
                <option value="GAME">GAME</option>
            </select>
        </div>

        <section class="header">
            <h1>Select Your Venue</h1>
            <p>Choose a venue from the list below for your event.</p>
        </section>

        <section class="venue-selection">
            <label for="venue-select">Select a venue:</label>
            <select id="venue-select" required>
                <option value="" disabled selected>-- Select a venue --</option>

                <optgroup label="Tashkent">
                    <option value="Humo Arena" data-price=250 data-capacity=12500 data-ticket=10>
                        Humo Arena - $250 - 12,500 people
                    </option>
                    <option value="UzExpoCenter" data-price=180 data-capacity=3000 data-ticket=5>
                        UzExpoCenter - $180 - 3,000 people
                    </option>
                    <option value="Navoi Theater" data-price=170 data-capacity=1400 data-ticket=5>
                        Navoi Theater - $170 - 1,400 people
                    </option>
                    <option value="Istiqlol Palace" data-price=180 data-capacity=3500 data-ticket=10>
                        Istiqlol Palace - $180 - 3,500 people
                    </option>
                    <option value="Tashkent International Hotel Conference Hall" data-price=250 data-capacity=500
                            data-ticket=20>
                        Tashkent International Hotel Conference Hall - $250 - 500 people
                    </option>
                    <option value="Palace of the Peoples' Friendship" data-price=300 data-capacity=3500 data-ticket=10>
                        Palace of the Peoples' Friendship - $300 - 3,500 people
                    </option>
                    <option value="Tashkent City Congress Hall" data-price=200 data-capacity=2000 data-ticket=20>
                        Tashkent City Congress Hall - $200 - 2,000 people
                    </option>
                    <option value="Tashkent State Conservatory" data-price=150 data-capacity=1000 data-ticket=5>
                        Tashkent State Conservatory - $150 - 1,000 people
                    </option>
                    <option value="Alisher Navoi National Park" data-price=130
                            data-capacity="Open space, different capacity" data-ticket=0>
                        Alisher Navoi National Park - $130 - Open space, different capacity
                    </option>
                </optgroup>

                <optgroup label="Samarkand">
                    <option value="Registan Square" data-price=200 data-capacity="Open area, capacity varies"
                            data-ticket=0>
                        Registan Square - $200 - Open area, capacity varies
                    </option>
                    <option value="Samarkand International Hotel Conference Hall" data-price=150 data-capacity=600
                            data-ticket=20>
                        Samarkand International Hotel Conference Hall - $150 - 600 people
                    </option>
                    <option value="Ulugbek Observatory Museum" data-price=110 data-capacity=200 data-ticket=2>
                        Ulugbek Observatory Museum - $110 - 200 people
                    </option>
                    <option value="Amir Temur Mausoleum" data-price=110 data-capacity=100 data-ticket=2>
                        Amir Temur Mausoleum - $110 - 100 people
                    </option>
                    <option value="Samarkand State University Conference Hall" data-price=150 data-capacity=500
                            data-ticket=10>
                        Samarkand State University Conference Hall - $150 - 500 people
                    </option>
                </optgroup>

                <optgroup label="Bukhara">
                    <option value="Ark Fortress" data-price=120 data-capacity="Open area, capacity varies"
                            data-ticket=0>
                        Ark Fortress - $120 - Open area, capacity varies
                    </option>
                    <option value="Bukhara State Medical Institute Conference Hall" data-price=150 data-capacity=400
                            data-ticket=10>
                        Bukhara State Medical Institute Conference Hall - $150 - 400 people
                    </option>
                    <option value="Lyabi-Hauz Complex" data-price=180 data-capacity="Open area, capacity varies"
                            data-ticket=0>
                        Lyabi-Hauz Complex - $180 - Open area, capacity varies
                    </option>
                    <option value="Bolo Haouz Mosque" data-price=120 data-capacity=1000 data-ticket=2>
                        Bolo Haouz Mosque - $120 - 1,000 people
                    </option>
                    <option value="Bukhara State University Conference Hall" data-price=110 data-capacity=500
                            data-ticket=10>
                        Bukhara State University Conference Hall - $110 - 500 people
                    </option>
                </optgroup>

                <optgroup label="Khiva">
                    <option value="Khiva State Museum-Reserve Conference Hall" data-price=150 data-capacity=300
                            data-ticket=5>
                        Khiva State Museum-Reserve Conference Hall - $150 - 300 people
                    </option>
                    <option value="Itchan Kala" data-price=140 data-capacity="Open area, capacity varies" data-ticket=0>
                        Itchan Kala - $140 - Open area, capacity varies
                    </option>
                    <option value="Tash Hauli Palace" data-price=160 data-capacity=200 data-ticket=2>
                        Tash Hauli Palace - $160 - 200 people
                    </option>
                    <option value="Kalta Minor Minaret" data-price=130 data-capacity="Open area, capacity varies"
                            data-ticket=2>
                        Kalta Minor Minaret - $130 - Open area, capacity varies
                    </option>
                </optgroup>

                <optgroup label="Kokand">
                    <option value="Kokand Palace of Khudoyar Khan" data-price=110 data-capacity=500 data-ticket=2>
                        Kokand Palace of Khudoyar Khan - $110 - 500 people
                    </option>
                    <option value="Kokand Museum of Local Lore" data-price=130 data-capacity=200 data-ticket=2>
                        Kokand Museum of Local Lore - $130 - 200 people
                    </option>
                    <option value="Kokand State Pedagogical Institute Conference Hall" data-price=150 data-capacity=400
                            data-ticket=10>
                        Kokand State Pedagogical Institute Conference Hall - $150 - 400 people
                    </option>
                </optgroup>

                <optgroup label="Andijan">
                    <option value="Andijan State University Conference Hall" data-price=150 data-capacity=500
                            data-ticket=10>
                        Andijan State University Conference Hall - $150 - 500 people
                    </option>
                    <option value="Bobur Park" data-price=170 data-capacity="Open space, capacity varies" data-ticket=0>
                        Bobur Park - $170 - Open space, capacity varies
                    </option>
                    <option value="Andijan Regional Music and Drama Theater" data-price=180 data-capacity=600
                            data-ticket=5>
                        Andijan Regional Music and Drama Theater - $180 - 600 people
                    </option>
                </optgroup>

                <optgroup label="Namangan">
                    <option value="Namangan State University Conference Hall" data-price=150 data-capacity=400
                            data-ticket=10>
                        Namangan State University Conference Hall - $150 - 400 people
                    </option>
                    <option value="Navruz Palace" data-price=170 data-capacity=1000 data-ticket=10>
                        Navruz Palace - $170 - 1,000 people
                    </option>
                    <option value="Namangan Regional Drama Theater" data-price=180 data-capacity=600 data-ticket=5>
                        Namangan Regional Drama Theater - $180 - 600 people
                    </option>
                </optgroup>

                <optgroup label="Nukus">
                    <option value="Nukus State University Conference Hall" data-price=150 data-capacity=500
                            data-ticket=10>
                        Nukus State University Conference Hall - $150 - 500 people
                    </option>
                    <option value="Karakalpak State Academic Drama Theater" data-price=180 data-capacity=600
                            data-ticket=5>
                        Karakalpak State Academic Drama Theater - $180 - 600 people
                    </option>
                    <option value="Savitsky Museum" data-price=170 data-capacity=300 data-ticket=2>
                        Savitsky Museum - $170 - 300 people
                    </option>
                </optgroup>

                <optgroup label="Urgench">
                    <option value="Urgench State University Conference Hall" data-price=120 data-capacity=500
                            data-ticket=10>
                        Urgench State University Conference Hall - $120 - 500 people
                    </option>
                    <option value="Urgench Cultural Center" data-price=170 data-capacity=600 data-ticket=0>
                        Urgench Cultural Center - $170 - 600 people
                    </option>
                    <option value="Khorezm Regional Musical Drama Theater" data-price=160 data-capacity=700
                            data-ticket=5>
                        Khorezm Regional Musical Drama Theater - $160 - 700 people
                    </option>
                </optgroup>

                <optgroup label="Termez">
                    <option value="Termez State University Conference Hall" data-price=140 data-capacity=400
                            data-ticket=10>
                        Termez State University Conference Hall - $140 - 400 people
                    </option>
                    <option value="Termez Archaeological Museum" data-price=180 data-capacity=200 data-ticket=2>
                        Termez Archaeological Museum - $180 - 200 people
                    </option>
                    <option value="Sultan Saodat Complex" data-price=140 data-capacity="Open space, capacity varies"
                            data-ticket=0>
                        Sultan Saodat Complex - $140 - Open space, capacity varies
                    </option>
                </optgroup>
            </select>
        </section>

        <section class="confirmation">
            <h2>Confirmation</h2>
            <div class="venue-info">
                <label for="venue-name">Venue name:</label>
                <input type="text" id="venue-name" class="venue-details" placeholder="Venue Name" name="locationName"
                       readonly>
                <label for="venue-name">Price:</label>
                <input type="number" id="venue-price" class="venue-details" placeholder="Price" name="locationPrice"
                       readonly>
                <label for="venue-name">Capacity:</label>
                <input type="number" id="venue-capacity" class="venue-details" placeholder="Capacity" name="capacity"
                       readonly>
                <label for="venue-name">Ticket Price:</label>
                <input type="number" id="venue-ticket" class="venue-details" placeholder="Ticket Price"
                       name="ticketPrice" readonly>
            </div>
        </section>

        <div class="form-section">
            <label for="startTime">Start date-time</label>
            <input type="datetime-local" id="startTime" name="startTime" required>
        </div>
        <div class="form-section">
            <label for="endTime">End date-time</label>
            <input type="datetime-local" id="endTime" name="endTime" required>
        </div>

        <div class="button-container">
            <button class="btn" type="submit">Create Event</button>
        </div>

    </form>
</div>

<!-- Confirmation Popup -->
<div id="confirmationPopup" class="popup" style="display: none;">
    <div class="popup-content">
        <span class="close" onclick="document.getElementById('confirmationPopup').style.display='none'">&times;</span>
        <h2>Confirm Event Creation</h2>
        <div id="confirmationMessage"></div>
        <div class="popup-actions">
            <button id="confirmCreateEvent" class="btn">Confirm</button>
            <button id="cancelCreateEvent" class="btn">Cancel</button>
        </div>
    </div>
</div>

<script src="../static/add-event.js"></script>


</body>
</html>