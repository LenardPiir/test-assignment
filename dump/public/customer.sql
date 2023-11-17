create table if not exists customer
(
    agree_to_terms boolean not null,
    id             uuid    not null
        primary key,
    name           varchar(255)
);

alter table customer
    owner to "sector-api";

