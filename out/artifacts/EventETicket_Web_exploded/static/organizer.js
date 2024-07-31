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

        xhr.onreadystatechange = function() {
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