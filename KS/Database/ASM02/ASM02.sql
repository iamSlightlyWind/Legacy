use ASM02;

/*
USE master;
IF EXISTS (SELECT name from sys.databases WHERE (name = 'ASM02'))
begin
    ALTER DATABASE [ASM02] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    drop database ASM02;
end
create database ASM02;
*/

-- Q1.

/* Suppliers: 
• SupplierID: primary key (an auto-generated number), data type is integer. 
• SupplierName: should be a string in any language, cannot be null 
• PhoneNumber: phone number of supplier. 
• Address: address of supplier. 
• Note: some note about supplier, free text.
a. Create Suppliers table with the most appropriate/economic field/column constraints & types. All fields  are mandatory except Note field. */

create table Suppliers (
    SupplierID int identity(1,1),
    SupplierName nvarchar(255) not null,
    PhoneNumber varchar(31) not null,
    Address nvarchar(255) not null,
    Note nvarchar(max),

    primary key (SupplierID)
);

/* Categories: 
• CategoryID: primary key (an auto-generated number), data type is integer. 
• CategoryName: should be a string in any language, cannot be null. 
• ParentCategoryID: foreign key, reference to CategoryID of Categories table, data type is integer.
• Note: some note about supplier, free text.
b. Create Categories table with the most appropriate/economic field/column constraints & types. All  fields are mandatory except Note field. */
create table Categories (
    CategoryID int identity(1,1),
    CategoryName nvarchar(255) not null,
    ParentCategoryID int,
    Note nvarchar(max),

    primary key (CategoryID),
    foreign key (ParentCategoryID) references Categories(CategoryID)
);

/* Products: 
• ProductID: primary key (an auto-generated number), data type is integer 
• ProductName: should be a string in any language, cannot be null 
• Summary: should be a string in any language 
• Price: data type is money (VND). 
• SupplierID: data type is integer. 
• CategoryID: foreign key, reference to CategoryID of Categories table, data type is integer.
• ManufactureDate: manufacture date of product. not null 
• ExpiredDate: expired date of product (not less than inserted time). 
• IsDiscontinued: status of the product (0: no longer for sale, 1: still on sale)
• Note: some note about product, free text.
c. Create Products table with the most appropriate/economic field/column constraints & types, all fields  are mandatory except Note field. */
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

/* Customers has attributes: 
• CustomerID: primary key (an auto-generated number), data type is integer 
• Email: email address of customer, must be unique and cannot be null 
• PhoneNumber: phone number of customer, must be unique 
• Password: password of customer, cannot be null 
• FirstName: should be a string in any language, cannot be null 
• LastName: should be a string in any language, cannot be null 
• Address: address of customer, can be null 
• Level: stores level of customer (accepts value range from 1 to 3 only). 
• IsActive: bit type, default is true.
d. Create Customers table with the most appropriate/economic field/column constraints & types */
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

/* Orders has attributes: 
• OrderID: primary key(an auto-generated number), data type is integer 
• OrderDate: cannot be null, data stored as date and time 
• ShipAddress: should be a string in any language.
e. Create Orders table with the most appropriate/economic field/column constraints & types */
create table Orders(
    OrderID int identity(1,1),
    OrderDate datetime not null,
    ShipAddress nvarchar(255) not null,

    primary key (OrderID)
);

/* OrderDetails has attributes: 
• OrderID: foreign key, reference to OrderID of Orders table 
• ProductID: foreign key, reference to ProductID of Products table 
• Quantity: cannot be null, positive value only  
• UnitPrice: money type, cannot be null 
• DiscountPercent: cannot be null, value in range from 0.00 to 0.70 
f. Create OrderDetails table with the most appropriate/economic field/column constraints & types. */
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

-- email nvarchar -- аpple.com == xn--pple-43d.com, browser translation using punycode as a security measure in 2010
-- phone 31 -- https://stackoverflow.com/questions/723587/whats-the-longest-possible-worldwide-phone-number-i-should-consider-in-sql-varc
-- assume that auto-generated numbers are generated using identity (unclear requirement)
-- use nvarchar(max) instead of text since it will use blob if needed -- https://stackoverflow.com/questions/834788/using-varcharmax-vs-text-on-sql-server




-- Q2.

-- a. Add an ProductCode field to Product table and make sure that the database will not allow the value for ProductCode to be inserted into a new row if that value has already been used in another row. 

alter table Products
add ProductCode nvarchar(255) not null unique;

-- b. Modify Product table to set default values to 0 for IsDiscontinued field. 

alter table Products
add constraint DF_Products_IsDiscontinued default 0 for IsDiscontinued;

-- c. Remove Notes field from Supplier table.

alter table Suppliers
drop column Note




-- Q3:

-- a. A product belongs to a supplier. A supplier can produce many products. Add the FOREIGN KEY constraint of SupplierID field to the Product table that will relate the Product table.

alter table Products
add constraint FK_Products_Suppliers
foreign key (SupplierID) references Suppliers(SupplierID);

-- b. An order always belongs to a certain customer. A customer can purchase multiple orders. Let's  establish a relationship between the customer and the order (FOREIGN KEY constraint)

alter table Orders
add CustomerID int not null references Customers(CustomerID);




-- Q4:

-- a. Add at least 5 records into each the created tables.

/* insert into Suppliers values
('Apple', '123456789', 'Cupertino, CA'),
('Samsung', '987654321', 'Seoul, South Korea'),
('Google', '123123123', 'Mountain View, CA'),
('Microsoft', '456456456', 'Redmond, WA'),
('Amazon', '789789789', 'Seattle, WA');

insert into Categories values
('Smartphones', null, 'Phones that are smart'),
('Laptops', null, 'Portable computers'),
('Tablets', null, 'Big phones'),
('Smartwatches', null, 'Watches that are smart'),
('SmartHome', null, 'Homes that are smart'),
('Accessories', null, 'Things that help the smart things'),
('Charger', 6, 'Things that charge the smart things'),
('Case', 6, 'Things that protect the smart things');

insert into Products values
('Apple Watch', 'New enough', 45000, 1, 4, '2021-01-01', '2022-01-01', 0, 'been overstocked', 'AWS9'),
('Z Fold 5', 'Foldy', 90000, 2, 1, '2021-01-01', '2022-01-01', 0, 'were gonna need more of this, restock once sold out', 'ZF5'),
('Pixel Slate', 'Slatey', 60000, 3, 3, '2021-01-01', '2022-01-01', 1, 'not selling well', 'PSL8'),
('Surface Pro 8', 'Pro', 75000, 4, 2, '2021-01-01', '2022-01-01', 0, '', 'SP8'),
('Echo Dot', 'Dot', 35000, 5, 5, '2021-01-01', '2022-01-01', 1, '', 'ED3');

insert into Customers values
('john.doe@example.com', '123-456-7890', 'password123', 'John', 'Doe', '123 Elm St', 1, 1),
('jane.smith@example.com', '234-567-8901', 'password456', 'Jane', 'Smith', '456 Oak St', 2, 1),
('alice.jones@example.com', '345-678-9012', 'password789', 'Alice', 'Jones', '789 Pine St', 3, 1),
('bob.brown@example.com', '456-789-0123', 'password101', 'Bob', 'Brown', '101 Maple St', 1, 1),
('carol.white@example.com', '567-890-1234', 'password202', 'Carol', 'White', '202 Birch St', 2, 1);

insert into Orders values
('2021-01-01', '123 Elm St', '1'),
('2021-01-02', '456 Oak St', '2'),
('2021-01-03', '789 Pine St', '3'),
('2021-01-04', '101 Maple St', '4'),
('2021-01-05', '202 Birch St', '5');

insert into OrderDetails values
('1', '1', '1', '10000000', '0.05'),
('2', '2', '2', '50000000', '0.10'),
('3', '3', '3', '20000000', '0.15'),
('4', '4', '4', '30000000', '0.20'),
('5', '5', '5', '5000000', '0.25'); */

/* b. Create a VIEW called vw_Product_Tracking that will appear to the product as ProductID,  ProductName, ProductCode. It has Level satisfied the criteria:  
- Price >= 50000. 
- Product still on sale.  */

go
create view vw_Product_Tracking
as
select ProductID, ProductName, ProductCode
from Products
where Price >= 50000 and IsDiscontinued = 0;
go