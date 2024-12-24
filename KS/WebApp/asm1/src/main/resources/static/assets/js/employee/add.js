function sendCreateRequest() {
    const employeeData = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        dateOfBirth: document.getElementById('dob').value,
        gender: parseInt(document.querySelector('input[name="gender"]:checked').value),
        address: document.getElementById('address').value,
        department: document.getElementById('department').value,
        remarks: document.getElementById('remark').value
    };

    const permissions = Array.from(document.querySelectorAll('input[name="permissions"]:checked'))
        .map(checkbox => checkbox.value);

    const accountData = {
        account: document.getElementById('account').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        status: document.getElementById('active').checked ? 1 : 0,
        employee: employeeData,
        permissions: permissions
    };

    $.ajax({
        type: 'POST',
        url: '/api/admin/employee/add',
        contentType: 'application/json',
        data: JSON.stringify(accountData),
        success: function (data) {
            console.log('Success:', data);
        },
        error: function (jqxhr, status, error) {
            console.error('Error:', error);
        }
    });
}

function validateForm() {
    const firstNameElement = document.getElementById('firstName');
    const lastNameElement = document.getElementById('lastName');
    const dobElement = document.getElementById('dob');
    const genderElement = document.querySelector('input[name="gender"]:checked');
    const phoneNumberElement = document.getElementById('phoneNumber');
    const emailElement = document.getElementById('email');
    const accountElement = document.getElementById('account');
    const passwordElement = document.getElementById('password');
    const departmentElement = document.getElementById('department');

    const firstName = (firstNameElement ? firstNameElement.value : '').trim();
    const lastName = (lastNameElement ? lastNameElement.value : '').trim();
    const dob = (dobElement ? dobElement.value : '').trim();
    const gender = genderElement ? genderElement.value : null;
    const phoneNumber = (phoneNumberElement ? phoneNumberElement.value : '').trim();
    const email = (emailElement ? emailElement.value : '').trim();
    const account = (accountElement ? accountElement.value : '').trim();
    const password = (passwordElement ? passwordElement.value : '').trim();
    const department = (departmentElement ? departmentElement.value : '').trim();

    if (firstName.length < 3 || firstName.length > 50) {
        spawnToast("Input not satisfied", "First name must be between 3 and 50 characters long");
        return false;
    }
    if (lastName.length < 3 || lastName.length > 50) {
        spawnToast("Input not satisfied", "Last name must be between 3 and 50 characters long");
        return false;
    }
    if (!dob) {
        spawnToast("Input not satisfied", "Please enter a valid date of birth");
        return false;
    }
    if (!gender) {
        spawnToast("Input not satisfied", "Please select a gender");
        return false;
    }
    if (!/^\d{10,15}$/.test(phoneNumber)) {
        spawnToast("Input not satisfied", "Phone number must be between 10 and 15 digits long");
        return false;
    }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        spawnToast("Input not satisfied", "Please enter a valid email address");
        return false;
    }
    if (account.length < 5 || account.length > 50) {
        spawnToast("Input not satisfied", "Account must be between 5 and 50 characters long");
        return false;
    }
    if (password.length < 6 || password.length > 100) {
        spawnToast("Input not satisfied", "Password must be between 6 and 100 characters long");
        return false;
    }
    if (!department) {
        spawnToast("Input not satisfied", "Please select a department");
        return false;
    }
    return true;
}

document.getElementById('employee-add-form').addEventListener('submit', function (event) {
    event.preventDefault();
    if (validateForm()) {
        sendCreateRequest();
    }
});

function loadDepartment() {
    fetch('/api/user/department/getAll', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
        .then(response => response.json())
        .then(data => {
            const departmentSelect = document.getElementById('department');
            data.forEach(department => {
                const option = document.createElement('option');
                option.value = department.name;
                option.text = department.name;
                departmentSelect.appendChild(option);
            });
        });
}

function loadPermissions() {
    fetch('/api/user/permission/getAll', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
        .then(response => response.json())
        .then(data => {
            const permissionsContainer = document.getElementById('permissions-container');

            data.forEach(permission => {
                const checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = `permission-${permission.id}`;
                checkbox.name = 'permissions';
                checkbox.value = permission.name;
                checkbox.className = 'me-2';

                const label = document.createElement('label');
                label.htmlFor = `permission-${permission.id}`;
                label.appendChild(document.createTextNode(permission.name));
                label.className = 'me-4';

                const permissionDiv = document.createElement('div');
                permissionDiv.className = 'd-inline-block me-3 mb-2';
                permissionDiv.appendChild(checkbox);
                permissionDiv.appendChild(label);

                permissionsContainer.appendChild(permissionDiv);
            });
        });
}

loadDepartment();
loadPermissions();