function profileInit() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        getProfile(JSON.parse(this.responseText));
    }
    xhttp.open('GET', '../assets/data/profile.json', true);
    xhttp.send();
}

function getProfile(profiles) {
    const email = window.localStorage.getItem('email');
    if (email == undefined) {
        window.location.href = "./login.html";
    }

    const profile = profiles.find(e => e.email === email);
    if (profile) {
        document.getElementById('profile-firstName').value = profile.firstName;
        document.getElementById('profile-lastName').value = profile.lastName;
        document.getElementById('profile-email').value = profile.email;
        document.getElementById('profile-phone').value = profile.phone;
        document.getElementById('profile-description').textContent = profile.description;
    }
}

function writeProfile() {
    const email = window.localStorage.getItem('email');
    const firstName = document.getElementById('profile-firstName').value;
    const lastName = document.getElementById('profile-lastName').value;
    const phone = document.getElementById('profile-phone').value;
    const description = document.getElementById('profile-description').value;

    const profile = {
        email: email,
        firstName: firstName,
        lastName: lastName,
        phone: phone,
        description: description
    }

    const xhttp = new XMLHttpRequest();
    xhttp.open('POST', '../assets/data/profile.json', true);
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhttp.send(JSON.stringify(profile));
}