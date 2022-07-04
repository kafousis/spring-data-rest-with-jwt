-- UNIQUE CONSTRAINTS

alter table privileges
   add constraint UNQ_privilege_name unique (name);

alter table roles
   add constraint UNQ_role_name unique (name);

alter table users
   add constraint UNQ_user_username unique (username);

alter table users
   add constraint UNQ_user_email unique (email);

-- FOREIGN KEYS

alter table roles_privileges
   add constraint FK_role_id
   foreign key (role_id)
   references roles;

alter table roles_privileges
   add constraint FK_privilege_id
   foreign key (privilege_id)
   references privileges;

alter table users
   add constraint FK_role_id
   foreign key (role_id)
   references roles;