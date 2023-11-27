INSERT INTO member(name, surname, gender, birthdate, login, password, status)
VALUES ('Domenic', 'Linderman', 'male', '1963-11-06', 'user', '$2a$10$r1InGXSHsQjRi2qjbdTv5.8tdc07My4RhxFf55VEUsXqhZOXo9z26', 'CLIENT'),
       ('Salvatore', 'Ganacci', 'male', '1934-04-26', 'admin', '$2a$10$RRqd/qmqzhcqgSftOmGOZO5JiWTIIgW/fQQGASKef0F8E0F7ehKr.', 'ADMIN');

INSERT INTO room(room_number, status, roomclass, capacity, image_path)
VALUES (1, 'AVAILABLE',  'PALACE', 4, 'PALACE.jpg'),
       (2, 'AVAILABLE', 'VIP', 2, 'VIP.jpg'),
       (3, 'AVAILABLE', 'NORMAL', 1, 'normal.jpg'),
       (4, 'AVAILABLE', 'PRESIDENT', 3, 'PRESIDENT.jpg'),
       (5, 'AVAILABLE', 'VIP', 2, 'VIP.jpg'),
       (6, 'AVAILABLE', 'NORMAL', 2, 'normal.jpg'),
       (7, 'AVAILABLE', 'NORMAL', 1, 'normal.jpg'),
       (8, 'AVAILABLE', 'NORMAL', 1, 'normal.jpg'),
       (404, 'MAINTENANCE', 'NORMAL', 1, null);

INSERT INTO reservation(room, reservationfrom, reservationuntil, userid)
VALUES (4, '1999-04-26', '2024-09-30', 1);
