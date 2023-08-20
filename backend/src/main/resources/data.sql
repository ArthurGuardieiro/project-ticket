INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_city (name, state) VALUES ('Uberlândia', 'MG');
INSERT INTO tb_city (name, state) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO tb_city (name, state) VALUES ('São Paulo', 'SP');
INSERT INTO tb_city (name, state) VALUES ('Uberaba', 'MG');

INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('arthur@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'arthur', 2, 1);
INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('joao@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'joao', 1, 2);
INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'maria', 1, 3);
INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('marta@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'marta', 1, 4);

INSERT INTO tb_event (name, addres, city_id, date) VALUES ('CIA 2023', 'Centro Park', 4, TIMESTAMP WITH TIME ZONE '2023-06-14T14:00:00Z');
INSERT INTO tb_event (name, addres, city_id, date) VALUES ('OpenArq', 'Casteli', 1, TIMESTAMP WITH TIME ZONE '2023-07-17T21:00:00Z');
INSERT INTO tb_event (name, addres, city_id, date) VALUES ('The One', 'London', 1, TIMESTAMP WITH TIME ZONE '2023-08-20T22:00:00Z');
INSERT INTO tb_event (name, addres, city_id, date) VALUES ('Balada da Barra', 'Barra da tijuca', 2, TIMESTAMP WITH TIME ZONE '2023-06-14T14:00:00Z');
INSERT INTO tb_event (name, addres, city_id, date) VALUES ('Calvin Harris', 'Morumbi', 3, TIMESTAMP WITH TIME ZONE '2023-06-14T14:00:00Z');

INSERT INTO tb_ticket (price, user_id, event_id) VALUES (300.0, 1, 1);
INSERT INTO tb_ticket (price, user_id, event_id) VALUES (550.0, 2, 1);
INSERT INTO tb_ticket (price, user_id, event_id) VALUES (300.0, 3, 1);