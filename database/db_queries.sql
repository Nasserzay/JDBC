DROP DATABASE IF EXISTS `db_queries`;

CREATE DATABASE `db_queries`;

USE `db_queries`;

CREATE TABLE employee
(
    id         int(11) PRIMARY KEY AUTO_INCREMENT,
    name       varchar(255) NOT NULL,
    gender     BOOLEAN,
    birth_date DATE,
    salary     REAL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

