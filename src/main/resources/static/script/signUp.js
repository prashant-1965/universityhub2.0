function showError(fieldId, message) {
    const field = document.getElementById(fieldId);
    const errorElement = document.getElementById(fieldId + 'Error');

    field.classList.add('error');
    errorElement.textContent = message;
    errorElement.style.display = 'block';
}

function clearErrors() {
    const errorElements = document.querySelectorAll('.error-message');
    errorElements.forEach(el => {
        el.style.display = 'none';
        el.textContent = '';
    });

    const inputElements = document.querySelectorAll('input, select');
    inputElements.forEach(el => el.classList.remove('error'));
}

function showAlert(message, type) {
    const alertElement = document.getElementById('alert');
    alertElement.textContent = message;
    alertElement.className = `alert alert-${type}`;
    alertElement.style.display = 'block';

    setTimeout(() => {
        alertElement.style.display = 'none';
    }, 5000);
}

function setLoading(isLoading) {
    const btn = document.getElementById('submitBtn');
    const loading = document.getElementById('loading');
    const form = document.getElementById('signupForm');

    if (isLoading) {
        btn.disabled = true;
        btn.textContent = 'Please wait...';
        loading.style.display = 'block';
        form.style.opacity = '0.6';
    } else {
        btn.disabled = false;
        btn.textContent = 'Create Account';
        loading.style.display = 'none';
        form.style.opacity = '1';
    }
}

function validateUsername(username) {
    if (!username.trim()) {
        return 'Username is required';
    }
    if (username.length < 3) {
        return 'Username must be at least 3 characters';
    }
    return null;
}

function validatePassword(password) {
    if (!password) {
        return 'Password is required';
    }
    if (password.length < 6) {
        return 'Password must be at least 6 characters';
    }
    return null;
}

function validateRole(role) {
    if (!role) {
        return 'Please select a role';
    }
    return null;
}

// Real-time password confirmation validation
document.getElementById('password').addEventListener('input', function() {
    const confirmPassword = document.getElementById('confirmPassword');
    if (confirmPassword.value && this.value !== confirmPassword.value) {
        showError('confirmPassword', 'Passwords do not match');
    } else {
        document.getElementById('confirmPasswordError').style.display = 'none';
        confirmPassword.classList.remove('error');
    }
});

document.getElementById('confirmPassword').addEventListener('input', function() {
    const password = document.getElementById('password').value;
    if (this.value && password !== this.value) {
        showError('confirmPassword', 'Passwords do not match');
    } else {
        document.getElementById('confirmPasswordError').style.display = 'none';
        this.classList.remove('error');
    }
});

// Form submission
document.getElementById('signupForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    clearErrors();
    document.getElementById('alert').style.display = 'none';

    const formData = new FormData(this);
    const username = formData.get('username');
    const password = formData.get('password');
    const confirmPassword = formData.get('confirmPassword');
    const role = formData.get('role');

    let hasErrors = false;

    const usernameError = validateUsername(username);
    if (usernameError) {
        showError('username', usernameError);
        hasErrors = true;
    }

    const passwordError = validatePassword(password);
    if (passwordError) {
        showError('password', passwordError);
        hasErrors = true;
    }

    if (password !== confirmPassword) {
        showError('confirmPassword', 'Passwords do not match');
        hasErrors = true;
    }

    const roleError = validateRole(role);
    if (roleError) {
        showError('role', roleError);
        hasErrors = true;
    }

    if (hasErrors) return;

    setLoading(true);

    try {
        const credentials = {
            username: username,
            password: password,
            role: role
        };

        const response = await fetch('/addCredentials', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(credentials)
        });

        const result = await response.text();

        if (response.ok) {
            if (result === 'Account Created Successfully') {
                showAlert('Account created successfully! Redirecting to login...', 'success');
                setTimeout(() => {
                    window.location.href = '/login';
                }, 2000);
            } else {
                showAlert(result, 'error');
            }
        } else {
            showAlert('Failed to create account. Please try again.', 'error');
        }
    } catch (error) {
        console.error('Signup error:', error);
        showAlert('Network error. Please check your connection and try again.', 'error');
    } finally {
        setLoading(false);
    }
});
