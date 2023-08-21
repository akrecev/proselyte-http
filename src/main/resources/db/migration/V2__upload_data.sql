INSERT INTO users (name)
VALUES ('John Doe'),
       ('John Silver'),
       ('John Smith'),
       ('John Bloody Egg');

INSERT INTO files (name, file_path)
VALUES ('spring.java', 'C:\Java'),
       ('hello world.doc', 'C:\Users'),
       ('file.txt', 'C:\Users\Public\Documents'),
       ('Treasure island', 'C:\Users\Public\Books');

INSERT INTO events (user_id, file_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (3, 1),
       (3, 2);