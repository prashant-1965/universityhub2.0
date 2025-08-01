function navigateTo(url) {
  const button = event.currentTarget;
  button.style.transform = "scale(0.95)";

  setTimeout(() => {
    button.style.transform = "scale(1)";
    setTimeout(() => {
      window.location.href = url;
    }, 200);
  }, 100);
}

function updateTimeDisplay() {
  const now = new Date();
  const options = { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' };
  document.getElementById('timeDisplay').textContent = now.toLocaleDateString('en-US', options);
}

updateTimeDisplay();

const cards = document.querySelectorAll('.dashboard-card');
cards.forEach(card => {
  card.addEventListener('mouseenter', () => {
    card.style.transform = 'translateY(-10px)';
  });

  card.addEventListener('mouseleave', () => {
    card.style.transform = 'translateY(0)';
  });
});
