insert
into
    users
    (username, password, first_name, last_name, email, enabled, role_id, created_at, updated_at)
values
    ('admin', '$2a$10$UXH78s4cAZjV7VGRA20li.z1GA8DV.ubEjVvp1vfp.8uEJ7XiFQua', 'Giannis', 'Kafousis', 'admin@spring.io', true, 1, current_timestamp, current_timestamp);

insert
into
    users
    (username, password, first_name, last_name, email, enabled, role_id, created_at, updated_at)
values
    ('manager', '$2a$10$EV.C18n9342j2bcyZPnzWuXuQ3O6gHeLxE8czDW/83iccpwiIQ0Dq', 'John', 'Doe', 'manager@spring.io', true, 2, current_timestamp, current_timestamp);

insert
into
    users
    (username, password, first_name, last_name, email, enabled, role_id, created_at, updated_at)
values
    ('user', '$2a$10$keo5enGVTpuapw5x0re.Eu9AfcfNeDJtzDmKAAU4zsFVRwlmEvKra', 'Fred', 'Bloggs', 'user@spring.io', true, 3, current_timestamp, current_timestamp);