create table users (
    id bigserial primary key,
    nickname varchar(128) unique not null,
    email varchar(128) unique not null,
    password varchar(80) not null,
    created_at timestamp default now()
);

create table roles (
    id bigserial primary key,
    role varchar(16) not null unique
);

insert into roles values
                      (1, 'USER'),
                      (2, 'OWNER'),
                      (3, 'PREMIUM'),
                      (4, 'ADMIN');

create table user_roles (
    user_id bigint references users,
    role_id bigint references roles,
    primary key (user_id, role_id)
);

create table parking (
    id bigserial primary key,
    latitude double precision not null,
    longitude double precision not null,
    name varchar(255) unique not null,
    max_places int not null,
    free_places int not null,
    rent_per_hour double precision,
    rating float
);

create table parking_review (
    user_id bigint references users,
    parking_id bigint references parking,
    grade int2 not null,
    comment text,
    created_at timestamp default now(),
    primary key (user_id, parking_id)
);

create table parking_points (
    parking_id bigint references parking,
    latitude double precision not null,
    longitude double precision not null,
    primary key (latitude, longitude)
);

create or replace function update_parking_rating()
returns trigger as $$
    begin
        update parking set rating = (
            select avg(grade) from parking_review where parking_id = new.parking_id
        ) where id = new.parking_id;
        return new;
    end;
$$ language plpgsql;

create trigger parking_review_insert_trigger
    after insert on parking_review
    for each row
    execute function update_parking_rating();