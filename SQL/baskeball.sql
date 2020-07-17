-- basketball database

CREATE TABLE team(
team_id int PRIMARY KEY AUTO_INCREMENT,
name varchar(200) UNIQUE,
mascot varchar(200)
);

INSERT INTO team VALUES (0,'Funkadellics','Funky the Otter');
INSERT INTO team VALUES (0,'Grand Dunk Railroad','Choo Chralie');
INSERT INTO team VALUES (0,'Iron Squad','Steely the Iron');

SELECT * FROM team;

CREATE TABLE player(
player_id int PRIMARY KEY AUTO_INCREMENT, 
name varchar(200),
salary int,
t_id int, -- shouldn't be unique (99% of time)
CONSTRAINT fk_player_team FOREIGN KEY player(t_id) REFERENCES team(team_id) -- create a foreign key
);
DROP TABLE player;
SELECT * FROM player;

-- Foreign key is a contstraint that links two tables togther
-- the table that has the foreign key is the child table. It's must exist before it can be created
-- the foreign key references the parent table
-- The field you place a foreign key constraint on is not unique. BUT what references should be

INSERT INTO player VALUES (0,'Adam Ranieri',1000000, 1);
INSERT INTO player VALUES (0,'Jamir',2000000, 1);
INSERT INTO player VALUES (0,'Brandon Cook',700000, 1);

INSERT INTO player VALUES (0,'Jesse',500000, 2);
INSERT INTO player VALUES (0,'Will',200000, 2);
INSERT INTO player VALUES (0,'Dylan',10000, 2);

INSERT INTO player VALUES (0,'Colin',1000000, 3);
INSERT INTO player VALUES (0,'Sean',2000000, 3);
INSERT INTO player VALUES (0,'Cullen',500000, 3);

-- by seperating tables we prevent redundancy

-- SELECT * FROM player LEFT join team on player.t_id = team.team_id;

CREATE TABLE coach(
coach_id int PRIMARY KEY AUTO_INCREMENT, 
name varchar(200), 
specialty varchar(200), 
salary int, 
t_id int,
CONSTRAINT fk_coach_team FOREIGN KEY coach(t_id) REFERENCES team(team_id)
);

INSERT INTO coach VALUES (0,'Mike White', 'Head Coach', 3000000, 1);
INSERT INTO coach VALUES (0,'Patric Young', 'Strenth Coach', 90000, 1);

INSERT INTO coach VALUES (0,'Walter White', 'Nutrition Coach', 1000000, 2);
INSERT INTO coach VALUES (0,'Gustavo Fring', 'Head Coach', 9000000, 2);

INSERT INTO coach VALUES (0,'Donald Draper', 'Head Coach', 30000000, 3);
INSERT INTO coach VALUES (0,'Roger Sterling', 'Side Coach', 20000000, 3);

-- 3rd normalized form
-- in 2nd normalized form
-- No transitive dependencies (you cannot find the information elsewhere in the database)
-- if player had a field called coach 

CREATE TABLE game(
game_id int PRIMARY KEY AUTO_INCREMENT, 
game_date date, 
location varchar(200));


INSERT INTO game VALUES (0, '1965-07-20', 'Cinnicinatti Funk Dome');
INSERT INTO game VALUES (0, '1965-07-22', 'Detroit Roller Dico');
INSERT INTO game VALUES (0, '1965-07-29', 'Miami Rock Arena');

SELECT * FROM game;
SELECT * FROM player;
-- a junction table needs to have two foreign keys
-- each foreign key links to a different parent
-- in order for a record to exist of a player playing in a game
-- both player and game must exist first
CREATE TABLE game_player(
player_id int, 
game_id int,
CONSTRAINT fk_gp_player FOREIGN KEY game_player(player_id) REFERENCES player(player_id),
CONSTRAINT fk_gp_game FOREIGN KEY game_player(game_id) REFERENCES game(game_id)
);
SELECT * FROM game_player;

INSERT INTO game_player VALUES(1,1);
INSERT INTO game_player VALUES(2,1);

INSERT INTO game_player VALUES (7,1);
INSERT INTO game_player VALUES (8,1);


INSERT INTO game_player VALUES (1,2);
INSERT INTO game_player VALUES (3,2);

INSERT INTO game_player VALUES (9,2);
INSERT INTO game_player VALUES (8,2);


INSERT INTO game_player VALUES (4,3);
INSERT INTO game_player VALUES (5,3);

INSERT INTO game_player values(2,3);
INSERT INTO game_player values(3,3);


-- Transactions and committing
-- In SQL you can create transactions and you must commit any changes to a database for them to persist
-- committing is telling the database to phyisically write your information


INSERT INTO team VALUES (0,'Muttonchop Mountaineers', 'The Mountaineer');
COMMIT; -- forces all changes since the last commit to be saved
ROLLBACK; -- rollback means to go back to the previous commit

DELETE FROM coach;

-- transaction is a logical sequence of SQL statements
-- ACID properties of transactions
-- when you create a transaction you should to ACID principals to ensure a consistent and good db

-- JOINS and UNIONS

-- JOINS are way to denormalize our database
-- we are horizontally stiching two tables together based on some join predicate
-- the main purpose of joins is so that you can make easier queries
SELECT * FROM coach;
SELECT * FROM team;
SELECT * FROM player;

-- you can create virtual tables which are computed tables that do not exist physically
SELECT name,mascot FROM team; -- what is returned is a virtual table

-- left join everything in the left table will be presented even if it does not match something in the right
SELECT * FROM team LEFT JOIN coach ON team.team_id = coach.t_id;

-- all coaches (the left table) will presented it does not show things in the right table it does not match too
SELECT * FROM coach LEFT JOIN team ON team.team_id = coach.t_id;

-- find me the coaches for players
SELECT coach.name,player.name  FROM coach INNER JOIN player on coach.t_id = player.t_id;

-- cartesian product every record in one table matched with every record in the other
-- sometimes helpful for answering hypothetical queries
SELECT * FROM coach CROSS JOIN player;


-- unions stack tables on top of each vertically
-- unions require that the two tables you union have the same amount of fields
-- unions are helpful when you have fields that are the same in seperate tables and you
-- need to perform query over both of them
-- whats the average salary in the league? 
SELECT name, salary FROM player
UNION ALL
SELECT name, salary FROM coach

-- functions are operations that you can apply to a field
-- aggregate must be applied to multiple records to make sense (average, sum, mode)
-- scalar is applied to single record's field

SELECT AVG(salary) FROM player;

-- when you use aggregate functions you should use group by to organize your query
-- group by put my records into buckets based on this criterion

-- what is the average salary per team?
SELECT * FROM player;
SELECT AVG(salary) FROM player GROUP BY t_id;

SELECT * FROM player left join team on player.t_id = team.team_id; -- virtual table

-- average salary by team name
SELECT avg(salary),name 
FROM (SELECT player_id,salary,team.name FROM player left join team on player.t_id = team.team_id)
AS dummy 
GROUP BY name;

-- functions cannot alter your database
-- no insert, deletes, or drops
-- must always have at least one input and always give an output

SELECT UPPER(name) FROM player; -- scalar function applies the functino to each field














