function removeTempElements() {
    document.querySelectorAll('#temp').forEach(element => element.remove());
}

function loadEmployeeList() {
    removeTempElements();
    setTimeout(() => {
        $('#content').load('/fragments/user/employeeList');
    }, 0);
}

function loadEmployeeAdd() {
    removeTempElements();
    setTimeout(() => {
        $('#content').load('/fragments/admin/employeeAdd');
    }, 0);
}

function spawnToast(header, content) {
    $.get('/fragments/user/toast.html', function(data) {
        var toast = $(data);

        toast.find('#toast-header-marker').text(header);
        toast.find('#toast-content-marker').text(content);

        var toastContainer = $('#toast-container');
        if (toastContainer.length === 0) {
            toastContainer = $('<div id="toast-container" class="toast-container position-fixed bottom-0 end-0 p-3"></div>');
            $('body').append(toastContainer);
        }
        toastContainer.append(toast);

        var bootstrapToast = new bootstrap.Toast(toast[0]);
        bootstrapToast.show();
    });
}

$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
    if (jqxhr.status === 403) {
        spawnToast('Access Denied', 'You do not have permission to access this resource.');
    }
});

$('#user-profile-form').validate({
    rules: {
        firstname: {
            required: true,
            minLength: 3
        },
        lastname: {
            required: true,
            minLength: 5
        }
    }
});