

CREATE OR REPLACE PROCEDURE reimbursement_system_db.procedure_tear_down_hibernate_project1_db ()

BEGIN
	
	DROP TABLE reimbursement_system_db.reimbursement_request;
	DROP TABLE reimbursement_system_db.employee ;
	DROP TABLE reimbursement_system_db.manager;

END;