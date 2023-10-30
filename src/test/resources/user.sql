DELETE FROM attend;
DELETE FROM booking;
DELETE FROM dependent;
DELETE FROM event;
DELETE FROM plan;
DELETE FROM profile;
DELETE FROM `user`;
INSERT INTO `user` (id, email, password, role, created_at, modified_at) VALUES (2, 'r.daima@aui.ma', '$2a$10$yUooQLvKn/SFd9VYD6Qq1.MLu.1zZm2ZXiNj5ljGBP00ZR4Iwi7Pu', 'CUSTOMER', 1697710671587, 1697710671587);