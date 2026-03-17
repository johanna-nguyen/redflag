CREATE TABLE category
(
    id   BIGSERIAL NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);
