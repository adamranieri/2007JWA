


-- set up procedure

CREATE OR REPLACE PROCEDURE reimbursement_system_db.procedure_setup_proj1_db ()

BEGIN
	
	create table emp_mgr (
	emp_id 			int 		unsigned	PRIMARY KEY		auto_increment,
	
	first_name		varchar(55)			not null,
	last_name		varchar(55)			not null,
	username  		varchar(55)			not null,
	password		varchar(55)			not null,
	emp_role		varchar(55)			not null,
	mgr_id			int		unsigned, -- the manager of the employee
	
	constraint fk_emp_mgr foreign key (mgr_id) references emp_mgr (emp_id) ON delete restrict on update cascade 
	
	
	);
	
	
	create table reimbursement_requests(
	rr_id					int			unsigned	primary key		AUTO_INCREMENT,
	emp_id 					int 		unsigned	not null,
	mgr_id					int 		unsigned	not null,
	reimbursement_request 	varchar(55) not null,
	reimbursement_status	varchar(55)	not null,
	reason					varchar(55),	
	
	constraint rr_emp foreign key (emp_id) references emp_mgr (emp_id) ON delete restrict on update cascade, 
	-- the below line should never reference the mgrId  because it is a foreign key
	--  so always reference the primary key.
	constraint rr_mgr foreign key (mgr_id) references emp_mgr (emp_id) ON delete restrict on update cascade 
	
	);
	

END;