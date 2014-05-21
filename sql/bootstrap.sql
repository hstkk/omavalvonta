create database if not exists omavalvonta;
grant CREATE,INSERT,DELETE,UPDATE,SELECT on omavalvonta.* to omavalvonta@localhost;
set password for omavalvonta@localhost=password('salasana');
flush privileges;