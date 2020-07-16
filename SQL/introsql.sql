-- keywords in SQL are not case sensitive
-- convention is to use all caps

-- SQL is a Scripting language 
-- scripting language Tells a machine what to do

CREATE TABLE EMPLOYEE(E_ID int, NAME varchar(200), DEPARTMENT varchar(200));

INSERT INTO EMPLOYEE VALUES (1,'Adam Ranieri', 'Training');
INSERT INTO EMPLOYEE VALUES (2,'Ryan Schlientz', 'Training');
INSERT INTO EMPLOYEE VALUES (3,'Sierra Nicholes', 'Training');
INSERT INTO EMPLOYEE VALUES (4,'Richard Or', 'Training');
INSERT INTO EMPLOYEE VALUES (5,'Peter Alagna', 'Management');


-- remove tables
DROP TABLE EMPLOYEE;

-- querying in SQL 
-- All queries start with select 
-- after the select it is what fields you want * is everything
SELECT * FROM EMPLOYEE;

SELECT NAME,DEPARTMENT FROM EMPLOYEE;

-- adding criteria to restrict what records you want
-- restricts what records I get not the fields
SELECT * FROM EMPLOYEE WHERE DEPARTMENT = 'Training';
SELECT NAME FROM EMPLOYEE WHERE DEPARTMENT = 'Training';

-- this will delete a single record
DELETE FROM EMPLOYEE WHERE E_ID = 4;
DELETE FROM EMPLOYEE WHERE DEPARTMENT = 'Training';

DELETE FROM EMPLOYEE;


-- CRUD
-- create Insert
-- read Select
-- update update/set
-- delete Delete


UPDATE EMPLOYEE SET DEPARTMENT ='Management' WHERE DEPARTMENT = 'Training';


-- The current table we have is awful =(
-- We (mostly) want our database to be normalized
-- Normalization is the process by which we reduce the redundancy within our database
-- minimizes the amount of space required to store our information
-- usally increases the speed of our CRUD operations on tables because our tables are smaller

-- 1st normalized form
-- All fields should be atomic (you cannot break down that field into smaller bits of useful information)
-- Each record should be uniquely identifiable (every record should have a primary key)
-- the primary key is a guaranteed unique identifier to get a single record (unique + not null)

-- 2nd normalized form
-- You must already be in first noramlized
-- You cannot have functional dependencies (You cannot compute or calculate a field using other fields)

-- constraints are restrictions on my fields. Very helpful for maintaing data integrity
-- primary key
-- foreign key
-- unique
-- not null
-- check 
-- default 


CREATE TABLE EMPLOYEE(
E_ID int PRIMARY KEY AUTO_INCREMENT, 
FIRST_NAME varchar(200), 
LAST_NAME varchar(200), 
DEPARTMENT varchar(200)
);

INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,DEPARTMENT )VALUES ('Adam', 'Ranieri','Training');
INSERT INTO EMPLOYEE VALUES (0,'Trevin', 'Chester','Training');
INSERT INTO EMPLOYEE VALUES (0,'Adam', 'Ranieri','Training');
INSERT INTO EMPLOYEE VALUES (0,null, 'Smith','Training');

SELECT * FROM EMPLOYEE;

DROP TABLE EMPLOYEE;
