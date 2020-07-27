

-- set up procedure

CREATE OR REPLACE PROCEDURE proj_0_db.procedure_setup_proj_0_db ()

BEGIN
	
	create table customer (
	customer_id 	int 	unsigned 	auto_increment,
	username  		varchar(55)			not null,
	password		varchar(55)			not null,	
	primary key  (customer_id)
	
	);
	
	
	create table account(
	account_id		int 	unsigned 	not null unique auto_increment ,
	customer_id		int 	unsigned 	not null,
	account_name 	varchar(55),
	balance			int,
	constraint fk_cust_id foreign key (customer_id) references customer(customer_id) on delete restrict on update cascade  
	);
	
	alter table account auto_increment = 100; 

END;