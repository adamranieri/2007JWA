


CREATE OR REPLACE PROCEDURE reimbursement_system_db.procedure_tear_down_project1_db ()

BEGIN
	
	DROP TABLE reimbursement_system_db.reimbursement_requests;
	DROP TABLE reimbursement_system_db.emp_mgr ;

END;