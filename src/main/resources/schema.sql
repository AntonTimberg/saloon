CREATE TABLE member
(
    id         BIGINT  AUTO_INCREMENT PRIMARY KEY,
    room       INTEGER UNIQUE CHECK (room >= 1 AND room <= 7),
    name       VARCHAR(35) NOT NULL,
    surname    VARCHAR(35) NOT NULL,
    gender     VARCHAR(10) NOT NULL,
    birthdate  DATE NOT NULL,
    login      VARCHAR(15) NOT NULL UNIQUE,
    password   VARCHAR(15) NOT NULL,
    status     VARCHAR(25) NOT NULL DEFAULT 'CLIENT'
);

CREATE TABLE room
(
    id          BIGINT  AUTO_INCREMENT PRIMARY KEY,
    room        INTEGER UNIQUE NOT NULL,
    status      VARCHAR NOT NULL,
    roomclass   VARCHAR NOT NULL,
    capacity    INTEGER NOT NULL,
    image_path  VARCHAR(255)
);

CREATE TABLE reservation
(
    id               BIGINT  AUTO_INCREMENT PRIMARY KEY,
    room             INTEGER NOT NULL,
    reservationfrom  DATE NOT NULL,
    reservationuntil DATE NOT NULL,
    userid           INTEGER NOT NULL
);