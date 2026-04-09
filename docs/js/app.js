/* ── State ── */
let allModules = [];
let currentLesson = null;
let currentModuleId = null;
let currentExIndex = 0;
let answered = false;

const PROGRESS_KEY = 'javaSpillProgress_v2';

function getProgress() {
    try { return JSON.parse(localStorage.getItem(PROGRESS_KEY) || '{}'); }
    catch { return {}; }
}
function saveProgress(p) {
    localStorage.setItem(PROGRESS_KEY, JSON.stringify(p));
    flashSaveIndicator();
}

/* ── Save indicator flash ── */
function flashSaveIndicator() {
    const el = document.getElementById('save-indicator');
    if (!el) return;
    el.classList.remove('hidden', 'fading');
    clearTimeout(el._timer);
    el._timer = setTimeout(() => {
        el.classList.add('fading');
        setTimeout(() => el.classList.add('hidden'), 400);
    }, 1600);
}

/* ── Settings modal ── */
function openSettings() {
    document.getElementById('settings-modal').classList.remove('hidden');
}
function closeSettings() {
    document.getElementById('settings-modal').classList.add('hidden');
}

// Lukk modal ved trykk på mørk bakgrunn – støtter både mus og touch (Android)
window.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('settings-modal');
    if (!modal) return;
    function tryClose(e) { if (e.target === modal) closeSettings(); }
    modal.addEventListener('click',      tryClose);
    modal.addEventListener('touchstart', tryClose, { passive: true });
});

/* ── Reset all progress ── */
function resetAll() {
    // Bruker innebygd confirm()-dialog – fungerer på alle plattformer
    if (!confirm('Er du sikker? Dette sletter ALL fremgang og XP.\nHandlingen kan ikke angres.')) return;
    localStorage.removeItem(PROGRESS_KEY);
    closeSettings();
    updateXP();
    renderHome();
    showToast('🗑️ All fremgang er tilbakestilt');
}

/* ── Export progress ── */
function exportProgress() {
    const data = localStorage.getItem(PROGRESS_KEY) || '{}';
    const blob = new Blob([data], { type: 'application/json' });
    const url  = URL.createObjectURL(blob);
    const a    = document.createElement('a');
    a.href = url;
    a.download = 'javaspill-lagring.json';
    a.click();
    URL.revokeObjectURL(url);
    showToast('📤 Lagring eksportert!');
}

/* ── Import progress ── */
function importProgress(event) {
    const file = event.target.files[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = (e) => {
        try {
            const parsed = JSON.parse(e.target.result);
            if (typeof parsed !== 'object' || parsed === null) throw new Error('Ugyldig format');
            localStorage.setItem(PROGRESS_KEY, JSON.stringify(parsed));
            closeSettings();
            updateXP();
            renderHome();
            showToast('📥 Lagring importert!');
        } catch {
            showToast('❌ Ugyldig fil – kunne ikke importere', 4000);
        }
    };
    reader.readAsText(file);
    event.target.value = '';
}

function markExerciseDone(exId) {
    const p = getProgress();
    p.exercises = p.exercises || {};
    if (!p.exercises[exId]) {
        p.exercises[exId] = true;
        p.xp = (p.xp || 0) + 20;
        saveProgress(p);
        updateXP();
    }
}

function isExerciseDone(exId) {
    const p = getProgress();
    return !!(p.exercises && p.exercises[exId]);
}

function isLessonDone(lesson) {
    if (!lesson || !lesson.exercises || lesson.exercises.length === 0) return false;
    return lesson.exercises.every(e => isExerciseDone(e.id));
}

function getModuleProgress(moduleId) {
    const m = allModules.find(x => x.id === moduleId);
    if (!m || !m.lessons) return { done: 0, total: 0 };
    let done = 0;
    let total = 0;
    m.lessons.forEach(ls => {
        // ls here is the summary (lessonId, title, exerciseCount)
        // We check from the cached full lessons
        total++;
        const cached = lessonCache[ls.id];
        if (cached && isLessonDone(cached)) done++;
    });
    return { done, total };
}

const lessonCache = {};

function updateXP() {
    const p = getProgress();
    document.getElementById('xp-count').textContent = p.xp || 0;
}

/* ── Navigation ── */
function navigate(view, id) {
    window.scrollTo(0, 0);
    if (view === 'home')   renderHome();
    if (view === 'module') renderModuleDetail(id);
    if (view === 'lesson') renderLessonView(id);
}

/* ── Offline / standalone helpers ── */
function isOffline() {
    return !!(window.COURSE_DATA);
}

function checkAnswerOffline(exId, answer) {
    const lessons = window.COURSE_DATA.lessons;
    for (const key of Object.keys(lessons)) {
        const lesson = lessons[key];
        for (const ex of (lesson.exercises || [])) {
            if (ex.id === exId) {
                let correct = false;
                if (ex.type === 'MULTIPLE_CHOICE') {
                    correct = parseInt(answer, 10) === ex.correctOptionIndex;
                } else {
                    const patterns = ex.requiredPatterns || [];
                    correct = patterns.length > 0 && patterns.every(p => answer.includes(p));
                }
                return {
                    correct,
                    feedback:     correct ? ex.correctFeedback    : ex.incorrectFeedback,
                    explanation:  ex.explanation || '',
                    solution:     ex.solutionCode || null
                };
            }
        }
    }
    return { correct: false, feedback: 'Øvelse ikke funnet.', solution: null };
}

/* ── Boot ── */
window.addEventListener('load', async () => {
    updateXP();
    try {
        if (isOffline()) {
            // Android / standalone mode – use bundled courseData.js
            allModules = window.COURSE_DATA.modules;
        } else {
            const res = await fetch('/api/modules');
            allModules = await res.json();
        }
        renderHome();
    } catch (e) {
        document.getElementById('app').innerHTML =
            '<p style="color:#ef4444;padding:40px">Kunne ikke laste kursdata. Sørg for at Main.java kjører.</p>';
    }
});

/* ── HOME VIEW ── */
function renderHome() {
    currentLesson = null;
    currentModuleId = null;

    const totalLessons = allModules.reduce((s, m) => s + m.lessonCount, 0);
    const totalAvail   = allModules.filter(m => m.available).length;

    const grid = allModules.map(m => {
        const prog  = getModuleProgress(m.id);
        const pct   = prog.total > 0 ? Math.round((prog.done / prog.total) * 100) : 0;
        const done  = pct === 100 && prog.total > 0;
        const badge = !m.available ? 'badge-locked 🔒 Kommer snart'
                    : done         ? 'badge-done ✅ Fullført'
                    :                'badge-available ▶ Tilgjengelig';
        const [bClass, bText] = [badge.split(' ')[0], badge.split(' ').slice(1).join(' ')];

        return `
        <div class="module-card ${!m.available ? 'locked' : ''}"
             onclick="${m.available ? `navigate('module', ${m.id})` : ''}"
             style="--card-color:${m.color}">
            <div class="module-card-header"></div>
            <div class="module-card-body">
                <div class="module-icon-row">
                    <span class="module-icon">${m.icon}</span>
                    <span class="module-badge ${bClass}">${bText}</span>
                </div>
                <div class="module-num">Modul ${m.id}</div>
                <div class="module-title">${m.title}</div>
                <div class="module-desc">${m.description}</div>
                ${m.available && prog.total > 0 ? `
                <div class="module-progress">
                    <div class="progress-bar-wrap">
                        <div class="progress-bar-fill" style="width:${pct}%; --card-color:${m.color}"></div>
                    </div>
                    <div class="progress-text">${prog.done} / ${prog.total} leksjoner fullført</div>
                </div>` : ''}
            </div>
        </div>`;
    }).join('');

    document.getElementById('app').innerHTML = `
        <div class="home-hero">
            <h1>Lær Java fra grunnen</h1>
            <p>Et interaktivt kurs med veiledning, kodeeksempler og øvelser – tilpasset nybegynnere.</p>
            <div class="hero-stats">
                <div class="hero-stat"><span class="num">${allModules.length}</span><span class="lbl">Moduler</span></div>
                <div class="hero-stat"><span class="num">${totalLessons}</span><span class="lbl">Leksjoner</span></div>
                <div class="hero-stat"><span class="num">${totalAvail}</span><span class="lbl">Tilgjengelig nå</span></div>
            </div>
        </div>
        <div class="section-title">Kursinnhold</div>
        <div class="modules-grid">${grid}</div>
    `;
}

/* ── MODULE DETAIL VIEW ── */
function renderModuleDetail(moduleId) {
    currentModuleId = moduleId;
    const m = allModules.find(x => x.id === moduleId);
    if (!m) return navigate('home');

    const lessonItems = m.lessons.map((ls, i) => {
        const cached = lessonCache[ls.id];
        const done = cached && isLessonDone(cached);
        return `
        <div class="lesson-item ${done ? 'done' : ''}" onclick="navigate('lesson', ${ls.id})">
            <div class="lesson-item-left">
                <div class="lesson-num">${done ? '✓' : (i + 1)}</div>
                <div>
                    <div class="lesson-item-title">${ls.title}</div>
                    <div class="lesson-item-sub">${ls.exerciseCount} øvelse${ls.exerciseCount !== 1 ? 'r' : ''}</div>
                </div>
            </div>
            <span class="lesson-arrow">→</span>
        </div>`;
    }).join('') || '<p style="color:var(--text-dim);padding:20px">Ingen leksjoner ennå.</p>';

    document.getElementById('app').innerHTML = `
        <button class="back-btn" onclick="navigate('home')">← Tilbake til moduler</button>
        <div class="module-detail-header" style="border-top: 3px solid ${m.color}">
            <span class="module-detail-icon">${m.icon}</span>
            <div>
                <div style="color:var(--text-dim);font-size:0.85rem;margin-bottom:4px">Modul ${m.id}</div>
                <h1>${m.title}</h1>
                <p>${m.description}</p>
            </div>
        </div>
        <div class="section-title">Leksjoner</div>
        <div class="lessons-list">${lessonItems}</div>
    `;
}

/* ── LESSON VIEW ── */
async function renderLessonView(lessonId) {
    document.getElementById('app').innerHTML =
        '<p style="color:var(--text-muted);padding:60px;text-align:center">Laster leksjon...</p>';

    let lesson = lessonCache[lessonId];
    if (!lesson) {
        try {
            if (isOffline()) {
                lesson = window.COURSE_DATA.lessons[String(lessonId)];
                if (!lesson) throw new Error('Leksjon ikke funnet');
            } else {
                const res = await fetch(`/api/lessons/${lessonId}`);
                lesson = await res.json();
            }
            lessonCache[lessonId] = lesson;
        } catch (e) {
            document.getElementById('app').innerHTML =
                '<p style="color:#ef4444;padding:40px">Kunne ikke laste leksjon.</p>';
            return;
        }
    }

    currentLesson  = lesson;
    currentExIndex = 0;
    answered       = false;

    const m = allModules.find(x => x.id === lesson.moduleId);
    const backTarget = m ? `navigate('module', ${m.id})` : `navigate('home')`;
    const moduleTitle = m ? m.title : 'Hjem';

    document.getElementById('app').innerHTML = `
        <button class="back-btn" onclick="${backTarget}">← ${moduleTitle}</button>
        <div class="lesson-header">
            <div class="breadcrumb">Modul ${lesson.moduleId} › <span>${lesson.title}</span></div>
            <h1>${lesson.title}</h1>
        </div>

        <!-- Step 1: Guide -->
        <div class="lesson-step" id="step-guide">
            <div class="lesson-step-header">
                <div class="step-num">1</div>
                <div class="step-title">Veiledning</div>
            </div>
            <div class="lesson-step-body">
                <div class="guide-content">${lesson.guideContent}</div>
            </div>
        </div>

        <!-- Step 2: Code Example -->
        ${lesson.codeExample ? `
        <div class="lesson-step" id="step-code">
            <div class="lesson-step-header">
                <div class="step-num">2</div>
                <div class="step-title">Kodeeksempel</div>
            </div>
            <div class="lesson-step-body">
                <button class="code-example-btn" id="code-toggle-btn" onclick="toggleCodeExample()">
                    <span>▶</span>
                    <span class="arrow">›</span>
                    ${lesson.codeExampleTitle || 'Se kodeeksempel'}
                </button>
                <div id="code-example-block" style="display:none">
                    <div class="code-block-wrapper" style="margin-top:16px">
                        <div class="code-block-title">
                            <span class="dot dot-r"></span>
                            <span class="dot dot-y"></span>
                            <span class="dot dot-g"></span>
                            <span style="margin-left:6px">${lesson.codeExampleTitle || 'Eksempel.java'}</span>
                        </div>
                        <pre><code class="language-java">${escapeHtml(lesson.codeExample)}</code></pre>
                    </div>
                </div>
            </div>
        </div>` : ''}

        <!-- Step 3: Exercises -->
        ${lesson.exercises && lesson.exercises.length > 0 ? `
        <div class="lesson-step" id="step-exercises">
            <div class="lesson-step-header">
                <div class="step-num">${lesson.codeExample ? '3' : '2'}</div>
                <div class="step-title">Øvelser</div>
            </div>
            <div class="lesson-step-body" id="exercise-container">
                <!-- injected by renderExercise() -->
            </div>
        </div>` : `
        <div style="text-align:center;padding:40px">
            <button class="btn btn-primary" onclick="${backTarget}">← Tilbake</button>
        </div>`}
    `;

    // Highlight code blocks in guide
    document.querySelectorAll('.guide-content pre code').forEach(b => {
        b.classList.add('language-java');
        hljs.highlightElement(b);
    });

    if (lesson.exercises && lesson.exercises.length > 0) {
        renderExercise();
    }
}

function toggleCodeExample() {
    const block = document.getElementById('code-example-block');
    const btn   = document.getElementById('code-toggle-btn');
    const shown = block.style.display !== 'none';
    block.style.display = shown ? 'none' : 'block';
    btn.classList.toggle('open', !shown);
    if (!shown) {
        block.querySelectorAll('pre code').forEach(b => hljs.highlightElement(b));
    }
}

/* ── EXERCISE RENDERER ── */
function renderExercise() {
    const container = document.getElementById('exercise-container');
    if (!container) return;

    const exercises = currentLesson.exercises;
    const ex = exercises[currentExIndex];
    answered = false;

    // Dots
    const dots = exercises.map((e, i) => {
        const cls = isExerciseDone(e.id) ? 'done'
                  : i === currentExIndex  ? 'current' : '';
        return `<div class="ex-dot ${cls}"></div>`;
    }).join('');

    const typeBadge = ex.type === 'MULTIPLE_CHOICE'
        ? '<span class="exercise-type-badge badge-mc">🅰 Flervalg</span>'
        : '<span class="exercise-type-badge badge-code">💻 Kodeoppgave</span>';

    // Question (handle multi-line with code block)
    const [qText, qCode] = splitQuestionCode(ex.question);

    const questionHtml = `
        <div class="exercise-question">
            ${qText}
            ${qCode ? `<pre>${escapeHtml(qCode)}</pre>` : ''}
        </div>`;

    // Input area
    const inputHtml = ex.type === 'MULTIPLE_CHOICE'
        ? renderMCOptions(ex)
        : renderCodeEditor(ex);

    container.innerHTML = `
        <div class="exercise-container">
            <div class="exercise-header">
                <div class="exercise-progress-dots">${dots}</div>
                <div style="display:flex;align-items:center;gap:10px">
                    <span style="font-size:0.8rem;color:var(--text-dim)">${currentExIndex + 1} / ${exercises.length}</span>
                    ${typeBadge}
                </div>
            </div>
            <div class="exercise-body">
                ${questionHtml}
                ${inputHtml}
                <div class="feedback-box" id="feedback-box"></div>
                <div class="exercise-actions" id="ex-actions">
                    ${ex.type === 'MULTIPLE_CHOICE'
                        ? `<button class="btn btn-primary" id="submit-btn" onclick="submitMC()" disabled>Sjekk svar</button>`
                        : `<button class="btn btn-primary" id="submit-btn" onclick="submitCode()">Kjør kode ▶</button>`}
                    ${ex.hint ? `<button class="btn btn-ghost" onclick="showHint()">💡 Hint</button>` : ''}
                </div>
            </div>
        </div>`;

    // Highlight code editor (non-editable preview) – skip, it's a textarea
}

function renderMCOptions(ex) {
    const letters = ['A', 'B', 'C', 'D'];
    return `<div class="mc-options" id="mc-options">` +
        ex.options.map((opt, i) =>
            `<div class="mc-option" id="mc-${i}" onclick="selectMC(${i})">
                <div class="mc-option-letter">${letters[i]}</div>
                <span>${escapeHtml(opt)}</span>
            </div>`
        ).join('') +
        `</div>`;
}

function renderCodeEditor(ex) {
    const template = ex.codeTemplate || 'public class LøsOppgaven {\n    public static void main(String[] args) {\n        // Skriv koden din her\n        \n    }\n}';
    return `
        <div class="code-editor-label">✏️ Skriv Java-kode:</div>
        <textarea class="code-editor" id="code-input" spellcheck="false"
            onkeydown="handleTabKey(event)">${escapeHtml(template)}</textarea>
        <div class="editor-hint">Tips: Trykk Tab for innrykk</div>`;
}

let selectedMCIndex = null;

function selectMC(index) {
    if (answered) return;
    selectedMCIndex = index;
    document.querySelectorAll('.mc-option').forEach((el, i) => {
        el.classList.toggle('selected', i === index);
    });
    const btn = document.getElementById('submit-btn');
    if (btn) btn.disabled = false;
}

async function submitMC() {
    if (answered || selectedMCIndex === null) return;
    answered = true;

    const ex = currentLesson.exercises[currentExIndex];
    const res = await checkAnswer(ex.id, String(selectedMCIndex));

    // Highlight options
    document.querySelectorAll('.mc-option').forEach((el, i) => {
        if (i === ex.correctOptionIndex) el.classList.add('correct');
        else if (i === selectedMCIndex && !res.correct) el.classList.add('wrong');
    });

    showFeedback(res, ex);
    updateExDot();
    if (res.correct) markExerciseDone(ex.id);
}

async function submitCode() {
    if (answered) return;
    answered = true;

    const ex   = currentLesson.exercises[currentExIndex];
    const code = document.getElementById('code-input')?.value || '';
    const res  = await checkAnswer(ex.id, code);

    showFeedback(res, ex);
    updateExDot();
    if (res.correct) markExerciseDone(ex.id);
}

async function checkAnswer(exId, answer) {
    try {
        if (isOffline()) {
            return checkAnswerOffline(exId, answer);
        }
        const res = await fetch('/api/check', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ exerciseId: exId, answer })
        });
        return await res.json();
    } catch (e) {
        return { correct: false, feedback: 'Nettverksfeil.', explanation: '', solution: null };
    }
}

function showFeedback(res, ex) {
    const box = document.getElementById('feedback-box');
    const cls = res.correct ? 'feedback-correct' : 'feedback-wrong';
    const icon = res.correct ? '✅' : '❌';

    const solutionHtml = !res.correct && ex.solutionCode ? `
        <button class="solution-toggle" onclick="toggleSolution()">Vis løsningsforslag</button>
        <div class="solution-block" id="solution-block">
            <pre><code class="language-java">${escapeHtml(ex.solutionCode)}</code></pre>
        </div>` : '';

    box.className = `feedback-box visible ${cls}`;
    box.innerHTML = `
        <div class="feedback-title">${icon} ${res.feedback}</div>
        ${res.explanation ? `<div class="feedback-body">${res.explanation}</div>` : ''}
        ${solutionHtml}`;

    // Add next/finish button
    const actions = document.getElementById('ex-actions');
    const exercises = currentLesson.exercises;
    const isLast = currentExIndex >= exercises.length - 1;

    if (isLast) {
        actions.innerHTML = `<button class="btn btn-success" onclick="finishLesson()">🎉 Fullfør leksjon</button>`;
    } else {
        actions.innerHTML = `<button class="btn btn-primary" onclick="nextExercise()">Neste øvelse →</button>`;
    }

    // Highlight any code in the solution
    box.querySelectorAll('pre code').forEach(b => hljs.highlightElement(b));
}

function toggleSolution() {
    const block = document.getElementById('solution-block');
    block.classList.toggle('visible');
    if (block.classList.contains('visible')) {
        block.querySelectorAll('pre code').forEach(b => hljs.highlightElement(b));
    }
}

function updateExDot() {
    const dots = document.querySelectorAll('.ex-dot');
    if (dots[currentExIndex]) {
        dots[currentExIndex].classList.remove('current');
        if (isExerciseDone(currentLesson.exercises[currentExIndex].id)) {
            dots[currentExIndex].classList.add('done');
        }
    }
}

function nextExercise() {
    currentExIndex++;
    answered = false;
    selectedMCIndex = null;
    renderExercise();
    document.getElementById('step-exercises')?.scrollIntoView({ behavior: 'smooth', block: 'start' });
}

function finishLesson() {
    const m = allModules.find(x => x.id === currentLesson.moduleId);
    const backFn = m ? `navigate('module', ${m.id})` : `navigate('home')`;
    const earnedXP = currentLesson.exercises.length * 20;

    document.getElementById('exercise-container').innerHTML = `
        <div class="complete-card" style="animation: popIn 0.4s ease">
            <div class="complete-trophy">🏆</div>
            <h2>Leksjon fullført!</h2>
            <p>Du har fullført <strong>${currentLesson.title}</strong>. Godt jobbet!</p>
            <div class="complete-xp">+${earnedXP} XP opptjent</div>
            <div style="display:flex;gap:12px;justify-content:center;flex-wrap:wrap">
                <button class="btn btn-ghost" onclick="${backFn}">← Tilbake til modulen</button>
                <button class="btn btn-primary" onclick="navigate('home')">🏠 Til hjem</button>
            </div>
        </div>`;

    showToast('🎉 Leksjon fullført! +' + earnedXP + ' XP');
}

function showHint() {
    const ex = currentLesson.exercises[currentExIndex];
    showToast('💡 Hint: ' + ex.hint, 4000);
}

/* ── Utilities ── */
function escapeHtml(str) {
    if (!str) return '';
    return str
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;');
}

function splitQuestionCode(q) {
    const nl = q.indexOf('\n');
    if (nl === -1) return [q, null];
    return [q.slice(0, nl), q.slice(nl + 1)];
}

function handleTabKey(e) {
    if (e.key === 'Tab') {
        e.preventDefault();
        const ta = e.target;
        const start = ta.selectionStart;
        const end   = ta.selectionEnd;
        ta.value = ta.value.slice(0, start) + '    ' + ta.value.slice(end);
        ta.selectionStart = ta.selectionEnd = start + 4;
    }
}

let toastTimer;
function showToast(msg, ms = 3000) {
    const t = document.getElementById('toast');
    t.textContent = msg;
    t.classList.remove('hidden');
    clearTimeout(toastTimer);
    toastTimer = setTimeout(() => t.classList.add('hidden'), ms);
}
