create table users (
    id bigint generated always as identity primary key,
    username varchar(100),
    password varchar(100)
);
create table roles (
    id bigint generated always as identity primary key,
    name varchar(100)
);
create table roles_has_users (
    id bigint generated always as identity primary key,
    user_id bigint references users(id),
    role_id bigint references roles(id)
);
insert into users (username, password) values ('1111', '1111');
insert into users (username, password) values ('2222', '2222');
insert into roles (name) values ('ROLE_User');
insert into roles (name) values ('ROLE_Admin');
insert into roles_has_users (user_id, role_id) values (1, 1);
insert into roles_has_users (user_id, role_id) values (1, 2);
insert into roles_has_users (user_id, role_id) values (2, 1);
