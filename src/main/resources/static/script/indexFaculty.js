// Initialize stats for the faculty dashboard
    function initializeStats() {
      // This would normally be fetched from the server
      document.getElementById('totalFaculty').textContent = '48';
      document.getElementById('activeFaculty').textContent = '42';
      document.getElementById('newFaculty').textContent = '8';
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

    // Add Faculty
    document.getElementById("addFacultyForm").onsubmit = async (e) => {
      e.preventDefault();
      const submitButton = e.target.querySelector('button[type="submit"]');
      showLoading(submitButton);

      const firstName = document.getElementById("firstName").value;
      const lastName = document.getElementById("lastName").value;
      const designation = document.getElementById("designation").value;
      const email = document.getElementById("email").value;
      const location = document.getElementById("location").value;

      if (!firstName || !lastName || !designation || !email || !location) {
        hideLoading(submitButton);
        showToast('Please fill in all fields', 'error');
        return;
      }

      const facultyData = {
        firstName,
        lastName,
        designation,
        email,
        location
      };

      try {
        const response = await fetch("/faculty/addFaculty", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(facultyData)
        });

        hideLoading(submitButton);
        const msg = await response.text();
        showToast(msg, response.ok ? 'success' : 'error');

        if (response.ok) {
          document.getElementById("addFacultyForm").reset();

          // Update stats
          const totalElement = document.getElementById('totalFaculty');
          const total = parseInt(totalElement.textContent);
          totalElement.textContent = (total + 1).toString();

          const activeElement = document.getElementById('activeFaculty');
          const active = parseInt(activeElement.textContent);
          activeElement.textContent = (active + 1).toString();
        }
      } catch (error) {
        hideLoading(submitButton);
        showToast("Failed to add faculty", 'error');
      }
    };

    // Get Faculty by UID
    document.getElementById("getFacultyForm").onsubmit = async (e) => {
      e.preventDefault();
      const submitButton = e.target.querySelector('button[type="submit"]');
      showLoading(submitButton);

      const resultContainer = document.getElementById("facultyResult");
      resultContainer.classList.remove('show');

      const uid = document.getElementById("facultyUid").value;

      if (!uid) {
        hideLoading(submitButton);
        showToast('Please enter a faculty UID', 'error');
        return;
      }

      try {
        const response = await fetch(`/faculty/findByUid/${uid}`);
        hideLoading(submitButton);

        if (response.ok) {
          const faculty = await response.json();
          if (faculty && faculty.uId) {
            resultContainer.innerHTML = `
              <div style="font-weight: bold; margin-bottom: 8px;">Faculty Details:</div>
              <div><strong>UID:</strong> ${faculty.uId}</div>
              <div><strong>Name:</strong> ${faculty.firstName} ${faculty.lastName}</div>
              <div><strong>Designation:</strong> ${faculty.designation}</div>
              <div><strong>Email:</strong> ${faculty.email}</div>
              <div><strong>Location:</strong> ${faculty.location}</div>
            `;
            resultContainer.classList.add('show');
            showToast(`Faculty found successfully`, 'success');
          } else {
            showToast("Faculty not found", 'error');
          }
        } else {
          showToast("Faculty not found", 'error');
        }
      } catch (error) {
        hideLoading(submitButton);
        showToast("Error fetching faculty", 'error');
      }
    };

    // Delete Faculty by UID
    document.getElementById("deleteFacultyForm").onsubmit = async (e) => {
      e.preventDefault();
      const submitButton = e.target.querySelector('button[type="submit"]');
      showLoading(submitButton);

      const uid = document.getElementById("facultyUidDelete").value;

      if (!uid) {
        hideLoading(submitButton);
        showToast('Please enter a faculty UID', 'error');
        return;
      }

      try {
        const response = await fetch(`/faculty/removeByUid/${uid}`, {
          method: "DELETE"
        });

        hideLoading(submitButton);
        const msg = await response.text();
        showToast(msg || `Faculty with UID ${uid} removed successfully`, response.ok ? 'success' : 'error');

        if (response.ok) {
          document.getElementById("deleteFacultyForm").reset();

          // Update stats
          const totalElement = document.getElementById('totalFaculty');
          const total = parseInt(totalElement.textContent);
          if (total > 0) totalElement.textContent = (total - 1).toString();

          const activeElement = document.getElementById('activeFaculty');
          const active = parseInt(activeElement.textContent);
          if (active > 0) activeElement.textContent = (active - 1).toString();
        }
      } catch (error) {
        hideLoading(submitButton);
        showToast("Error removing faculty", 'error');
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