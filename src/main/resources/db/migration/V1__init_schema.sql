CREATE TABLE IF NOT EXISTS files
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY,
    name      CHARACTER VARYING(50) NOT NULL,
    file_path CHARACTER VARYING(50) NOT NULL,
    CONSTRAINT pk_files PRIMARY KEY (id)
);