class User {
    constructor(firstName, lastName, email, phone, desc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.desc = desc;
    }
}

class Content {
    constructor(title, brief, content) {
        this.title = title;
        this.brief = brief;
        this.content = content;
    }
}

class LoginModel {
    constructor(email, password) {
        this.email = email;
        this.password = password;
    }
}

class RegisterModel {
    constructor(username, email, password, repassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }
}

class ProfileModel {
    constructor(firstName, lastName, phone, desc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.desc = desc;
    }
}

class ContentModel {
    constructor(title, brief, content) {
        this.title = title;
        this.brief = brief;
        this.content = content;
    }
}

module.exports = { User, Content, LoginModel, RegisterModel, ProfileModel, ContentModel };