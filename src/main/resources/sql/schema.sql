CREATE SEQUENCE job_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 10;


CREATE TABLE job
(
    id          BIGINT PRIMARY KEY UNIQUE DEFAULT nextval('job_seq'),
    title       VARCHAR(255) NOT NULL UNIQUE,
    description TEXT         NOT NULL,
    min_salary  BIGINT       NOT NULL,
    max_salary  BIGINT       NOT NULL,
    location    VARCHAR(255) NOT NULL,
    company_id  BIGINT       NOT NULL,
    version     BIGINT       NOT NULL
);