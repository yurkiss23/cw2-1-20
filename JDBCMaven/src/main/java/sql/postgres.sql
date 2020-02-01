psql -U postgres

CREATE USER vova with PASSWORD 'Qwerty1-';

\du

CREATE DATABASE paraskadb;

\l

GRANT ALL PRIVILEGES ON DATABASE "paraskadb" to vova;

\q


CREATE TABLE tbl_employees
(
   ID SERIAL PRIMARY KEY,
   NAME VARCHAR(100) NOT NULL,
   SALARY decimal(15, 2) NOT NULL,
   CREATED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

