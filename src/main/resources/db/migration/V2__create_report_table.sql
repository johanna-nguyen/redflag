CREATE TABLE report
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    first_name    CHAR                  NULL,
    last_name     CHAR                  NULL,
    age_range     VARCHAR(255)          NULL,
    city          VARCHAR(255)          NULL,
    nationality   VARCHAR(255)          NULL,
    create_at     datetime              NULL,
    category_name BIGINT                NULL,
    CONSTRAINT pk_report PRIMARY KEY (id)
);