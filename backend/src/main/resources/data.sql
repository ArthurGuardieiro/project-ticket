INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_city (name, state) VALUES ('Uberlândia', 'MG');
INSERT INTO tb_city (name, state) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO tb_city (name, state) VALUES ('São Paulo', 'SP');
INSERT INTO tb_city (name, state) VALUES ('Uberaba', 'MG');

INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('arthur@gmail.com', 'senha123', 'arthur', 2, 1);
INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('joao@gmail.com', 'senha123', 'joao', 1, 2);
INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('maria@gmail.com', 'senha123', 'maria', 1, 3);
INSERT INTO tb_user (email, password, name, role_id, city_id) VALUES ('marta@gmail.com', 'senha123', 'marta', 1, 4);

INSERT INTO tb_event (name, addres, city_id) VALUES ('CIA', 'Centro Park', 4);
INSERT INTO tb_event (name, addres, city_id) VALUES ('OpenArq', 'Casteli', 1);
INSERT INTO tb_event (name, addres, city_id) VALUES ('The One', 'London', 1);
INSERT INTO tb_event (name, addres, city_id) VALUES ('Balada da Barra', 'Barra da tijuca', 2);
INSERT INTO tb_event (name, addres, city_id) VALUES ('Calvin Harris', 'Morumbi', 3);

INSERT INTO tb_ticket (price, user_id, event_id) VALUES (300.0, 1, 1);
INSERT INTO tb_ticket (price, user_id, event_id) VALUES (550.0, 2, 1);
INSERT INTO tb_ticket (price, user_id, event_id) VALUES (300.0, 3, 1);