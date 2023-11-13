CREATE TABLE IF NOT EXISTS customer
(
    agree_to_terms boolean not null,
    id             uuid    not null
    primary key,
    name           varchar(255)
    );

CREATE TABLE IF NOT EXISTS sector
(
    level       integer      not null,
    code        varchar(255) not null
    primary key,
    name        varchar(255),
    parent_code varchar(255)
    constraint fkgyv63wb72u464n8uy7ipj51y7
    references sector
    );

CREATE TABLE IF NOT EXISTS customer_sector
(
    customer_id uuid         not null
    constraint fknnkjby3cy78t11i2l7b63n1me
    references customer,
    sector_code varchar(255) not null
    constraint fkhgmmcds9m2b1gt7b4tt54ehee
    references sector,
    primary key (customer_id, sector_code)
    );