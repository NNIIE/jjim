drop table if exists user cascade;
drop table if exists product cascade;
drop table if exists drawer cascade;

CREATE TABLE user (
   id VARCHAR(36) NOT NULL,
   email VARCHAR(50) NOT NULL,
   password VARCHAR(100) NOT NULL,
   created_at DATETIME(6) NOT NULL,
   updated_at DATETIME(6) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY uq_user_email (email)
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    thumbnail_url VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
);

CREATE TABLE drawer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(36) not null,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    UNIQUE KEY uq_user_name (user_id, name)
);