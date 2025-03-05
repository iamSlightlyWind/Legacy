-- mssql
create table role (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50)
)

create table users (
    id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(50),
    password VARCHAR(50),
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES role(id)
)

-- user detail
create table user_detail (
    id INT PRIMARY KEY IDENTITY(1,1),
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    dob DATE,
    phone VARCHAR(15),
    addr VARCHAR(255),
    pic VARBINARY(MAX),
    email VARCHAR(255) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
)

-- programs
create table programs (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100) NOT NULL,
    detail TEXT
)

-- application detail
create table application_detail (
    id INT PRIMARY KEY IDENTITY(1,1),
    student_id INT NOT NULL,
    program_id INT NOT NULL,
    english_cert VARCHAR(255),
    id_card VARCHAR(255),
    transcript VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (program_id) REFERENCES programs(id)
)

-- venue
create table venue (
    id INT PRIMARY KEY IDENTITY(1,1),
    location VARCHAR(100) NOT NULL,
    time_starts DATETIME NOT NULL,
    time_ends DATETIME NOT NULL,
    max_candidates INT NOT NULL,
    type VARCHAR(50) NOT NULL
)

-- venue assignment
create table venue_assignment (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT NOT NULL,
    venue_id INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (venue_id) REFERENCES venue(id)
)

-- payment
create table payment (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATETIME NOT NULL,
    payment_type VARCHAR(50) NOT NULL,
    application_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (application_id) REFERENCES application_detail(id)
)