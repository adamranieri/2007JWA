-- your tables should match your entities
-- your fields in Java should match your fields in the database 
-- the name of the table should match your entity class name

CREATE TABLE school(
s_id int PRIMARY KEY AUTO_INCREMENT, 
name varchar(200) NOT NULL, 
capacity int);

CREATE TABLE student(
stu_id int PRIMARY KEY AUTO_INCREMENT,
name varchar(200),
s_id int,
CONSTRAINT fk_student_school FOREIGN KEY student(s_id) REFERENCES school(s_id)
);



INSERT INTO school VALUES (0,'Mon High',120);
SELECT * FROM school;
SELECT * FROM student;
INSERT into student VALUES (0,'Mike',5);

-- a procedure is basically just a stored script
-- it *can* take in parameters
-- SQL is so old that C invented curly brackets 20 years later
-- set to ORACLE Syntax

SET SQL_MODE = 'ORACLE';

CREATE OR REPLACE PROCEDURE set_up_mondb AS
BEGIN
	CREATE TABLE school(
	s_id int PRIMARY KEY AUTO_INCREMENT, 
	name varchar(200) NOT NULL, 
	capacity int);

	CREATE TABLE student(
	stu_id int PRIMARY KEY AUTO_INCREMENT,
	name varchar(200),
	s_id int,
	CONSTRAINT fk_student_school FOREIGN KEY student(s_id) REFERENCES school(s_id)
	);
END;


CREATE OR REPLACE PROCEDURE tear_down_mondb AS
BEGIN
	DROP TABLE student;
	DROP TABLE school;
END;

CALL tear_down_mondb;
CALL set_up_mondb;

