

CALL reimbursement_system_db.procedure_setup_proj1_db;

CALL reimbursement_system_db.procedure_tear_down_project1_db;

CALL reimbursement_system_db.procedure_populate_proj1_db;


SELECT * FROM reimbursement_system_db.emp_mgr em  order by emp_id ASC ;
SELECT * FROM reimbursement_system_db.reimbursement_requests rr ;

DELETE FROM reimbursement_system_db.emp_mgr where emp_id > 3; -- to delete the employees

DELETE FROM reimbursement_system_db.emp_mgr;


-- UPDATE emp_mgr SET mgr_id = NULL WHERE firstName LIKE 'test%';

DELETE FROM reimbursement_system_db.emp_mgr  WHERE first_name LIKE 'test%';

DELETE FROM reimbursement_system_db.reimbursement_requests  WHERE reimbursement_request LIKE 'test%';

















