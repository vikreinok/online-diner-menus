create table if not exists menu
(
ID  INT,
entityVersion BIGINT,
created DATE,
name VARCHAR(255),
);
insert into menu ( created, name) values (  '2014-09-16', 'testdata');
