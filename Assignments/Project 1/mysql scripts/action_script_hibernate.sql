
CALL reimbursement_system_db.procedure_setup_proj1_hibernate_db;

CALL reimbursement_system_db.procedure_populate_proj1_hibernate_db;

CALL reimbursement_system_db.procedure_tear_down_hibernate_project1_db;




SELECT * FROM reimbursement_system_db.manager;
SELECT * FROM reimbursement_system_db.employee;
SELECT * FROM reimbursement_system_db.reimbursement_request rr order by emp_id  ;

DELETE FROM reimbursement_system_db.emp_mgr where emp_id > 3; -- to delete the employees

DELETE FROM reimbursement_system_db.emp_mgr;



-- UPDATE emp_mgr SET mgr_id = NULL WHERE firstName LIKE 'test%';

DELETE FROM reimbursement_system_db.employee  WHERE first_name LIKE 'test%';
DELETE FROM reimbursement_system_db.manager  WHERE first_name LIKE 'test%';

DELETE FROM reimbursement_system_db.reimbursement_request  WHERE reimbursement_request LIKE 'test%';





