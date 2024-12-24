let currentPage = 1;
let totalPages = 1;

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

function renderEmployeeData(data) {
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
}

function loadEmployeeData(page) {
    if (page < 1 || page > totalPages) return;

    const input = document.getElementById('searchInput').value;
    const filter = document.getElementById('filterBy').value;

    fetch('/api/user/employee/get', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ page: page, input: input, filter: filter })
    })
        .then(response => response.json())
        .then(data => {
            renderEmployeeData(data.employees);
            currentPage = page;
            totalPages = data.maxPage;
            document.getElementById('paginationNav').style.display = totalPages > 1 ? 'block' : 'none';
            updatePaginationControls();
        })
        .catch(error => console.error('Error fetching employee data:', error));
}

function sendSearchRequest(page) {
    loadEmployeeData(page);
}

loadEmployeeData(1);