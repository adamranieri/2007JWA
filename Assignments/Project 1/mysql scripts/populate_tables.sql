

CREATE OR REPLACE PROCEDURE reimbursement_system_db.procedure_populate_proj1_db ()

BEGIN
	
INSERT INTO reimbursement_system_db.emp_mgr 
(emp_id , first_name , last_name , username , password , emp_role, mgr_id)
VALUES
(0, 'John', 'Doe', 'johnUser', 'johnPass', 'MGR', NULL),
(0, 'rick', 'brick', 'rickUser', 'rickPass', 'MGR', NULL),
(0, 'mary', 'brooks', 'maryUser', 'maryPass', 'MGR', NULL),
(0, 'greg', 'dreg', 'gregUser', 'gregPass', 'EMP', 1),
(0, 'ria', 'bro', 'riaUser', 'riaPass', 'EMP', 1),
(0, 'jax', 'briggs', 'jaxUser', 'jaxPass', 'EMP', 2),
(0, 'sonia', 'blade', 'soniaUser', 'soniaPass', 'EMP', 2),
(0, 'max', 'sonic', 'maxUser', 'maxPass', 'EMP', 3),
(0, 'rob', 'bob', 'robUser', 'robPass', 'EMP', 3),
(0, 'creflo', 'dollar', 'crefloUser', 'crefloPass', 'EMP', NULL);



INSERT INTO reimbursement_system_db.reimbursement_request 
(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
VALUES
(0, 5, 1, 'req 5000$ to buy awesome car', 'DENIED', "you should save to buy it, no free money for you" ),
(0, 6, 2, 'req gazillion dollar to be rich', 'APPROVED', "why not, it is not like it's coming from my pocket"),
(0, 6, 2, 'req 550000 to cover medical bill', 'APPROVED', "covered by insurance"),
(0, 9, 3,  'req 10000 to cover student loans', 'DENIED', " no reason, I don't feel like it ");

END;