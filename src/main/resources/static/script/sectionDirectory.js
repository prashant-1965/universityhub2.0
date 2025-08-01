// Initialize stats
function initializeStats() {
  // This would normally be fetched from the server
  document.getElementById('totalSections').textContent = '8';
  document.getElementById('activeSections').textContent = '7';
  document.getElementById('newSections').textContent = '2';
}

window.addEventListener('load', initializeStats);

function showToast(message, type = 'success') {
  const toast = document.getElementById('toast');
  toast.innerHTML = type === 'success' ?
  `<i class="fas fa-check-circle"></i> ${message}` :
  `<i class="fas fa-exclamation-circle"></i> ${message}`;
  toast.className = `toast ${type} visible`;

  setTimeout(() => {
    toast.className = 'toast';
  }, 3000);
}

// Search functionality
document.getElementById('searchInput').addEventListener('keyup', function() {
  const searchValue = this.value.toLowerCase();
  const rows = document.querySelectorAll('#sectionTable tbody tr');

  let visibleCount = 0;

  rows.forEach(row => {
    const text = row.textContent.toLowerCase();
    if (text.includes(searchValue)) {
      row.style.display = '';
      visibleCount++;
    } else {
      row.style.display = 'none';
    }
  });

  // Show/hide empty state
  document.getElementById('emptyState').style.display = visibleCount === 0 ? 'block' : 'none';
});

// Delete section function
async function deleteSection(code) {
  if (!confirm(`Are you sure you want to delete section ${code}?`)) {
    return;
  }

  try {
    const response = await fetch(`/section/deleteSectionByCode/${code}`, {
      method: "DELETE"
    });

    const msg = await response.text();
    showToast(msg || `${code} deleted successfully`, response.ok ? 'success' : 'error');

    if (response.ok) {
      loadSections();

      // Update stats
      const totalElement = document.getElementById('totalSections');
      const total = parseInt(totalElement.textContent);
      if (total > 0) totalElement.textContent = (total - 1).toString();

      const activeElement = document.getElementById('activeSections');
      const active = parseInt(activeElement.textContent);
      if (active > 0) activeElement.textContent = (active - 1).toString();
    }
  } catch (error) {
    showToast("Error deleting section", 'error');
  }
}

// Load sections data
async function loadSections() {
  const tableContainer = document.getElementById("tableContainer");
  const loadingElement = document.getElementById("loadingTable");
  const emptyState = document.getElementById("emptyState");
  const tbody = document.getElementById("sectionTable").querySelector("tbody");

  // Show loading state
  loadingElement.style.display = "flex";

  try {
    const response = await fetch("/section/getAllSection");
    const sections = await response.json();

    // Hide loading state
    loadingElement.style.display = "none";

    tbody.innerHTML = "";

    if (sections && sections.length > 0) {
      sections.forEach(section => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${section.id || '-'}</td>
          <td>${section.sectionCode || '-'}</td>
          <td>${section.sectionInCharge || '-'}</td>
          <td>
            <div class="actions">
              <button class="btn-action btn-view" title="View Details" onclick="viewSection('${section.sectionCode}')">
                <i class="fas fa-eye"></i>
              </button>
              <button class="btn-action btn-edit" title="Edit Section" onclick="editSection('${section.sectionCode}')">
                <i class="fas fa-edit"></i>
              </button>
              <button class="btn-action btn-delete" title="Delete Section" onclick="deleteSection('${section.sectionCode}')">
                <i class="fas fa-trash-alt"></i>
              </button>
            </div>
          </td>
        `;
        tbody.appendChild(row);
      });

      emptyState.style.display = "none";
      document.getElementById('totalSections').textContent = sections.length;
    } else {
      emptyState.style.display = "block";
      document.getElementById('totalSections').textContent = '0';
    }
  } catch (error) {
    loadingElement.style.display = "none";
    emptyState.style.display = "block";
    showToast("Error loading sections", 'error');
  }
}

// View section details
function viewSection(code) {
  showToast(`Viewing section ${code}`, 'success');
  // In a real application, you would navigate to a details page
  // window.location.href = `/sectionUi/viewSection/${code}`;
}

// Edit section function
function editSection(code) {
  showToast(`Editing section ${code}`, 'success');
  // In a real application, you would navigate to an edit page
  // window.location.href = `/sectionUi/editSection/${code}`;
}

// Initialize data loading when page loads
window.onload = () => {
  loadSections();
  initializeStats();
};

// Add animations for cards
document.querySelectorAll('.card').forEach((card, index) => {
  card.style.animationDelay = `${0.1 + (index * 0.15)}s`;
});