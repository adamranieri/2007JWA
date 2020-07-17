

-- mariadb method of calling procedures
CREATE OR REPLACE PROCEDURE procedure_tear_down_tables () 
BEGIN 
	DROP TABLE IF EXISTS proj_0_db.account;
	DROP TABLE IF EXISTS proj_0_db.customer;
END;

