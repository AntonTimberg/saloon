INSERT INTO member(room, name, surname, gender, birthdate, login, password, status)
VALUES (1,'Domenic', 'Linderman', 'male', '1963-11-06', 'user', 'user', 'CLIENT'),
       (null, 'Salvatore', 'Ganacci', 'male', '1934-04-26', 'admin', 'admin', 'ADMIN');

INSERT INTO room(room_number, status, roomclass, capacity, image_path)
VALUES (1, 'OCCUPIED',  'PALACE', 4, 'PALACE.jpg'),
       (2, 'AVAILABLE', 'VIP', 2, 'VIP.jpg'),
       (3, 'AVAILABLE', 'NORMAL', 1, 'normal.jpg'),
       (4, 'AVAILABLE', 'PRESIDENT', 3, 'PRESIDENT.jpg'),
       (5, 'AVAILABLE', 'VIP', 2, 'VIP.jpg'),
       (6, 'AVAILABLE', 'NORMAL', 2, 'normal.jpg'),
       (7, 'AVAILABLE', 'NORMAL', 1, 'normal.jpg'),
       (8, 'AVAILABLE', 'NORMAL', 1, 'normal.jpg'),
       (404, 'MAINTENANCE', 'NORMAL', 1, null);

INSERT INTO reservation(room, reservationfrom, reservationuntil, userid)
VALUES (4, '1999-04-26', '2023-09-30', 1);
