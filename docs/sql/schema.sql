CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE category (
    id           UUID        PRIMARY KEY DEFAULT uuid_generate_v4(),
    name         VARCHAR(100) NOT NULL
);

CREATE TABLE post (
    id           UUID         PRIMARY KEY DEFAULT uuid_generate_v4(),
    title        VARCHAR(500) NOT NULL,
    content      TEXT         NOT NULL,
    created_date TIMESTAMP    NOT NULL DEFAULT NOW(),
    category_id  UUID         NOT NULL REFERENCES category(id)
);
