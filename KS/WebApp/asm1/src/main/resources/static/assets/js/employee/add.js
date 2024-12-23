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

    fetch('/api/employee/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(accountData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function loadDepartment() {
    fetch('/api/department/getAll', {
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
    fetch('/api/permission/getAll', {
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

$(document).ready(function () {
    loadDepartment();
    loadPermissions();
});