INSERT INTO users(room, name, surname, gender, age, login, password, status)
VALUES (4,'Domenic', 'from Torrent', 'male', 56, 'pip', 'pip', 'CLIENT'),
       (null, 'Berimor', 'Unknown', 'male', 111, 'admin', 'admin', 'ADMIN');

INSERT INTO rooms(room, status, roomclass)
VALUES (4, 'OCCUPIED',  'PALACE'),
       (1, 'AVAILABLE', 'VIP'),
       (2, 'AVAILABLE', 'NORMAL'),
       (3, 'AVAILABLE', 'PRESIDENT'),
       (5, 'AVAILABLE', 'VIP'),
       (6, 'AVAILABLE', 'NORMAL'),
       (7, 'AVAILABLE', 'NORMAL');