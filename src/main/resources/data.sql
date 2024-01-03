create table todo
(
    id          INTEGER not null,
    username    VARCHAR not null,
    description VARCHAR not null,
    target_date DATE    not null,
    is_done     BOOLEAN not null
);

insert into todo(id, username, description, target_date, is_done)
values(10001, 'Jitendra', 'Learn DSA', CURRENT_TIMESTAMP(), false);

insert into todo(id, username, description, target_date, is_done)
values(10002, 'Jitendra', 'Learn Algo', CURRENT_TIMESTAMP(), false);

insert into todo(id, username, description, target_date, is_done)
values(10003, 'Jitendra', 'Learn AWS', CURRENT_TIMESTAMP(), false);

insert into todo(id, username, description, target_date, is_done)
values(10004, 'Jitendra', 'Learn DevOps', CURRENT_TIMESTAMP(), false);