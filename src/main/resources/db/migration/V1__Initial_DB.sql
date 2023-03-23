create table roles (id bigint not null auto_increment, name varchar(255), primary key (id));
create table users (id bigint not null auto_increment, birth_date date, first_name varchar(255), last_name varchar(255),
    login varchar(255) not null, password varchar(255) not null, work_exp integer, primary key (id));
create table users_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));


alter table roles add constraint UK_roles unique (name);

alter table users add constraint UK_users unique (login);
alter table users_roles add constraint role_id_fk foreign key (role_id) references roles (id);
alter table users_roles add constraint user_id_fk foreign key (user_id) references users (id);