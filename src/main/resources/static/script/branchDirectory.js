// Simulate stats (in a real app, these would come from the backend)
function initializeStats() {
  // This would normally be fetched from the server
  document.getElementById('totalBranches').textContent = '12';
  document.getElementById('activeBranches').textContent = '10';
  document.getElementById('newBranches').textContent = '3';
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
  const rows = document.querySelectorAll('#branchTable tbody tr');

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

// Delete branch function
async function deleteBranch(code) {
  if (!confirm(`Are you sure you want to delete branch ${code}?`)) {
    return;
  }

  try {
    const response = await fetch(`/branch/deleteBranchByCode/${code}`, {
      method: "DELETE"
    });

    const msg = await response.text();
    showToast(msg || `${code} deleted successfully`, response.ok ? 'success' : 'error');

    if (response.ok) {
      loadBranches();

      // Update stats
      const totalElement = document.getElementById('totalBranches');
      const total = parseInt(totalElement.textContent);
      if (total > 0) totalElement.textContent = (total - 1).toString();

      const activeElement = document.getElementById('activeBranches');
      const active = parseInt(activeElement.textContent);
      if (active > 0) activeElement.textContent = (active - 1).toString();
    }
  } catch (error) {
    showToast("Error deleting branch", 'error');
  }
}

// Load branches data
async function loadBranches() {
  const tableContainer = document.getElementById("tableContainer");
  const loadingElement = document.getElementById("loadingTable");
  const emptyState = document.getElementById("emptyState");
  const tbody = document.getElementById("branchTable").querySelector("tbody");

  // Show loading state
  loadingElement.style.display = "flex";

  try {
    const response = await fetch("/branch/getAllBranch");
    const branches = await response.json();

    // Hide loading state
    loadingElement.style.display = "none";

    tbody.innerHTML = "";

    if (branches && branches.length > 0) {
      branches.forEach(branch => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${branch.id || '-'}</td>
          <td>${branch.branchCode || '-'}</td>
          <td>${branch.branchName || '-'}</td>
          <td>${branch.branchHod || '-'}</td>
          <td>
            <div class="actions">
              <button class="btn-action btn-view" title="View Details" onclick="viewBranch('${branch.branchCode}')">
                <i class="fas fa-eye"></i>
              </button>
              <button class="btn-action btn-edit" title="Edit Branch" onclick="editBranch('${branch.branchCode}')">
                <i class="fas fa-edit"></i>
              </button>
              <button class="btn-action btn-delete" title="Delete Branch" onclick="deleteBranch('${branch.branchCode}')">
                <i class="fas fa-trash-alt"></i>
              </button>
            </div>
          </td>
        `;
        tbody.appendChild(row);
      });

      emptyState.style.display = "none";
      document.getElementById('totalBranches').textContent = branches.length;
    } else {
      emptyState.style.display = "block";
      document.getElementById('totalBranches').textContent = '0';
    }
  } catch (error) {
    loadingElement.style.display = "none";
    emptyState.style.display = "block";
    showToast("Error loading branches", 'error');
  }
}

// View branch details
function viewBranch(code) {
  // Redirect to a detail page or show in a modal
  showToast(`Viewing branch ${code}`, 'success');
  // In a real application, you would navigate to a details page
  // window.location.href = `/branchUi/viewBranch/${code}`;
}

// Edit branch function
function editBranch(code) {
  showToast(`Editing branch ${code}`, 'success');
  // In a real application, you would navigate to an edit page
  // window.location.href = `/branchUi/editBranch/${code}`;
}

// Initialize data loading when page loads
window.onload = () => {
  loadBranches();
  initializeStats();
};

// Add animations for cards
document.querySelectorAll('.card').forEach((card, index) => {
  card.style.animationDelay = `${0.1 + (index * 0.15)}s`;
});