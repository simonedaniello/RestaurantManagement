set character_set_server = 'utf8';
set collation_server = 'utf8_general_ci';
create database maindb;
create user 'isssr'@'localhost' identified by 'password';
grant all on maindb.* to 'isssr'@'localhost';