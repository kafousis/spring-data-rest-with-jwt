create table privileges (
    id bigserial not null,
    name varchar(255) not null,
    category varchar(255) not null,
    primary key (id)
);

create table roles (
    id bigserial not null,
    name varchar(255) not null,
    description varchar(255),
    primary key (id)
);

create table roles_privileges (
    role_id bigserial not null,
    privilege_id bigserial not null,
    primary key (role_id, privilege_id)
);

create table users (
    id bigserial not null,
    username varchar(255) not null,
    password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    enabled boolean not null,
    role_id bigserial not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    primary key (id)
);