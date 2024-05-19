CREATE TYPE gender AS ENUM ('MALE', 'FEMALE', 'NONE');

CREATE TABLE profiles
(
    id        SERIAL PRIMARY KEY,
    public_id VARCHAR(22) NOT NULL UNIQUE,
    context_uri VARCHAR(50) NOT NULL UNIQUE,
    display_name VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    country VARCHAR(15) NOT NULL,
    birthdate DATE NOT NULL,
    gender gender NOT NULL
);