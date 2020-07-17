

CREATE OR REPLACE PROCEDURE proj_0_db.procedure_populate_tables ()

BEGIN 
	
	insert into customer (username , password) values 
	("mike_d", 444), 
	("john",  987) ,
	("rex", 456), 
	( "drake", 777), 
	("karen", 898);


	insert into account (customer_id, account_name, balance ) values
	( 1, "savings", 55),
	( 1, "checking", 190),
	( 2, "checking", 788),
	( 2, "savings", 10000),
	( 3, "money market", 40000),
	( 4, "investments", 78 ),
	( 5, "rubble", 898),
	( 5, "mortgage", 1000);
	
END;