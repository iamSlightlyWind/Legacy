use ASM3;

-- a. Add at least 8 records into each created table, both normal and abnormal data: (Included in ASM2_with_insert.sql)
/* insert into Suppliers values
('Apple', '123456789', 'Cupertino, CA'),
('Samsung', '987654321', 'Seoul, South Korea'),
('Google', '123123123', 'Mountain View, CA'),
('Microsoft', '456456456', 'Redmond, WA'),
('Amazon', '789789789', 'Seattle, WA'),
('Dell', '654654654', 'Round Rock, TX'),
('HP', '321321321', 'Palo Alto, CA'),
('Lenovo', '654987321', 'Beijing, China');

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
('Apple Watch', 'New enough', 45000, 1, 4, '2021-01-01', '2025-01-01', 0, 'been overstocked', 'AWS9'),
('Z Fold 5', 'Foldy', 90000, 2, 1, '2021-01-01', '2026-01-01', 0, 'were gonna need more of this, restock once sold out', 'ZF5'),
('Pixel Slate', 'Slatey', 60000, 3, 3, '2021-01-01', '2027-01-01', 0, 'not selling well', 'PSL8'),
('Surface Pro 8', 'Pro', 75000, 4, 2, '2021-01-01', '2028-01-01', 0, '', 'SP8'),
('Echo Dot', 'Dot', 35000, 5, 5, '2021-01-01', '2029-01-01', 0, '', 'ED3'),
('iPhone 6', '6', 20000, 1, 1, '2021-01-01', '2022-01-01', 0, 'old', 'IP6'),
('Galaxy S5', 'S5', 30000, 2, 1, '2021-01-01', '2022-01-01', 0, 'old', 'GS5'),
('Pixel 2', '2', 40000, 3, 1, '2021-01-01', '2022-01-01', 0, 'old', 'PXL2');

insert into Customers values
('john.nguyen@example.com', '123-456-7890', 'password123', 'John', 'Doe', '123 Elm St', 3, 1),
('nguyenasd.smith@example.com', '234-567-8901', 'password456', 'Jane', 'Smith', '456 Oak St', 2, 1),
('alice.jones@example.com', '345-678-9012', 'password789', 'Alice', 'Jones', '789 Pine St', 3, 1),
('bob.brown@example.com', '456-789-0123', 'password101', 'Bob', 'Brown', '101 Maple St', 1, 1),
('carol.wnguyenasdte@example.com', '567-890-1234', 'password202', 'Carol', 'White', '202 Birch St', 3, 1),
('dr.who@example.com', '678-901-2345', 'password303', 'Dr', 'Who', '303 Cedar St', 3, 1),
('dave.diver@example.com', '789-012-3456', 'password404', 'Dave', 'Diver', '404 Walnut St', 1, 1),
('rockd.snguyenasdt@example.com', '890-123-4567', 'password505', 'Rock', 'Start', '505 Pineapple St', 3, 1);

insert into Orders values
('2021-01-01', '123 Elm St', '1'),
('2021-01-02', '456 Oak St', '2'),
('2021-01-03', '789 Pine St', '6'),
('2021-01-04', '101 Maple St', '4'),
('2021-01-05', '202 Birch St', '5'),
('2021-01-06', '303 Cedar St', '6'),
('2021-01-07', '404 Walnut St', '1'),
('2021-01-08', '505 Pineapple St', '2');

insert into OrderDetails values
('1', '1', '1', '45000', '0.05'),
('2', '2', '2', '90000', '0.10'),
('3', '3', '3', '60000', '0.15'),
('4', '4', '4', '75000', '0.20'),
('5', '5', '5', '35000', '0.25'),
('6', '6', '6', '20000', '0.30'),
('7', '7', '7', '30000', '0.35'),
('8', '8', '8', '40000', '0.40'); */

/*
select * from Suppliers
select * from Categories
select * from Products
select * from Customers
select * from Orders
select * from OrderDetails
*/




-- b. Update the phone number of the supplier with ID is 5.
update Suppliers
set PhoneNumber = '123456789'
where supplierid = 5




-- c. Create order for customer with ID = 3. This customer buys
-- 4 products with ID = 5 and 3 products with ID = 2. Both products get 15% discount.
insert into Orders values
(getdate(), 'dorm H', '3');

declare @newid int;
select @newid = SCOPE_IDENTITY();

insert into orderdetails values
(@newID, 5, 4, 35000, 0.15),
(@newID, 3, 2, 90000, 0.15);




-- d. Delete an order with ID = 3 of customer with Id = 6.
if exists (select 1 from orders where orderid = 3 and CustomerID = 6)
begin
    delete from orderdetails where orderid = 3
    delete from orders where orderid = 3
end



-- e. Save all expired products to a new table named ExpiredProducts.
select * 
into ExpiredProducts
from Products
where ExpiredDate < getdate()




-- f. Delete all expried products.
update Products
set IsDiscontinued = 1
where ExpiredDate < getdate()



-- g. Find all customers with level 3 and their email contains “nguyen”.
select * from customers
where level = 3 and email like '%nguyen%'




-- h. List IDs of customers who have purchased. Data has no duplicate results.
select customers.CustomerID from customers
join orders on customers.CustomerID = orders.CustomerID
group by customers.CustomerID




-- i. Create a view showing CustomerID, FullName, Email, Level of all customers
go
create view vw_show_customers
as
select CustomerID, concat(FirstName, ' ', LastName) as FullName, email, level
from customers
go

select * from vw_show_customers