

CALL proj_0_db.procedure_tear_down_tables(); 

call proj_0_db.procedure_setup_proj_0_db(); 

call proj_0_db.procedure_populate_tables;


select * from proj_0_db.account ;

SELECT * FROM proj_0_db.customer c ;

DELETE FROM proj_0_db.customer WHERE customer_id > 5;


