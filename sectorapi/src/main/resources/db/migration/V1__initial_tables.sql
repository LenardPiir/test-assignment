CREATE TABLE IF NOT EXISTS customer
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    term_agreement BOOLEAN
    );

CREATE TABLE IF NOT EXISTS sector
(
    id           SERIAL PRIMARY KEY,
    customer_id  INT NOT NULL,
    name         VARCHAR(255),
    CONSTRAINT fk_customer_sectors
    FOREIGN KEY (customer_id) REFERENCES customer(id)
    );

CREATE TABLE IF NOT EXISTS industry
(
    id           SERIAL PRIMARY KEY,
    sector_id    INT NOT NULL,
    name         VARCHAR(255),
    CONSTRAINT fk_sector_industries
    FOREIGN KEY (sector_id) REFERENCES sector(id)
    );

CREATE TABLE IF NOT EXISTS industry_specification
(
    id           SERIAL PRIMARY KEY,
    industry_id  INT NOT NULL,
    name         VARCHAR(255),
    CONSTRAINT fk_industry_specifications
    FOREIGN KEY (industry_id) REFERENCES industry(id)
    );