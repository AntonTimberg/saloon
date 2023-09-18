CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    room       INTEGER UNIQUE CHECK (room >= 1 AND room <= 7),
    name       VARCHAR(35) NOT NULL,
    surname    VARCHAR(35) NOT NULL,
    gender     VARCHAR(10) NOT NULL,
    birthdate  DATE NOT NULL,
    login      VARCHAR(15) NOT NULL UNIQUE,
    password   VARCHAR(15) NOT NULL,
    status     VARCHAR(25) NOT NULL DEFAULT 'Client'
);

CREATE TABLE rooms
(
    id          BIGINT  AUTO_INCREMENT PRIMARY KEY,
    room INTEGER UNIQUE NOT NULL,
    status      VARCHAR NOT NULL,
    roomclass   VARCHAR NOT NULL
);