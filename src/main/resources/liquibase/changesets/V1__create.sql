create table if not exists books (
    id bigint generated by default as identity primary key,
    isbn varchar(32) not null unique,
    name varchar(255) not null,
    author varchar(255) not null,
    publisher varchar(255) not null,
    genre varchar(128) not null,
    year_of_publication int not null,
    pages int not null
);

create table if not exists executions (
    id bigint generated by default as identity primary key,
    method_name varchar(512) not null,
    class_name varchar(512) not null,
    args varchar(4096) not null,
    is_success boolean not null,
    millis_took bigint,
    executed_at timestamp not null
);
