create table if not exists roles
(
    id   bigint not null auto_increment,
    name varchar(255) unique,
    primary key (id)
);

create table if not exists users
(
    id         bigint       not null auto_increment,
    birth_date date,
    first_name varchar(255),
    last_name  varchar(255),
    login      varchar(255) not null unique,
    password   varchar(255) not null,
    work_exp   integer,
    primary key (id)
);


create table if not exists users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (role_id) references roles (id),
    foreign key (user_id) references users (id)
);