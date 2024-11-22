import { User, Content, LoginModel, RegisterModel, ProfileModel, ContentModel } from '/scripts/utils/model.js'

function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const loginModel = new LoginModel(username, password);

    console.log(loginModel);
}

document.addEventListener('DOMContentLoaded', () => {
    const loginButton = document.getElementById('loginButton');
    loginButton.addEventListener('click', login);
});