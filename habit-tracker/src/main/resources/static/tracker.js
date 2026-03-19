const token = localStorage.getItem('token');
if (!token) window.location.href = '/login.html';

// ─── API HELPER ───────────────────────────────────────────────
async function apiFetch(url, options = {}) {
    const res = await fetch(url, {
        ...options,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token,
            ...options.headers
        }
    });
    if (res.status === 401) {
        localStorage.removeItem('token');
        window.location.href = '/login.html';
    }
    return res;
}

// ─── STATE ────────────────────────────────────────────────────
let HABITS = [];
let state = {};

const DAYS = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];

const LEVELS = [
    { xp: 0,      title: 'The Condemned', quote: 'Cast out. Nothing to your name.' },
    { xp: 300,    title: 'The Exile',     quote: 'You accepted the sentence. You walk anyway.' },
    { xp: 1000,   title: 'The Survivor',  quote: 'You did not break. You still exist.' },
    { xp: 3000,   title: 'The Relentless',quote: 'Pain is just another morning.' },
    { xp: 7500,   title: 'The Awakened', quote: 'No fog this time. You earn a scar.' },
    { xp: 17500,  title: 'The Ascendant', quote: 'You are no longer who you were.' },
    { xp: 35000,  title: 'The Harbinger', quote: 'Others feel the change before they understand it.' },
    { xp: 70000,  title: 'The Architect', quote: 'Work until yourself.' },
    { xp: 120000, title: 'The Provider',  quote: 'The house. The family. The freedom.' },
    { xp: 200000, title: 'The Legend',    quote: 'You came from nothing. You built everything.' }
];

// ─── AUTH ─────────────────────────────────────────────────────
function logout() {
    localStorage.removeItem('token');
    window.location.href = '/login.html';
}

// ─── DATES ───────────────────────────────────────────────────
function getWeekDates() {
    const today = new Date(), sun = new Date(today);
    sun.setDate(today.getDate() - today.getDay());
    return Array.from({ length: 7 }, (_, i) => {
        const d = new Date(sun);
        d.setDate(sun.getDate() + i);
        return d;
    });
}

function formatDate(d) {
    return d.toLocaleDateString('en-GB', { day: 'numeric', month: 'short' });
}

// ─── XP LOGIC ────────────────────────────────────────────────
function getDayXP(dk) {
    const ds = state[dk] || {};
    return HABITS.reduce((s, h) => {
        if (ds[h.key] === true)  return s + (h.inverted ? -h.penalty : h.xp);
        if (ds[h.key] === false) return s + (h.inverted ? h.xp : -h.penalty);
        return s;
    }, 0);
}

function getTotalXP() {
    const today = new Date();
    today.setHours(23, 59, 59);
    return getWeekDates().reduce((s, d) =>
        d <= today ? s + Math.max(0, getDayXP(d.toISOString().split('T')[0])) : s, 0);
}

function getLevel(xp) {
    let l = 0;
    LEVELS.forEach((v, i) => { if (xp >= v.xp) l = i; });
    return l;
}

// ─── UI UPDATES ──────────────────────────────────────────────
function updateXPBar() {
    const xp = getTotalXP(), lvl = getLevel(xp), next = LEVELS[lvl + 1] || LEVELS[lvl];
    document.getElementById('total-xp').textContent = xp;
    document.getElementById('level-val').textContent = lvl + 1;
    document.getElementById('title-val').textContent = LEVELS[lvl].title;
    document.getElementById('title-quote').textContent = LEVELS[lvl].quote;
    document.getElementById('next-xp').textContent = next.xp;
    document.getElementById('need-xp').textContent = Math.max(0, next.xp - xp);
}

function updateBodyMission() {
    const ws = getWeekDates()
        .map(d => parseFloat(state[d.toISOString().split('T')[0] + '_weight'] || 0))
        .filter(w => w > 0);
    const cur = ws.length ? ws[ws.length - 1] : 106.5;
    const avg = ws.length ? (ws.reduce((a, b) => a + b, 0) / ws.length).toFixed(1) : 106.5;
    const lost = (106.5 - cur).toFixed(1);
    const pct = Math.max(0, ((106.5 - cur) / (106.5 - 87.5) * 100)).toFixed(1);
    document.getElementById('current-weight').textContent = cur.toFixed(1);
    document.getElementById('lost-kg').textContent = lost;
    document.getElementById('avg-weight').textContent = avg;
    document.getElementById('progress-pct').textContent = pct + '% to goal';
    document.getElementById('progress-fill').style.width = Math.min(100, pct) + '%';
}

// ─── TABLE ────────────────────────────────────────────────────
function buildTableHeader() {
    const tr = document.getElementById('table-header');
    tr.innerHTML = `
        <th class="col-date">📅 Date</th>
        <th>Day</th>
        ${HABITS.map(h => `<th><span class="habit-icon">${h.icon}</span>${h.name.split(' ')[0]}</th>`).join('')}
        <th class="col-xp">⚡ XP</th>
        <th>📝 Notes</th>
        <th>Wt</th>
        <th>7d</th>
    `;
}

function buildTable() {
    const dates = getWeekDates();
    const today = new Date();
    today.setHours(23, 59, 59);
    const tbody = document.getElementById('table-body');
    tbody.innerHTML = '';

    dates.forEach(d => {
        const di = d.getDay(), isSun = di === 0, isSat = di === 6, isPast = d <= today;
        const dk = d.toISOString().split('T')[0], ds = state[dk] || {};
        const tr = document.createElement('tr');
        tr.className = isSun ? 'row-sun' : isSat ? 'row-sat' : 'row-weekday';

        let html = `<td class="cell-date">${formatDate(d)}</td>
                    <td><span class="cell-day ${isSun ? 'sunday' : isSat ? 'saturday' : ''}">${DAYS[di]}</span></td>`;

        HABITS.forEach(h => {
            if (isSun && h.rest) { html += `<td><span class="rest-badge">REST</span></td>`; return; }
            html += `<td><input type="checkbox" class="habit-cb ${h.inverted ? 'inverted' : ''}"
                        data-date="${dk}" data-habit="${h.key}"
                        ${ds[h.key] ? 'checked' : ''} ${!isPast ? 'disabled' : ''}></td>`;
        });

        html += `<td>${isPast ? `<span class="cell-xp">${getDayXP(dk)}</span>` : ''}</td>`;
        html += `<td><input class="notes-input" type="text" value="${state[dk + '_notes'] || ''}" data-date="${dk}"></td>`;
        html += `<td><input class="weight-input" type="number" step="0.1" value="${state[dk + '_weight'] || ''}" data-date="${dk}" placeholder="—"></td>`;
        html += `<td style="font-family:'Share Tech Mono',monospace;font-size:11px;color:var(--text3)">—</td>`;

        tr.innerHTML = html;
        tbody.appendChild(tr);
    });

    // Total row
    const total = document.createElement('tr');
    total.className = 'row-total';
    const wkXP = dates.reduce((s, d) => s + Math.max(0, getDayXP(d.toISOString().split('T')[0])), 0);
    let th = `<td colspan="2" style="text-align:left;padding-left:10px;font-size:10px;letter-spacing:2px;color:var(--text3)">WK TOTAL</td>`;
    HABITS.forEach(h => {
        const c = dates.filter(d => (state[d.toISOString().split('T')[0]] || {})[h.key]).length;
        th += `<td><span class="total-value">${c}</span><span class="total-label">✓</span></td>`;
    });
    th += `<td><span class="total-value">${wkXP}</span><span class="total-label">XP</span></td>
           <td colspan="3" style="color:var(--text3);font-size:10px">wk avg</td>`;
    total.innerHTML = th;
    tbody.appendChild(total);

    attachListeners();
}

// ─── LISTENERS ───────────────────────────────────────────────
function attachListeners() {
    document.querySelectorAll('.habit-cb').forEach(cb => {
        cb.addEventListener('change', async e => {
            const { date, habit } = e.target.dataset;
            if (!state[date]) state[date] = {};
            state[date][habit] = e.target.checked;
            await apiFetch(`/api/logs/${date}`, {
                method: 'POST',
                body: JSON.stringify({ habitCompletions: { [habit]: e.target.checked } })
            });
            buildTable();
            updateXPBar();
        });
    });

    document.querySelectorAll('.weight-input').forEach(inp => {
        inp.addEventListener('change', async e => {
            const date = e.target.dataset.date;
            const weight = parseFloat(e.target.value) || 0;
            state[date + '_weight'] = weight;
            await apiFetch(`/api/logs/${date}/weight`, {
                method: 'PATCH',
                body: JSON.stringify({ weightKg: weight })
            });
            updateBodyMission();
            buildTable();
        });
    });

    document.querySelectorAll('.notes-input').forEach(inp => {
        inp.addEventListener('change', e => {
            state[e.target.dataset.date + '_notes'] = e.target.value;
        });
    });
}

// ─── API LOADERS ─────────────────────────────────────────────
async function loadHabits() {
    const res = await apiFetch('/api/habits');
    if (res.ok) {
        const data = await res.json();
        HABITS = data.map(h => ({
            key: h.id, icon: '📋', name: h.name,
            xp: h.xpReward, penalty: h.xpPenalty,
            rest: h.isSundayRest, core: h.isCore,
            inverted: h.isInverted, notes: h.description || ''
        }));
    }
}

async function loadEntriesForDate(dateKey) {
    const res = await apiFetch(`/api/logs/${dateKey}/entries`);
    if (res.ok) {
        const entries = await res.json();
        if (!state[dateKey]) state[dateKey] = {};
        entries.forEach(entry => { state[dateKey][entry.habit.id] = entry.completed; });
    }
}

async function loadWeightForDate(dateKey) {
    const res = await apiFetch(`/api/logs/${dateKey}`);
    if (res.ok) {
        const log = await res.json();
        if (log.weightKg) state[dateKey + '_weight'] = log.weightKg;
    }
}

// ─── INIT ─────────────────────────────────────────────────────
loadHabits().then(async () => {
    const dates = getWeekDates();
    await Promise.all(dates.map(d => {
        const dk = d.toISOString().split('T')[0];
        return Promise.all([loadEntriesForDate(dk), loadWeightForDate(dk)]);
    }));
    buildTableHeader();
    buildTable();
    updateXPBar();
    updateBodyMission();
});
