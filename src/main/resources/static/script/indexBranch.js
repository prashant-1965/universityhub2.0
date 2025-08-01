
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

    // Add Branch
    document.getElementById("addBranchForm").onsubmit = async (e) => {
      e.preventDefault();
      const submitButton = e.target.querySelector('button[type="submit"]');
      showLoading(submitButton);

      const branchCode = document.getElementById("branchCode").value;
      const branchName = document.getElementById("branchName").value;
      const branchHod = document.getElementById("branchHod").value;

      if (!branchCode || !branchName || !branchHod) {
        hideLoading(submitButton);
        showToast('Please fill in all fields', 'error');
        return;
      }

      const branchData = { branchCode, branchName, branchHod };

      try {
        const response = await fetch("/branch/addBranch", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(branchData)
        });

        hideLoading(submitButton);
        const msg = await response.text();
        showToast(msg, response.ok ? 'success' : 'error');

        if (response.ok) {
          document.getElementById("addBranchForm").reset();

          // Update stats
          const totalElement = document.getElementById('totalBranches');
          const total = parseInt(totalElement.textContent);
          totalElement.textContent = (total + 1).toString();

          const activeElement = document.getElementById('activeBranches');
          const active = parseInt(activeElement.textContent);
          activeElement.textContent = (active + 1).toString();
        }
      } catch (error) {
        hideLoading(submitButton);
        showToast("Failed to add branch", 'error');
      }
    };

    // Get Branch by Code
    document.getElementById("getBranchForm").onsubmit = async (e) => {
      e.preventDefault();
      const submitButton = e.target.querySelector('button[type="submit"]');
      showLoading(submitButton);

      const resultContainer = document.getElementById("branchResult");
      resultContainer.classList.remove('show');

      const code = document.getElementById("branchCodeQuery").value;

      if (!code) {
        hideLoading(submitButton);
        showToast('Please enter a branch code', 'error');
        return;
      }

      try {
        const response = await fetch(`/branch/getBranchByCode/${code}`);
        hideLoading(submitButton);

        if (response.ok) {
          const branch = await response.json();
          if (branch && branch.branchCode) {
            resultContainer.innerHTML = `
              <div style="font-weight: bold; margin-bottom: 8px;">Branch Details:</div>
              <div><strong>Code:</strong> ${branch.branchCode}</div>
              <div><strong>Name:</strong> ${branch.branchName}</div>
              <div><strong>HOD:</strong> ${branch.branchHod}</div>
            `;
            resultContainer.classList.add('show');
            showToast(`Branch found successfully`, 'success');
          } else {
            showToast("Branch not found", 'error');
          }
        } else {
          showToast("Branch not found", 'error');
        }
      } catch (error) {
        hideLoading(submitButton);
        showToast("Error fetching branch", 'error');
      }
    };

    // Delete Branch by Code
    document.getElementById("deleteBranchForm").onsubmit = async (e) => {
      e.preventDefault();
      const submitButton = e.target.querySelector('button[type="submit"]');
      showLoading(submitButton);

      const code = document.getElementById("branchCodeDelete").value;

      if (!code) {
        hideLoading(submitButton);
        showToast('Please enter a branch code', 'error');
        return;
      }

      try {
        const response = await fetch(`/branch/deleteBranchByCode/${code}`, {
          method: "DELETE"
        });

        hideLoading(submitButton);
        const msg = await response.text();
        showToast(msg || `${code} deleted successfully`, response.ok ? 'success' : 'error');

        if (response.ok) {
          document.getElementById("deleteBranchForm").reset();

          // Update stats
          const totalElement = document.getElementById('totalBranches');
          const total = parseInt(totalElement.textContent);
          if (total > 0) totalElement.textContent = (total - 1).toString();

          const activeElement = document.getElementById('activeBranches');
          const active = parseInt(activeElement.textContent);
          if (active > 0) activeElement.textContent = (active - 1).toString();
        }
      } catch (error) {
        hideLoading(submitButton);
        showToast("Error deleting branch", 'error');
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