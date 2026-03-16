CREATE TABLE reporter
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    ip_address VARCHAR(255)          NULL,
    create_at  datetime              NULL,
    CONSTRAINT pk_reporter PRIMARY KEY (id)
);