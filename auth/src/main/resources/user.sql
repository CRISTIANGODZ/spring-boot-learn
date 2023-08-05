CREATE DATABASE auth;

USE auth;

CREATE TABLE `user_auth` (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             username VARCHAR(200) NOT NULL,
                             password VARCHAR(500) NOT NULL,
                             INDEX idx_username(username)
);

INSERT INTO auth.user_auth (username, password) VALUES ('zhangSan', '$2a$10$cQL7c7t7Z7BuGN3RFCs/sOQYBtbtZfepr.LoeLKBFTaa.aVnW20Qu')