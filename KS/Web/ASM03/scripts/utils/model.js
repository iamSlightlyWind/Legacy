class User {
    constructor(firstName, lastName, email, phone, description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }
}

class Content {
    constructor(title, briefDescription, fullContent) {
        this.title = title;
        this.briefDescription = briefDescription;
        this.fullContent = fullContent;
    }
}

class LoginModel {
    constructor(username, password) {
        this.username = username;
        this.password = password;
    }
}

class RegisterModel {
    constructor(username, email, password, confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}

class ProfileModel {
    constructor(firstName, lastName, phone, description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.description = description;
    }
}

class ContentModel {
    constructor(title, briefDescription, fullContent) {
        this.title = title;
        this.briefDescription = briefDescription;
        this.fullContent = fullContent;
    }
}

// Export the classes
export { User, Content, LoginModel, RegisterModel, ProfileModel, ContentModel };