

CREATE OR REPLACE PROCEDURE reimbursement_system_db.procedure_setup_proj1_hibernate_db ()

BEGIN
	
	create table manager (
	mgr_id 			int 		unsigned	PRIMARY KEY		auto_increment,
	
	first_name		varchar(55)			not null,
	last_name		varchar(55)			not null,
	username  		varchar(55)			not null,
	password		varchar(55)			not null
	
	);

	create table employee (
	emp_id 			int 		unsigned	PRIMARY KEY		auto_increment,
	
	first_name		varchar(55)			not null,
	last_name		varchar(55)			not null,
	username  		varchar(55)			not null,
	password		varchar(55)			not null,
	mgr_id			int			unsigned		not null,
	
	constraint fk_emp_mgr foreign key (mgr_id) references manager (mgr_id) ON delete restrict on update cascade 
	
	);
	
	create table reimbursement_request(
	rr_id					int			unsigned	primary key		AUTO_INCREMENT,
	emp_id 					int 		unsigned	not null,
	mgr_id					int 		unsigned	not null,
	reimbursement_request 	varchar(55) not null,
	reimbursement_status	varchar(55)	not null,
	reason					varchar(55),	
	
	constraint rr_emp foreign key (emp_id) references employee (emp_id) ON delete restrict on update cascade, 
	-- the below line should never reference the mgrId  because it is a foreign key
	--  so always reference the primary key.
	constraint rr_mgr foreign key (mgr_id) references manager (mgr_id) ON delete restrict on update cascade 
	
	);
	

END;