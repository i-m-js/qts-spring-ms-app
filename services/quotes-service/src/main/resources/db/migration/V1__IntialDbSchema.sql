drop table if exists quote_users;
create table quote_users(
    id bigserial primary key,
    username varchar(256) not null unique,
    email varchar(256) not null,
    password text,
    is_active boolean default false,
    user_source int check user_source in (0, 1, 2, 3),
    created_on timestamp default NOW(),
    last_modified_on timestamp default NOW(),
    last_login timestamp default NOW()
);
-- drop index if exists QUOTE_USERS_IDX;
-- create index QUOTE_USERS_IDX ON quote_users(id);
-- create table annonymous_users(
--     uid text primary key,
--     generated_on timestamp default now() --For now, update this directly here for postgres
--     -- expires_at timestamp default DATEADD(YEAR, 2, NOW())
-- );
-- drop table quote_authors if exists;
-- create table quote_authors(
--     id bigserial primary key,
--     author_name varchar(128),
--     user_id bigint references quote_users(id)
-- );
drop table IF EXISTS quotes;
create table quotes(
    id bigserial primary key,
    content text not null,
    posted_by_id bigint references quote_users(id),
    author varchar(256),
    posted_on timestamp default now(),
    modified_on timestamp default now()
);
drop table if exists quote_events;
create table quote_events(
    user_id bigint references quote_users(id),
    -- annonymous_uid text references annonymous_users(uid),
    quote_id bigint references quotes(id),
    event_type int check event_type in (0, 1, 2, 3)
);
drop index if exists QUOTE_EVENTS_IDX;
create index QUOTE_EVENTS_IDX ON quote_events(user_id, quote_id);