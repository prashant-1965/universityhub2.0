// Initialize stats (in a real app, these would come from the backend)
  function initializeStats() {
    // This would normally be fetched from the server
    document.getElementById('totalSections').textContent = '15';
    document.getElementById('activeSections').textContent = '12';
    document.getElementById('newSections').textContent = '4';
  }

  window.addEventListener('load', initializeStats);

  // Toast notification system
  function createToast(message, type = 'success') {
    const toastContainer = document.getElementById('toastContainer');

    // Create toast element
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;

    // Create content container
    const toastContent = document.createElement('div');
    toastContent.className = 'toast-content';

    // Add appropriate icon based on toast type
    const iconClass = {
      'success': 'fas fa-check-circle',
      'error': 'fas fa-exclamation-circle',
      'info': 'fas fa-info-circle',
      'warning': 'fas fa-exclamation-triangle'
    };

    const icon = document.createElement('span');
    icon.className = `toast-icon ${iconClass[type]}`;
    toastContent.appendChild(icon);

    // Add message
    const messageSpan = document.createElement('span');
    messageSpan.textContent = message;
    toastContent.appendChild(messageSpan);

    toast.appendChild(toastContent);

    // Add close button
    const closeBtn = document.createElement('button');
    closeBtn.className = 'toast-close';
    closeBtn.innerHTML = '&times;';
    closeBtn.addEventListener('click', () => {
      toast.classList.remove('visible');
      setTimeout(() => {
        toastContainer.removeChild(toast);
      }, 500);
    });
    toast.appendChild(closeBtn);

    // Add toast to container
    toastContainer.appendChild(toast);

    // Make toast visible after a small delay (for animation)
    setTimeout(() => {
      toast.classList.add('visible');
    }, 10);

    // Auto remove after 5 seconds
    setTimeout(() => {
      if (toastContainer.contains(toast)) {
        toast.classList.remove('visible');
        setTimeout(() => {
          if (toastContainer.contains(toast)) {
            toastContainer.removeChild(toast);
          }
        }, 500);
      }
    }, 5000);
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

  // Add section functionality
  document.getElementById("addSectionForm").onsubmit = async (e) => {
    e.preventDefault();
    const submitButton = e.target.querySelector('button[type="submit"]');
    showLoading(submitButton);

    const sectionCode = document.getElementById("addCode").value;
    const sectionInCharge = document.getElementById("addInCharge").value;

    if (!sectionCode || !sectionInCharge) {
      hideLoading(submitButton);
      createToast('Please fill in all fields', 'error');
      return;
    }

    const section = {
      sectionCode: sectionCode,
      sectionInCharge: sectionInCharge
    };

    try {
      const response = await fetch("/section/addSection", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(section)
      });

      hideLoading(submitButton);
      const message = await response.text();

      if (response.ok) {
        createToast('Section added successfully!', 'success');

        // Clear form fields
        document.getElementById("addCode").value = "";
        document.getElementById("addInCharge").value = "";

        // Update stats
        const totalElement = document.getElementById('totalSections');
        const total = parseInt(totalElement.textContent);
        totalElement.textContent = (total + 1).toString();

        const activeElement = document.getElementById('activeSections');
        const active = parseInt(activeElement.textContent);
        activeElement.textContent = (active + 1).toString();
      } else {
        createToast(message || 'Failed to add section', 'error');
      }
    } catch (error) {
      hideLoading(submitButton);
      createToast('Network error. Please try again later.', 'error');
    }
  };

  // Find section functionality
  document.getElementById("getSectionForm").onsubmit = async (e) => {
    e.preventDefault();
    const submitButton = e.target.querySelector('button[type="submit"]');
    showLoading(submitButton);

    const resultContainer = document.getElementById("searchResult");
    resultContainer.classList.remove('show');

    const code = document.getElementById("getCode").value;

    if (!code) {
      hideLoading(submitButton);
      createToast('Please enter a section code', 'error');
      return;
    }

    try {
      const response = await fetch(`/section/getSectionByCode/${code}`);
      hideLoading(submitButton);

      if (response.ok) {
        const section = await response.json();
        if (section && section.sectionCode) {
          resultContainer.innerHTML = `
            <div style="font-weight: bold; margin-bottom: 8px;">Section Details:</div>
            <div><strong>Code:</strong> ${section.sectionCode}</div>
            <div><strong>In-Charge:</strong> ${section.sectionInCharge}</div>
          `;
          resultContainer.classList.add('show');
          createToast(`Section found successfully`, 'success');
        } else {
          createToast("Section not found", 'error');
        }
      } else {
        createToast("Section not found", 'error');
      }
    } catch (error) {
      hideLoading(submitButton);
      createToast("Error fetching section", 'error');
    }
  };

  // Confirm and delete section
  document.getElementById("deleteSectionForm").onsubmit = async (e) => {
    e.preventDefault();
    const submitButton = e.target.querySelector('button[type="submit"]');
    const code = document.getElementById("removeCode").value;

    if (!code) {
      createToast('Please enter a section code to delete', 'error');
      return;
    }

    // Create confirmation toast
    const toastContainer = document.getElementById('toastContainer');

    const toast = document.createElement('div');
    toast.className = 'toast warning visible';

    const toastContent = document.createElement('div');
    toastContent.className = 'toast-content';

    const icon = document.createElement('span');
    icon.className = 'toast-icon fas fa-exclamation-triangle';
    toastContent.appendChild(icon);

    const messageSpan = document.createElement('span');
    messageSpan.innerHTML = `Are you sure you want to delete section <strong>${code}</strong>?`;
    toastContent.appendChild(messageSpan);

    toast.appendChild(toastContent);

    // Add action buttons
    const actionContainer = document.createElement('div');
    actionContainer.style.marginLeft = '0.5rem';

    const confirmBtn = document.createElement('button');
    confirmBtn.textContent = 'Delete';
    confirmBtn.style.background = 'white';
    confirmBtn.style.color = '#f59e0b';
    confirmBtn.style.border = 'none';
    confirmBtn.style.padding = '0.25rem 0.75rem';
    confirmBtn.style.borderRadius = '0.5rem';
    confirmBtn.style.marginRight = '0.5rem';
    confirmBtn.style.fontWeight = '600';
    confirmBtn.style.cursor = 'pointer';
    confirmBtn.addEventListener('click', async () => {
      toastContainer.removeChild(toast);
      await deleteSectionByCode(code, submitButton);
    });
    actionContainer.appendChild(confirmBtn);

    const cancelBtn = document.createElement('button');
    cancelBtn.textContent = 'Cancel';
    cancelBtn.style.background = 'rgba(255,255,255,0.3)';
    cancelBtn.style.color = 'white';
    cancelBtn.style.border = 'none';
    cancelBtn.style.padding = '0.25rem 0.75rem';
    cancelBtn.style.borderRadius = '0.5rem';
    cancelBtn.style.fontWeight = '600';
    cancelBtn.style.cursor = 'pointer';
    cancelBtn.addEventListener('click', () => {
      toastContainer.removeChild(toast);
    });
    actionContainer.appendChild(cancelBtn);

    toast.appendChild(actionContainer);
    toastContainer.appendChild(toast);

    // Auto remove after 8 seconds
    setTimeout(() => {
      if (toastContainer.contains(toast)) {
        toastContainer.removeChild(toast);
      }
    }, 8000);
  };

  // Delete section functionality
  async function deleteSectionByCode(code, button) {
    showLoading(button);

    try {
      const response = await fetch(`/section/removeSectionByCode/${code}`, {
        method: "DELETE"
      });

      hideLoading(button);
      const message = await response.text();

      if (response.ok) {
        createToast(`Section '${code}' has been successfully deleted.`, 'success');
        document.getElementById("removeCode").value = "";

        // Update stats
        const totalElement = document.getElementById('totalSections');
        const total = parseInt(totalElement.textContent);
        if (total > 0) totalElement.textContent = (total - 1).toString();

        const activeElement = document.getElementById('activeSections');
        const active = parseInt(activeElement.textContent);
        if (active > 0) activeElement.textContent = (active - 1).toString();
      } else {
        createToast(message || 'Failed to delete section.', 'error');
      }
    } catch (error) {
      hideLoading(button);
      createToast('Network error. Please try again later.', 'error');
    }
  }

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