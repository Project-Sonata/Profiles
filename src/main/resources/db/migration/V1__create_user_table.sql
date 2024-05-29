CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    sonata_id varchar(22) NOT NULL UNIQUE,
    context_uri VARCHAR(50) NOT NULL UNIQUE
);