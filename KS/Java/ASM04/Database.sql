use master
go

IF EXISTS (SELECT 1
FROM sys.databases
WHERE name = 'SMS')
BEGIN
    ALTER DATABASE SMS SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    DROP DATABASE SMS
END
go

create database SMS
go

use SMS
go

create table Customer (
    customer_id int identity(1,1),
    customer_name nvarchar(255) not null,

    primary key (customer_id)
);

create table Employee (
    employee_id int identity(1,1),
    employee_name nvarchar(255) not null,
    salary float not null,
    supervisor_id int,

    primary key (employee_id),
    foreign key (supervisor_id) references Employee(employee_id)
);

create table Product (
    product_id int identity(1,1),
    product_name nvarchar(255) not null,
    list_price float not null,

    primary key (product_id)
);

create table Orders (
    order_id int identity(1,1),
    order_date date not null,
    customer_id int not null,
    employee_id int not null,
    total float not null,

    primary key (order_id),
    foreign key (customer_id) references Customer(customer_id),
    foreign key (employee_id) references Employee(employee_id)
);

--List all lineitems for  an order, returns a list with all line items for  a given order id

create table LineItem (
    order_id int not null,
    product_id int not null,
    quantity int not null,
    price float not null,

    primary key (order_id, product_id),
    foreign key (order_id) references Orders(order_id),
    foreign key (product_id) references Product(product_id)
)
go

create or alter FUNCTION computeOrderTotal (@orderID int)
RETURNS float
AS
BEGIN
    DECLARE @total float
    SELECT @total = SUM(quantity * price)
    FROM LineItem
    WHERE order_id = @orderID
    RETURN @total
END
go

create or alter PROCEDURE addCustomer
@customer_name nvarchar(255),
@result bit OUTPUT
AS
BEGIN
    DECLARE @customer_id int
    INSERT INTO Customer (customer_name)
    VALUES (@customer_name)
    SELECT @customer_id = SCOPE_IDENTITY()
    IF @customer_id IS NOT NULL
        SET @result = 1
    ELSE
        SET @result = 0
END
go

create or alter PROCEDURE deleteCustomer
@customer_id int,
@result bit output
AS
BEGIN
    if not EXISTS (select 1 from Customer where customer_id = @customer_id)
    BEGIN
        set @result = 0
        return
    END
    
    declare @orderID int
    declare cursor_orderID cursor for
    select order_id from orders where customer_id = @customer_id

    open cursor_orderID
    fetch next from cursor_orderID into @orderID;
    print 'Order ID: ' + CAST(@orderID AS VARCHAR)
    WHILE (@@FETCH_STATUS = 0)
        BEGIN
            PRINT 'Processing order_id: ' + CAST(@orderID AS VARCHAR)
            /* delete from LineItem where order_id = @orderID */
            fetch next from cursor_orderID into @orderID
        END
    CLOSE cursor_orderID
    DEALLOCATE cursor_orderID

    /* delete from Orders where customer_id = @customer_id
    delete from Customer where customer_id = @customer_id */
    set @result = 1
END
go

/* 
DECLARE @result BIT
EXEC deleteCustomer @customer_id = 1, @result = @result OUTPUT
PRINT 'Result: ' + CAST(@result AS VARCHAR)
*/

-- Insert data into Customer table
INSERT INTO Customer (customer_name) VALUES ('John Doe');
INSERT INTO Customer (customer_name) VALUES ('Jane Smith');
INSERT INTO Customer (customer_name) VALUES ('Alice Johnson');

-- Insert data into Employee table
INSERT INTO Employee (employee_name, salary, supervisor_id) VALUES ('Michael Brown', 60000, NULL);
INSERT INTO Employee (employee_name, salary, supervisor_id) VALUES ('Emily Davis', 55000, 1);
INSERT INTO Employee (employee_name, salary, supervisor_id) VALUES ('David Wilson', 50000, 1);

-- Insert data into Product table
INSERT INTO Product (product_name, list_price) VALUES ('Laptop', 1200.00);
INSERT INTO Product (product_name, list_price) VALUES ('Smartphone', 800.00);
INSERT INTO Product (product_name, list_price) VALUES ('Tablet', 400.00);

-- Insert data into Orders table
INSERT INTO Orders (order_date, customer_id, employee_id, total) VALUES ('2023-01-15', 1, 1, 2000.00);
INSERT INTO Orders (order_date, customer_id, employee_id, total) VALUES ('2023-02-20', 2, 2, 1500.00);
INSERT INTO Orders (order_date, customer_id, employee_id, total) VALUES ('2023-03-10', 3, 3, 1000.00);

-- Insert data into LineItem table
INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (1, 1, 1, 1200.00);
INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (1, 2, 1, 800.00);
INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (2, 2, 1, 800.00);
INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (2, 3, 2, 400.00);
INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (3, 3, 2, 400.00);
INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (3, 1, 1, 1200.00);

/*
delete from LineItem where order_id = 1

select * from Customer
select * from Orders
select * from LineItem
*/