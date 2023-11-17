create table if not exists sector
(
    level       integer      not null,
    code        varchar(255) not null
        primary key,
    name        varchar(255),
    parent_code varchar(255)
        constraint fkgyv63wb72u464n8uy7ipj51y7
            references sector
);

alter table sector
    owner to "sector-api";

