// AJAX Plain JS
function loadViewContent() {
    document.getElementById('content').innerHTML = 'loading';
    // let count = 5;
    // const timer = setInterval(() => {
    //     document.getElementById('content').innerHTML = count;
    //     count--;
    // }, 1000);
    // if (timer == 1) {
    //     clearInterval(timer);
    // }
    setTimeout(() => {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            document.getElementById('content').innerHTML = this.responseText;
        }
        xhttp.open('GET', 'view-content.html', true);
        xhttp.send();
    }, 1000)

}

function loadFormContent() {
    $('#content').html('loading');
    setTimeout(() => {
        $('#content').load('form-content.html');
    }, 1000);
}

function loadUserProfile() {
    $('#content').html('loading');
    setTimeout(() => {
        $('#content').load('edit-profile.html');
    }, 1);
}

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

function loadContentData() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        fillDataTable(JSON.parse(this.responseText));
    }
    xhttp.open('GET', '../assets/data/content.json', true);
    xhttp.send();
}

function fillDataTable(jsonData) {
    let element = document.getElementById('table-body');
    for (let i = 0; i < jsonData.length; i++) {
        const data = jsonData[i];
        let tableRow = document.createElement('tr');
        let cellId = document.createElement('td');
        let cellTitle = document.createElement('td');
        let cellBrief = document.createElement('td');
        let cellCreatedDate = document.createElement('td');
        cellId.innerHTML = data.id;
        cellTitle.innerHTML = data.title;
        cellBrief.innerHTML = data.brief;
        cellCreatedDate.innerHTML = data.createdDate;
        tableRow.append(cellId);
        tableRow.append(cellTitle);
        tableRow.append(cellBrief);
        tableRow.append(cellCreatedDate);
        element.appendChild(tableRow);
    }

}

function logout() {
    window.localStorage.removeItem('email')
    window.location.href = "./login.html";
}