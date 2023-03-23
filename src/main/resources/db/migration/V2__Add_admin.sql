insert into users (login, password, work_exp) values ('admin', 'admin', 0);
insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_ADMIN');
insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (1, 2);