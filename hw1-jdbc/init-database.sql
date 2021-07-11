create table if not exists locations(
    id serial primary key,
    name varchar(45) unique not null
);

create table if not exists routes(
    id serial primary key,
    from_id integer,
    to_id integer,
    cost integer,
    foreign key (from_id) references locations(id),
    foreign key (to_id) references locations(id)

);

create table if not exists problems(
    id serial primary key,
    from_id integer,
    to_id integer,
    foreign key (from_id) references locations(id),
    foreign key (to_id) references locations(id)
);

create table if not exists solutions(
    problem_id serial primary key,
    cost integer
--     foreign key (problem_id) references problems(id)
);

alter table solutions add foreign key (problem_id) references problems(id)
on delete cascade;
