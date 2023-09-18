INSERT INTO users(room, name, surname, gender, birthdate, login, password, status)
VALUES (4,'Domenic', 'Linderman', 'male', '1963-11-06', 'morgan', '917371', 'CLIENT'),
       (null, 'Berimor', 'Unknown', 'male', '1934-04-26', 'admin', 'admin', 'ADMIN');

INSERT INTO rooms(room, status, roomclass)
VALUES (4, 'OCCUPIED',  'PALACE'),
       (1, 'AVAILABLE', 'VIP'),
       (2, 'AVAILABLE', 'NORMAL'),
       (3, 'AVAILABLE', 'PRESIDENT'),
       (5, 'AVAILABLE', 'VIP'),
       (6, 'AVAILABLE', 'NORMAL'),
       (7, 'AVAILABLE', 'NORMAL');