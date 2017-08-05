create table salary (
	id bigint not null, 
	amount double,
	from_date date,
	to_date date,
	primary key (id)
);

create table employee_salaries (
	employee_id bigint not null references employee, 
	salaries_id bigint not null references salary 
);

create sequence salary_id_seq start with 1 increment by 1;
