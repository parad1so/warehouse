create table Product(
                        id serial primary key,

                        last_purchase_price int,
                        last_sale_price int,
                        product_name varchar(50),
                        vendor_code varchar(50)
);
create table Number_of_warehouse(
                                    id serial primary key,
                                    warehouse_name varchar(100)
);

create table warehouse(
                          id serial primary key,
                          warehouse_id int,
                          product_id int,
                          foreign key (product_id) references Product(id),
                          foreign key (warehouse_id) references Number_of_warehouse(id),
                          quantity int
);
create table purchase_price(
                               id serial primary key,
                               price int
);
create table Selling_price(
                              id serial primary key,
                              price int
);
create table list_of_product(
                                id serial primary key,
                                product_id int,
                                foreign key(product_id) references Product(id),
                                quantity int,
                                purchase_price_id int,
                                foreign key (purchase_price_id) references purchase_price(id),
                                selling_price_id int,
                                foreign key (selling_price_id) references Selling_price(id)

);
create table number_of_product(
                                  id serial primary key,
                                  number int
);
create table sale_of_product(
                                id serial primary key,
                                number_id int,
                                foreign key (number_id) references number_of_product(id),
                                warehouse_id int,
                                foreign key (warehouse_id) references warehouse(id),
                                list_of_product_id int,
                                foreign key (list_of_product_id) references list_of_product(id)
);
create table arrival_of_product(
                                   id serial primary key,
                                   number_id int,
                                   foreign key (number_id) references number_of_product(id),
                                   warehouse_id int,
                                   foreign key (warehouse_id) references warehouse(id),
                                   list_of_product_id int,
                                   foreign key (list_of_product_id) references list_of_product(id)
);
create table moving_of_product(
                                  id serial primary key,
                                  number_id int,
                                  foreign key (number_id) references number_of_product(id),
                                  warehouseA_id int,
                                  foreign key (warehouseA_id) references warehouse(id),
                                  warehouseB_id int,
                                  foreign key (warehouseB_id) references warehouse(id),
                                  list_of_product_id int,
                                  foreign key (list_of_product_id) references list_of_product(id)
);

create table user_account(
    id serial primary key ,
    name varchar(50),
    password varchar(100)
);

insert into Product values (1, 700, 1100, 'samsung galaxy s20', 's20');
insert into Product values (2, 500, 800,  'samsung galaxy s10', 's10');
insert into Product values (3, 'MQAD2RU/A', 'IPhone 10', 500, 999);
insert into Number_of_warehouse values (1, 'Склад1');
insert into Number_of_warehouse values (2, 'Склад2');
insert into warehouse values (1,1,1,50);
insert into warehouse values (2,1,2,30);
insert into warehouse values (3,2,3,20);
insert into Selling_price (price)
values (700);
insert into Selling_price (price)
values (1000);
insert into Selling_price (price)
values (500);

insert into purchase_price (price)
values (300);
insert into purchase_price (price)
values (500);
insert into purchase_price (price)
values (800);

insert into list_of_product(product_id, quantity, purchase_price_id, selling_price_id) values (1,50,2,2);
insert into list_of_product(product_id, quantity, purchase_price_id, selling_price_id) VALUES (3,100,2,2);
insert into list_of_product(product_id, quantity, purchase_price_id, selling_price_id) VALUES (2,400,2,2);
insert into number_of_product(number) values (1231);
insert into number_of_product(number) values (1232);

insert into sale_of_product(number_id, warehouse_id, list_of_product_id) VALUES (1,1,1);
insert into sale_of_product(number_id, warehouse_id, list_of_product_id) VALUES (2,2,2);
insert into arrival_of_product(number_id, warehouse_id, list_of_product_id) VALUES (1,1,1);
insert into arrival_of_product(number_id, warehouse_id, list_of_product_id) VALUES (2,2,2);
insert into arrival_of_product(number_id, warehouse_id, list_of_product_id) VALUES (2,3,3);
insert into moving_of_product(number_id, warehouseA_id, warehouseB_id, list_of_product_id) VALUES (1,1,2,1);


/*Тест*/ 
select product_name as product_id, quantity, purchase_price_id, purchase_price_id, selling_price_id from list_of_product inner join Product P on list_of_product.product_id = P.id;
SELECT warehouse.id, product_name, product_id, quantity FROM warehouse inner join Product on warehouse.product_id = Product.id and last_purchase_price>500;

CREATE VIEW arrival_list_product AS
SELECT arrival_of_product.number_id, arrival_of_product.warehouse_id, arrival_of_product.list_of_product_id, product.product_name , list_of_product.product_id, list_of_product.quantity, purchase_price.price
from arrival_of_product left join list_of_product  on arrival_of_product.list_of_product_id = list_of_product.id
left join product on list_of_product.product_id = Product.id
left join purchase_price on list_of_product.purchase_price_id = purchase_price.id;


CREATE VIEW sale_list_product AS
SELECT sale_of_product.number_id, sale_of_product.warehouse_id, sale_of_product.list_of_product_id, product.product_name , list_of_product.product_id, list_of_product.quantity, selling_price.price
from sale_of_product inner join list_of_product  on sale_of_product.id = list_of_product.id
                        inner join product on list_of_product.product_id = Product.id
                        inner join Selling_price on list_of_product.purchase_price_id = Selling_price.id;

CREATE view moving_list_product AS
SELECT moving_of_product.number_id, moving_of_product.warehousea_id, moving_of_product.warehouseb_id, moving_of_product.list_of_product_id, product.product_name , list_of_product.product_id, list_of_product.quantity
from moving_of_product inner join list_of_product  on moving_of_product.id = list_of_product.id
                     inner join product on list_of_product.product_id = Product.id;

CREATE view general_list_of_product AS
    SELECT product.vendor_code, product.product_name , purchase_price.price as purchase_price, selling_price.price as selling_price
FROM list_of_product join Product on list_of_product.product_id = Product.id
join purchase_price on list_of_product.purchase_price_id = purchase_price.id
join selling_price on list_of_product.selling_price_id = selling_price.id

CREATE VIEW stock_balances as
    SELECT  product.vendor_code, product.product_name, Number_of_warehouse.warehouse_name
from warehouse join Product on warehouse.product_id = Product.id
    join Number_of_warehouse on warehouse.warehouse_id = Number_of_warehouse.id;

