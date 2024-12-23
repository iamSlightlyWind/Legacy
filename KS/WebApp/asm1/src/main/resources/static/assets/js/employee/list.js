function loadEmployeeData() {
    fetch('/api/employee/getAll', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('employeeTableBody');
            tableBody.innerHTML = '';
            data.forEach(employee => {
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${employee.id}</td>
                <td>${employee.firstName} ${employee.lastName}</td>
                <td>${employee.dateOfBirth}</td>
                <td>${employee.address}</td>
                <td>${employee.phoneNumber}</td>
                <td>${employee.department.name}</td>
                <td><a href="/employee/view/${employee.id}" class="btn btn-link">View</a></td>
            `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching employee data:', error));
}

$(document).ready(function () {
    loadEmployeeData();
});