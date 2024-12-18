function login() {
    window.localStorage.removeItem('email');

    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        processLogin(JSON.parse(this.responseText));
    }
    xhttp.open('GET', '../assets/data/account.json', true);
    xhttp.send();
}

function processLogin(accounts) {
    let email = $('#email').val();
    let password = $('#password').val();
    const result = accounts.find(e => e.email === email && e.password === password);
    if (result) {
        window.localStorage.setItem('email', result.email);
        window.location.href = "./home.html";
    } else {
        window.alert('Wrong username or password');
    }
}