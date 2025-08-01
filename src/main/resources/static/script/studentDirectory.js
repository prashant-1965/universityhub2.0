// Initialize stats
function initializeStats() {
    // This would normally be fetched from the server
    document.getElementById('totalStudents').textContent = '0';
    document.getElementById('activeStudents').textContent = '0';
    document.getElementById('newStudents').textContent = '0';
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
    const rows = document.querySelectorAll('#studentTable tbody tr');

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

// Delete student function
async function deleteStudent(id) {
    if (!confirm(`Are you sure you want to delete student with ID ${id}?`)) {
        return;
    }

    try {
        const response = await fetch(`/student/deleteStudent/${id}`, {
            method: "DELETE"
        });

        const msg = await response.text();
        showToast(msg || `Student ID ${id} deleted successfully`, response.ok ? 'success' : 'error');

        if (response.ok) {
            loadStudents();

            // Update stats
            const totalElement = document.getElementById('totalStudents');
            const total = parseInt(totalElement.textContent);
            if (total > 0) totalElement.textContent = (total - 1).toString();

            const activeElement = document.getElementById('activeStudents');
            const active = parseInt(activeElement.textContent);
            if (active > 0) activeElement.textContent = (active - 1).toString();
        }
    } catch (error) {
        showToast("Error deleting student", 'error');
    }
}

// Load students data
async function loadStudents() {
    const tableContainer = document.getElementById("tableContainer");
    const loadingElement = document.getElementById("loadingTable");
    const emptyState = document.getElementById("emptyState");
    const tbody = document.getElementById("studentTable").querySelector("tbody");

    // Show loading state
    loadingElement.style.display = "flex";

    try {
        const response = await fetch("/student/allStudent");
        const students = await response.json();

        // Hide loading state
        loadingElement.style.display = "none";

        tbody.innerHTML = "";

        if (students && students.length > 0) {
            students.forEach(student => {
                const row = document.createElement("tr");
                row.innerHTML = `
        <td>${student.stdId || '-'}</td>
        <td>${student.firstName || '-'}</td>
        <td>${student.lastName || '-'}</td>
        <td>${student.location || '-'}</td>
        <td>${student.email || '-'}</td>
        <td>
          <div class="actions">
            <button class="btn-action btn-view" title="View Details" onclick="viewStudent('${student.stdId}')">
              <i class="fas fa-eye"></i>
            </button>
            <button class="btn-action btn-edit" title="Edit Student" onclick="editStudent('${student.stdId}')">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn-action btn-delete" title="Delete Student" onclick="deleteStudent('${student.stdId}')">
              <i class="fas fa-trash-alt"></i>
            </button>
          </div>
        </td>
      `;
                tbody.appendChild(row);
            });

            emptyState.style.display = "none";
            document.getElementById('totalStudents').textContent = students.length;
            document.getElementById('activeStudents').textContent = students.length;
            document.getElementById('newStudents').textContent = Math.floor(students.length / 3); // Example calculation
        } else {
            emptyState.style.display = "block";
            document.getElementById('totalStudents').textContent = '0';
            document.getElementById('activeStudents').textContent = '0';
            document.getElementById('newStudents').textContent = '0';
        }
    } catch (error) {
        loadingElement.style.display = "none";
        emptyState.style.display = "block";
        showToast("Error loading students", 'error');
    }
}

// View student details
function viewStudent(id) {
    showToast(`Viewing student ${id}`, 'success');
    // In a real application, you would navigate to a details page
    // window.location.href = `/studentUi/viewStudent/${id}`;
}

// Edit student function
function editStudent(id) {
    showToast(`Editing student ${id}`, 'success');
    // In a real application, you would navigate to an edit page
    // window.location.href = `/studentUi/editStudent/${id}`;
}

// Initialize data loading when page loads
window.onload = () => {
    loadStudents();
    initializeStats();
};

// Add animations for cards
document.querySelectorAll('.card').forEach((card, index) => {
    card.style.animationDelay = `${0.1 + (index * 0.15)}s`;
});
