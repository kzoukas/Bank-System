DROP SCHEMA IF EXISTS user;
DROP SCHEMA IF EXISTS account;
DROP SCHEMA IF EXISTS transaction;
CREATE SCHEMA IF NOT EXISTS user
CREATE SCHEMA IF NOT EXISTS account
CREATE SCHEMA IF NOT EXISTS transaction
CREATE TABLE user
(
    id INT4 NOT NULL,
    firstName varchar (255) NOT NULL,
    lastName varchar (255) NOT NULL,
    primary key (id)
);

CREATE TABLE account
(
    id INT4 NOT NULL,
    customerID BIGINT (19) NOT NULL,
    initialCredit DOUBLE (17) NOT NULL,
    primary key (id)
);

CREATE TABLE transaction
(
    id INT4 NOT NULL,
    account BIGINT (19) NOT NULL,
    currentDate TIMESTAMP (26,6) NOT NULL,
    customerID BIGINT (19) NOT NULL,
    money DOUBLE (17) NOT NULL,
    primary key (id)
);
