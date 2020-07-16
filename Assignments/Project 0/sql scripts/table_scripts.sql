


create table customer (

customer_id 	int 	unsigned 	not null unique auto_increment,
username  		varchar(55)			not null,
password		varchar(55)			not null,

primary key  (customer_id)

);


create table Account(

account_id		int 	unsigned 	not null unique auto_increment ,
customer_id		int 	unsigned 	not null,

account_name 	varchar(55),
balance			int,

constraint fk_cust_id foreign key (customer_id) references customer(customer_id) on delete restrict on update cascade

);


alter table Account auto_increment = 100; 