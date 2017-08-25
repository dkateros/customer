--sample data. also fix sequences
insert into customer (id, firstname, lastname, birthdate, email) values (1, 'Dimitris', 'Kateros', '1900-11-10', 'email@gmail.com');
select next value for customer_seq;