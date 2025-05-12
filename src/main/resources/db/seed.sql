-- src/main/resources/db/seed.sql

CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
    );

-- bcrypt hash for 'testpassword' (example: $2a$10$Dow1Qw6Qw6Qw6Qw6Qw6QwOeQw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6)
INSERT INTO users (username, email, password) VALUES (
                                                         'aaa',
                                                         'aaa@gmail.com',
                                                         '$2a$10$Dow1Qw6Qw6Qw6Qw6Qw6QwOeQw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6'
                                                     );