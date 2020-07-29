-- for hibernate leave tables and fields lowercase
CREATE TABLE director(d_id int PRIMARY KEY AUTO_INCREMENT, name varchar(200));
DROP TABLE director;

SELECT * from director;
SELECT * FROM movie;

CREATE TABLE movie(
m_id int PRIMARY KEY AUTO_INCREMENT, 
title varchar(200) NOT NULL, 
runtime int,
d_id int,
CONSTRAINT fk_movie_director FOREIGN KEY movie(d_id) REFERENCES director(d_id));

INSERT INTO director VALUES (0,'Steven Spielberg');

INSERT INTO movie VALUES (0,'Jurassic Park', 120,1);
INSERT INTO movie VALUES (0,'Saving Private Ryan', 135,1);
INSERT INTO movie VALUES (0,'ET', 100,1);


CREATE TABLE actor(
a_id int PRIMARY KEY AUTO_INCREMENT,
name varchar(200)
);

CREATE TABLE actor_movie(
a_id int,
m_id int,
CONSTRAINT fk_am_actor FOREIGN KEY actor_movie(a_id) REFERENCES actor(a_id),
CONSTRAINT fk_am_movie FOREIGN KEY actor_movie(m_id) REFERENCES movie(m_id)

);


INSERT INTO actor VALUES(0,'Ismael "da Rock"');
INSERT INTO actor VALUES(0,'Stone Cold Noah Rutan');

INSERT INTO actor_movie VALUES(1,8);
INSERT INTO actor_movie VALUES(1,9);

INSERT INTO actor_movie VALUES (2,2);
INSERT INTO actor_movie VALUES(2,3);
INSERT INTO actor_movie VALUES(2,10);

SELECT * FROM actor_movie;


