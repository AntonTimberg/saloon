INSERT INTO member(room, name, surname, gender, birthdate, login, password, status)
VALUES (4,'Domenic', 'Linderman', 'male', '1963-11-06', 'morgan', '917371', 'CLIENT'),
       (null, 'Berimor', 'Unknown', 'male', '1934-04-26', 'admin', 'admin', 'ADMIN');

INSERT INTO room(room, status, roomclass, capacity)
VALUES (4, 'OCCUPIED',  'PALACE', 4),
       (1, 'AVAILABLE', 'VIP', 2),
       (2, 'AVAILABLE', 'NORMAL', 1),
       (3, 'AVAILABLE', 'PRESIDENT', 3),
       (5, 'AVAILABLE', 'VIP', 2),
       (6, 'AVAILABLE', 'NORMAL', 2),
       (7, 'AVAILABLE', 'NORMAL', 1);

INSERT INTO reservation(room, reservationfrom, reservationuntil, userid)
VALUES (4, '1999-04-26', '2023-09-30', 1);
