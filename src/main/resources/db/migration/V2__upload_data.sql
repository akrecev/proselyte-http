INSERT INTO users (name)
VALUES ('Santa Claus'),
       ('John Silver'),
       ('Frodo Baggins'),
       ('Chingachgook');

INSERT INTO files (name, file_path)
VALUES ('Treasure-island-map.jpg', 'D:/kretsev/study/dev/proselyte/2.4/proselyte-http/src/main/resources/upload/'),
       ('Text.txt', 'D:/kretsev/study/dev/proselyte/2.4/proselyte-http/src/main/resources/upload/'),
       ('Excel.xlsx', 'D:/kretsev/study/dev/proselyte/2.4/proselyte-http/src/main/resources/upload/'),
       ('Word.docx', 'D:/kretsev/study/dev/proselyte/2.4/proselyte-http/src/main/resources/upload/');

INSERT INTO events (user_id, file_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (3, 1),
       (3, 2);