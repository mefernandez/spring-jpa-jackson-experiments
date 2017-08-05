create table employee (
	id bigint not null, 
	name varchar(255),
	pass varchar(255),
	primary key (id)
);

create sequence employee_id_seq start with 1 increment by 1;
