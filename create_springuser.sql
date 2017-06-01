create database maindb;
create user 'isssr'@'localhost' identified by 'password';
grant all on maindb.* to 'isssr'@'localhost';
