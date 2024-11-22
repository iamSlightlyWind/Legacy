const loginRestriction = {
    email: {
        type: 'text',
        min: 5,
        max: 50
    },
    password: {
        type: 'text',
        min: 8,
        max: 30
    }
};

const registerRestriction = {
    username: {
        type: 'text',
        min: 3,
        max: 30
    },
    email: {
        type: 'text',
        min: 5,
        max: 50
    },
    password: {
        type: 'text',
        min: 8,
        max: 30
    },
    repassword: {
        type: 'text',
        min: 8,
        max: 30
    }
};

const profileRestriction = {
    firstName: {
        type: 'text',
        min: 3,
        max: 30
    },
    lastName: {
        type: 'text',
        min: 3,
        max: 30
    },
    phone: {
        type: 'text',
        min: 9,
        max: 13
    },
    desc: {
        type: 'any',
        min: 0,
        max: 200
    }
};

const contentRestriction = {
    title: {
        type: 'text',
        min: 10,
        max: 200
    },

    brief: {
        type: 'text',
        min: 30,
        max: 150
    },

    content: {
        type: 'text',
        min: 50,
        max: 1000
    }
};

module.exports = { loginRestriction, registerRestriction, profileRestriction, contentRestriction };