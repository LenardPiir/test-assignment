create table if not exists customer_sector
(
    customer_id uuid         not null,
    sector_code varchar(255) not null,
    primary key (customer_id, sector_code)
);

alter table customer_sector
    owner to "sector-api";

