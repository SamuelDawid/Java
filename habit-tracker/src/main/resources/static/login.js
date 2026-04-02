// Redirect if already logged in
if (localStorage.getItem('token')) {
    window.location.href = '/tracker.html';
}

// ─── UI HELPERS ───────────────────────────────────────────────
function showError(msg) {
    const el = document.getElementById('error-msg');
    el.textContent = msg;
    el.style.display = 'block';
    document.getElementById('success-msg').style.display = 'none';
}

function showSuccess(msg) {
    const el = document.getElementById('success-msg');
    el.textContent = msg;
    el.style.display = 'block';
    document.getElementById('error-msg').style.display = 'none';
}

function clearMessages() {
    document.getElementById('error-msg').style.display = 'none';
    document.getElementById('success-msg').style.display = 'none';
}

// ─── FORM TOGGLE ──────────────────────────────────────────────
function showRegister() {
    clearMessages();
    document.getElementById('login-form').style.display = 'none';
    document.getElementById('register-form').style.display = 'block';
    document.getElementById('card-title').textContent = '⚔ New Exile Registration';
    document.getElementById('toggle-link').innerHTML = 'Already have an account? <a onclick="showLogin()">Login</a>';
}

function showLogin() {
    clearMessages();
    document.getElementById('login-form').style.display = 'block';
    document.getElementById('register-form').style.display = 'none';
    document.getElementById('card-title').textContent = '⚔ Access Terminal';
    document.getElementById('toggle-link').innerHTML = 'No account? <a onclick="showRegister()">Create one</a>';
}

// ─── AUTH ACTIONS ─────────────────────────────────────────────
async function handleLogin() {
    const email = document.getElementById('login-email').value.trim();
    const password = document.getElementById('login-password').value;
    if (!email || !password) { showError('All fields required.'); return; }

    try {
        const res = await fetch('/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });
        if (res.ok) {
            const data = await res.json();
            localStorage.setItem('token', data.token);
            showSuccess('Access granted. Redirecting...');
            setTimeout(() => window.location.href = '/tracker.html', 800);
        } else if (res.status === 404) {
            showError('Invalid email or password.');
        } else {
            showError('Something went wrong. Try again.');
        }
    } catch (e) {
        showError('Cannot connect to server.');
    }
}

async function handleRegister() {
    const userName = document.getElementById('reg-username').value.trim();
    const email = document.getElementById('reg-email').value.trim();
    const password = document.getElementById('reg-password').value;
    if (!userName || !email || !password) { showError('All fields required.'); return; }
    if (password.length < 6) { showError('Password must be at least 6 characters.'); return; }

    try {
        const res = await fetch('/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userName, email, password })
        });
        if (res.ok) {
            const data = await res.json();
            localStorage.setItem('token', data.token);
            showSuccess('Account created. Redirecting...');
            setTimeout(() => window.location.href = '/tracker.html', 800);
        } else if (res.status === 500) {
            showError('Email or username already exists.');
        } else {
            showError('Registration failed. Try again.');
        }
    } catch (e) {
        showError('Cannot connect to server.');
    }
}

// ─── KEYBOARD SUPPORT ─────────────────────────────────────────
document.addEventListener('keydown', e => {
    if (e.key === 'Enter') {
        if (document.getElementById('register-form').style.display !== 'none') {
            handleRegister();
        } else {
            handleLogin();
        }
    }
});
