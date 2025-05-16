/**
 * SocialApp - Main JavaScript File
 */

// Document Ready Function
document.addEventListener('DOMContentLoaded', function() {
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Initialize popovers
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });
    
    // File input preview for image uploads
    const fileInputs = document.querySelectorAll('.custom-file-input');
    fileInputs.forEach(input => {
        input.addEventListener('change', function() {
            // Update file name display
            const fileName = this.value.split('\\').pop();
            const label = this.nextElementSibling;
            label.textContent = fileName;
            
            // Preview image if applicable
            const preview = document.getElementById('preview');
            if (preview && this.files && this.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    preview.src = e.target.result;
                    preview.style.display = 'block';
                }
                reader.readAsDataURL(this.files[0]);
            }
        });
    });
    
    // Auto-dismiss alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            const closeButton = alert.querySelector('.close');
            if (closeButton) {
                closeButton.click();
            }
        }, 5000);
    });
    
    // Like button animation
    const likeButtons = document.querySelectorAll('.btn-like');
    likeButtons.forEach(button => {
        button.addEventListener('click', function() {
            this.classList.add('animate__animated', 'animate__heartBeat');
        });
    });
});

// Character counter for text areas
function countCharacters(textarea, counterElement, maxLength) {
    const counter = document.getElementById(counterElement);
    const length = textarea.value.length;
    counter.textContent = length + '/' + maxLength;
    
    if (length > maxLength) {
        counter.classList.add('text-danger');
    } else {
        counter.classList.remove('text-danger');
    }
}

// Confirm delete actions
function confirmDelete(message) {
    return confirm(message || 'Êtes-vous sûr de vouloir supprimer cet élément ?');
}

// Toggle follow/unfollow button
function toggleFollowButton(button, username, action) {
    const form = button.closest('form');
    form.action = '/users/' + username + '/' + action;
    
    if (action === 'follow') {
        button.innerHTML = '<i class="bi bi-person-dash"></i> Ne plus suivre';
        button.classList.replace('btn-primary', 'btn-outline-primary');
    } else {
        button.innerHTML = '<i class="bi bi-person-plus"></i> Suivre';
        button.classList.replace('btn-outline-primary', 'btn-primary');
    }
}
