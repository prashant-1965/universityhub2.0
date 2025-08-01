function initializeStats() {
        // This would normally be fetched from the server
        document.getElementById('totalStudents').textContent = '345';
        document.getElementById('activeStudents').textContent = '312';
        document.getElementById('newStudents').textContent = '87';
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

    function navigateTo(url) {
        const button = event.currentTarget;
        button.classList.add('loading');

        setTimeout(() => {
            button.classList.remove('loading');
            window.location.href = url;
        }, 500);
    }

    // Show loading state for buttons
    function showLoading(button) {
        button.classList.add('loading');
    }

    function hideLoading(button) {
        button.classList.remove('loading');
    }

    // Add Student
    document.getElementById("addStudentForm").onsubmit = async (e) => {
        e.preventDefault();
        const submitButton = e.target.querySelector('button[type="submit"]');
        showLoading(submitButton);

        const student = {
            firstName: document.getElementById("addFirstName").value,
            lastName: document.getElementById("addLastName").value,
            location: document.getElementById("addLocation").value,
            email: document.getElementById("addEmail").value
        };

        if (!student.firstName || !student.lastName || !student.location || !student.email) {
            hideLoading(submitButton);
            showToast('Please fill in all fields', 'error');
            return;
        }

        try {
            const response = await fetch("/student/addStudent", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(student)
            });

            hideLoading(submitButton);
            const msg = await response.text();
            showToast(msg, response.ok ? 'success' : 'error');

            if (response.ok) {
                document.getElementById("addStudentForm").reset();

                // Update stats
                const totalElement = document.getElementById('totalStudents');
                const total = parseInt(totalElement.textContent);
                totalElement.textContent = (total + 1).toString();

                const activeElement = document.getElementById('activeStudents');
                const active = parseInt(activeElement.textContent);
                activeElement.textContent = (active + 1).toString();
            }
        } catch (error) {
            hideLoading(submitButton);
            showToast("Failed to add student", 'error');
        }
    };

    // Get Student by ID
    document.getElementById("getStudentForm").onsubmit = async (e) => {
        e.preventDefault();
        const submitButton = e.target.querySelector('button[type="submit"]');
        showLoading(submitButton);

        const resultContainer = document.getElementById("studentResult");
        resultContainer.classList.remove('show');

        const id = document.getElementById("getId").value;

        if (!id) {
            hideLoading(submitButton);
            showToast('Please enter a student ID', 'error');
            return;
        }

        try {
            const response = await fetch(`/student/studentById/${id}`);
            hideLoading(submitButton);

            if (response.ok) {
                const student = await response.json();
                if (student && student.stdId) {
                    resultContainer.innerHTML = `
            <div style="font-weight: bold; margin-bottom: 8px;">Student Details:</div>
            <div><strong>ID:</strong> ${student.stdId}</div>
            <div><strong>Name:</strong> ${student.firstName} ${student.lastName}</div>
            <div><strong>Location:</strong> ${student.location}</div>
            <div><strong>Email:</strong> ${student.email}</div>
          `;
                    resultContainer.classList.add('show');
                    showToast(`Student found successfully`, 'success');
                } else {
                    showToast("Student not found", 'error');
                }
            } else {
                showToast("Student not found", 'error');
            }
        } catch (error) {
            hideLoading(submitButton);
            showToast("Error fetching student", 'error');
        }
    };

    // Delete Student by ID
    document.getElementById("deleteStudentForm").onsubmit = async (e) => {
        e.preventDefault();
        const submitButton = e.target.querySelector('button[type="submit"]');
        showLoading(submitButton);

        const id = document.getElementById("deleteId").value;

        if (!id) {
            hideLoading(submitButton);
            showToast('Please enter a student ID', 'error');
            return;
        }

        try {
            const response = await fetch(`/student/removeStudent/${id}`, {
                method: "DELETE"
            });

            hideLoading(submitButton);
            const msg = await response.text();
            showToast(msg || `Student ${id} deleted successfully`, response.ok ? 'success' : 'error');

            if (response.ok) {
                document.getElementById("deleteStudentForm").reset();

                // Update stats
                const totalElement = document.getElementById('totalStudents');
                const total = parseInt(totalElement.textContent);
                if (total > 0) totalElement.textContent = (total - 1).toString();

                const activeElement = document.getElementById('activeStudents');
                const active = parseInt(activeElement.textContent);
                if (active > 0) activeElement.textContent = (active - 1).toString();
            }
        } catch (error) {
            hideLoading(submitButton);
            showToast("Error deleting student", 'error');
        }
    };

    // Add hover effects
    const cards = document.querySelectorAll('.card');
    cards.forEach(card => {
        card.addEventListener('mouseenter', () => {
            card.style.transform = 'translateY(-10px)';
        });

        card.addEventListener('mouseleave', () => {
            card.style.transform = 'translateY(0)';
        });
    });