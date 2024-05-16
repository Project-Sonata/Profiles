CREATE TABLE profiles
(
    id        SERIAL PRIMARY KEY,
    public_id VARCHAR(22) NOT NULL UNIQUE,
    display_name VARCHAR(50)
);