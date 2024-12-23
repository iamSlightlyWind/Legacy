let currentPage = 1;
let totalPages = 1;

function loadEmployeeData(page) {
    if (page < 1 || page > totalPages) return;

    fetch('/api/employee/get', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ page: page })
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
            currentPage = page;
            updatePaginationControls();
        })
        .catch(error => console.error('Error fetching employee data:', error));
}

function loadMaxPage() {
    fetch('/api/employee/maxPage', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
        .then(response => response.json())
        .then(data => {
            totalPages = data;
            console.log('Total pages:', totalPages);
            document.getElementById('paginationNav').style.display = totalPages > 1 ? 'block' : 'none';
            updatePaginationControls();
            loadEmployeeData(1);
        })
        .catch(error => console.error('Error fetching max page:', error));
}

function updatePaginationControls() {
    const paginationControls = document.getElementById('paginationControls');
    paginationControls.innerHTML = `
            <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
                <a class="page-link" href="#" onclick="loadEmployeeData(currentPage - 1)">Previous</a>
            </li>
        `;
    for (let i = 1; i <= totalPages; i++) {
        paginationControls.innerHTML += `
                <li class="page-item ${i === currentPage ? 'active' : ''}">
                    <a class="page-link" href="#" onclick="loadEmployeeData(${i})">${i}</a>
                </li>
            `;
    }
    paginationControls.innerHTML += `
            <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
                <a class="page-link" href="#" onclick="loadEmployeeData(currentPage + 1)">Next</a>
            </li>
        `;
}

$(document).ready(function () {
    loadMaxPage();
});