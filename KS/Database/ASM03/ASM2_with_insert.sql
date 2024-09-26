use ASM3;

/*
USE master;
IF EXISTS (SELECT name from sys.databases WHERE (name = 'ASM3'))
begin
    ALTER DATABASE [ASM3] SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    drop database ASM3;
    create database ASM3;
end
*/

create table Suppliers (
    SupplierID int identity(1,1),
    SupplierName nvarchar(255) not null,
    PhoneNumber varchar(31) not null,
    Address nvarchar(255) not null,
    Note nvarchar(max),

    primary key (SupplierID)
);

create table Categories (
    CategoryID int identity(1,1),
    CategoryName nvarchar(255) not null,
    ParentCategoryID int,
    Note nvarchar(max),

    primary key (CategoryID),
    foreign key (ParentCategoryID) references Categories(CategoryID)
);

create table Products (
    ProductID int identity(1,1),
    ProductName nvarchar(255) not null,
    Summary nvarchar(max) not null,
    Price money not null,
    SupplierID int not null,
    CategoryID int not null,
    ManufactureDate date not null,
    ExpiredDate date not null,
    IsDiscontinued bit not null,
    Note nvarchar(max),

    primary key (ProductID),
    foreign key (CategoryID) references Categories(CategoryID)/* ,
    foreign key (SupplierID) references Suppliers(SupplierID) */
);

alter table Products
add constraint CHK_Products_ExpiredDate check (ExpiredDate >= ManufactureDate);

create table Customers(
    CustomerID int identity(1,1),
    Email nvarchar(320) unique not null, 
    PhoneNumber varchar(31) unique not null,
    Password nvarchar(255) not null,
    FirstName nvarchar(255) not null,
    LastName nvarchar(255) not null,
    Address nvarchar(255),
    Level int check (Level >= 1 and Level <= 3),
    IsActive bit default 1,

    primary key (CustomerID)
);

create table Orders(
    OrderID int identity(1,1),
    OrderDate datetime not null,
    ShipAddress nvarchar(255) not null,

    primary key (OrderID)
);

create table OrderDetails(
    OrderID int,
    ProductID int,
    Quantity int not null check (Quantity > 0),
    UnitPrice money not null,
    DiscountPercent decimal(3, 2) not null check (DiscountPercent >= 0.00 and DiscountPercent <= 0.70),

    primary key (OrderID, ProductID),
    foreign key (OrderID) references Orders(OrderID),
    foreign key (ProductID) references Products(ProductID)
);

alter table Products
add ProductCode nvarchar(255) not null unique;

alter table Products
add constraint DF_Products_IsDiscontinued default 0 for IsDiscontinued;

alter table Suppliers
drop column Note

alter table Products
add constraint FK_Products_Suppliers
foreign key (SupplierID) references Suppliers(SupplierID);

alter table Orders
add CustomerID int not null references Customers(CustomerID);

go
create view vw_Product_Tracking
as
select ProductID, ProductName, ProductCode
from Products
where Price >= 50000 and IsDiscontinued = 0;
go

insert into Suppliers (SupplierName, PhoneNumber, Address) values
('Apple', '123456789', 'Cupertino, CA'),
('Samsung', '987654321', 'Seoul, South Korea'),
('Google', '123123123', 'Mountain View, CA'),
('Microsoft', '456456456', 'Redmond, WA'),
('Amazon', '789789789', 'Seattle, WA'),
('Dell', '654654654', 'Round Rock, TX'),
('HP', '321321321', 'Palo Alto, CA'),
('Lenovo', '654987321', 'Beijing, China');

insert into Categories (CategoryName, ParentCategoryID, Note) values
('Smartphones', null, 'Phones that are smart'),
('Laptops', null, 'Portable computers'),
('Tablets', null, 'Big phones'),
('Smartwatches', null, 'Watches that are smart'),
('SmartHome', null, 'Homes that are smart'),
('Accessories', null, 'Things that help the smart things'),
('Charger', 6, 'Things that charge the smart things'),
('Case', 6, 'Things that protect the smart things');

insert into Products (ProductName, Summary, Price, SupplierID, CategoryID, ManufactureDate, ExpiredDate, IsDiscontinued, Note, ProductCode) values
('Apple Watch', 'New enough', 45000, 1, 4, '2021-01-01', '2025-01-01', 0, 'been overstocked', 'AWS9'),
('Z Fold 5', 'Foldy', 90000, 2, 1, '2021-01-01', '2026-01-01', 0, 'were gonna need more of this, restock once sold out', 'ZF5'),
('Pixel Slate', 'Slatey', 60000, 3, 3, '2021-01-01', '2027-01-01', 0, 'not selling well', 'PSL8'),
('Surface Pro 8', 'Pro', 75000, 4, 2, '2021-01-01', '2028-01-01', 0, '', 'SP8'),
('Echo Dot', 'Dot', 35000, 5, 5, '2021-01-01', '2029-01-01', 0, '', 'ED3'),
('iPhone 6', '6', 20000, 1, 1, '2021-01-01', '2022-01-01', 0, 'old', 'IP6'),
('Galaxy S5', 'S5', 30000, 2, 1, '2021-01-01', '2022-01-01', 0, 'old', 'GS5'),
('Pixel 2', '2', 40000, 3, 1, '2021-01-01', '2022-01-01', 0, 'old', 'PXL2');

insert into Customers (Email, PhoneNumber, Password, FirstName, LastName, Address, Level, IsActive) values
('john.nguyen@example.com', '123-456-7890', 'password123', 'John', 'Doe', '123 Elm St', 3, 1),
('nguyenasd.smith@example.com', '234-567-8901', 'password456', 'Jane', 'Smith', '456 Oak St', 2, 1),
('alice.jones@example.com', '345-678-9012', 'password789', 'Alice', 'Jones', '789 Pine St', 3, 1),
('bob.brown@example.com', '456-789-0123', 'password101', 'Bob', 'Brown', '101 Maple St', 1, 1),
('carol.wnguyenasdte@example.com', '567-890-1234', 'password202', 'Carol', 'White', '202 Birch St', 3, 1),
('dr.who@example.com', '678-901-2345', 'password303', 'Dr', 'Who', '303 Cedar St', 3, 1),
('dave.diver@example.com', '789-012-3456', 'password404', 'Dave', 'Diver', '404 Walnut St', 1, 1),
('rockd.snguyenasdt@example.com', '890-123-4567', 'password505', 'Rock', 'Start', '505 Pineapple St', 3, 1);

insert into Orders (OrderDate, ShipAddress, CustomerID) values
('2021-01-01', '123 Elm St', '1'),
('2021-01-02', '456 Oak St', '2'),
('2021-01-03', '789 Pine St', '3'),
('2021-01-04', '101 Maple St', '4'),
('2021-01-05', '202 Birch St', '5'),
('2021-01-06', '303 Cedar St', '6'),
('2021-01-07', '404 Walnut St', '1'),
('2021-01-08', '505 Pineapple St', '2');

insert into OrderDetails (OrderID, ProductID, Quantity, UnitPrice, DiscountPercent) values
('1', '1', '1', '45000', '0.05'),
('2', '2', '2', '90000', '0.10'),
('3', '3', '3', '60000', '0.15'),
('4', '4', '4', '75000', '0.20'),
('5', '5', '5', '35000', '0.25'),
('6', '6', '6', '20000', '0.30'),
('7', '7', '7', '30000', '0.35'),
('8', '8', '8', '40000', '0.40');