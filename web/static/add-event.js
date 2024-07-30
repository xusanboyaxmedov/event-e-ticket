function showBalancePopup() {
    document.getElementById('balancePopup').style.display = 'block';
}

function closeBalancePopup() {
    document.getElementById('balancePopup').style.display = 'none';
}

function updateBalance() {
    var balanceInput = document.getElementById('balance').value;
    var currentBalance = parseFloat(document.getElementById('balanceDisplay').innerText);
    var newBalance = parseFloat(balanceInput);

    if (!isNaN(newBalance) && newBalance > 0) {
        var updatedBalance = currentBalance + newBalance;
        document.getElementById('balanceDisplay').innerText = updatedBalance.toFixed(2);

        // Ideally, send the new balance to the server
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "updateBalance", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("balance=" + updatedBalance);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                // Handle response from the server if needed
                alert('Balance updated successfully');
            }
        };

        closeBalancePopup();
    } else {
        alert('Please enter a valid balance');
    }
}

let venueSelect = document.getElementById('venue-select');
let venueName = document.getElementById('venue-name');
let venuePrice = document.getElementById('venue-price');
let venueCapacity = document.getElementById('venue-capacity');
let venueTicket = document.getElementById('venue-ticket');

venueSelect.addEventListener('change', () => {
    let selectedOption = venueSelect.options[venueSelect.selectedIndex];
    let name = selectedOption.value;
    let price = parseFloat(selectedOption.getAttribute('data-price'));
    let capacity = parseInt(selectedOption.getAttribute('data-capacity'));
    let ticket = parseFloat(selectedOption.getAttribute('data-ticket'));
    console.log(selectedOption);

    venueName.value = name;
    venuePrice.value = price;
    venueCapacity.value = capacity;
    venueTicket.value = ticket;
});


window.addEventListener('load', function () {
    const alert = document.getElementById('alertMessage');
    if (alert) {
        setTimeout(() => {
            alert.classList.add('fade-out');
        }, 3000);
    }
});

function showDeletePopup(eventId, eventType) {
    document.getElementById('eventType').innerText = eventType;
    document.getElementById('deletePopup').style.display = 'flex';

    // Set event listener for the "Yes" button
    document.getElementById('confirmDelete').onclick = function () {
        document.getElementById('deleteEventForm' + eventId).submit();
    };

    // Set event listener for the "No" button
    document.getElementById('cancelDelete').onclick = function () {
        document.getElementById('deletePopup').style.display = 'none';
    };
}