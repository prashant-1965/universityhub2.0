// Basic client-side validation
document.querySelector('form').addEventListener('submit', function(e) {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    if (!username) {
        alert('Please enter your username');
        e.preventDefault();
        return;
    }

    if (!password) {
        alert('Please enter your password');
        e.preventDefault();
        return;
    }

    if (!role) {
        alert('Please select your role');
        e.preventDefault();
        return;
    }
});

// Auto-hide alerts after 5 seconds
const alerts = document.querySelectorAll('.alert');
alerts.forEach(alert => {
    setTimeout(() => {
        alert.style.opacity = '0';
        setTimeout(() => {
            alert.style.display = 'none';
        }, 300);
    }, 5000);
});
