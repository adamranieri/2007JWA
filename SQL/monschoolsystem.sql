-- your tables should match your entities
-- your fields in Java should match your fields in the database 
-- the name of the table should match your entity class name

CREATE TABLE school(
s_id int PRIMARY KEY AUTO_INCREMENT, 
name varchar(200) NOT NULL, 
capacity int);


INSERT INTO school VALUES (0,'Mon High',120);
SELECT * FROM school;