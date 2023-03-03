-- INSERT INTO student(firstname, lastname) VALUES
--     ("Santatra", "Harimbola"),
--     ("Nicolas", "Romanina"),
--     ("Tolotra", "Aurelien"),
--     ("Lahatriniaina", "Harentsoa")
--     ;

-- CREATE TABLE subject(
--     id int not null PRIMARY KEY AUTO_INCREMENT,
--     name VARCHAR(20),
--     credit tinyint,
--     instructor_id int
-- );

CREATE TABLE instructor(
    id int not null PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
);