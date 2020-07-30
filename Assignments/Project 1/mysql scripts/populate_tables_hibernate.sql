

CREATE OR REPLACE PROCEDURE reimbursement_system_db.procedure_populate_proj1_hibernate_db ()

BEGIN
	
INSERT INTO reimbursement_system_db.manager
(mgr_id , first_name , last_name , username , password )
VALUES
(0, 'John', 'Doe', 'johnUser', 'johnPass'),
(0, 'rick', 'brick', 'rickUser', 'rickPass'),
(0, 'mary', 'brooks', 'maryUser', 'maryPass');

	
INSERT INTO reimbursement_system_db.employee 
(emp_id , first_name , last_name , username , password, mgr_id )
VALUES

(0, 'greg', 'dreg', 'gregUser', 'gregPass', 1),
(0, 'ria', 'bro', 'riaUser', 'riaPass', 1),
(0, 'jax', 'briggs', 'jaxUser', 'jaxPass', 2),
(0, 'sonia', 'blade', 'soniaUser', 'soniaPass', 2),
(0, 'max', 'sonic', 'maxUser', 'maxPass', 3),
(0, 'rob', 'bob', 'robUser', 'robPass', 3);



INSERT INTO reimbursement_system_db.reimbursement_request 
(rr_id, emp_id , mgr_id ,reimbursement_request , reimbursement_status , reason )
VALUES
(0, 1, 1, 'req 5000$ to buy awesome car', 'DENIED', "you should save to buy it, no free money for you" ),
(0, 3, 2, 'req gazillion dollar to be rich', 'APPROVED', "why not, it is not like it's coming from my pocket"),
(0, 3, 2, 'req 550000 to cover medical bill', 'APPROVED', "covered by insurance"),
(0, 4, 2, 'req 550000 to cover Lamborghini', 'APPROVED', "it is an awesome car "),
(0, 6, 3,  'req 10000 to cover student loans', 'DENIED', " no reason, I don't feel like it ");

END;