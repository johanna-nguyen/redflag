CREATE TABLE reporter
(
    id         BIGSERIAL NOT NULL,
    ip_address VARCHAR(255)          NULL,
    create_at  timestamp             NULL,
    CONSTRAINT pk_reporter PRIMARY KEY (id)
);
