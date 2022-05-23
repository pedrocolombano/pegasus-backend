INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_MANAGER');
INSERT INTO tb_role (authority) VALUES ('ROLE_MODERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');


INSERT INTO tb_user (first_name, last_name, document, email, phone, password, birth_date, gender) VALUES (
'Alex', 'Green', '98093124071', 'alex@gmail.com', '11 91234-5678', '$2a$04$JGb96Dqos2v1T8kIG5TdWOv0JoBXySk/1GObr3dFYtfcsjjQoDMXK', '2000-02-15', 'MALE');

INSERT INTO tb_user (first_name, last_name, document, email, phone, password, birth_date, gender) VALUES (
'Maria', 'Brown', '50842599002', 'maria@gmail.com', '11 94321-8765', '$2a$04$JGb96Dqos2v1T8kIG5TdWOv0JoBXySk/1GObr3dFYtfcsjjQoDMXK', '1997-01-30', 'FEMALE');

INSERT INTO tb_user (first_name, last_name, document, email, phone, password, birth_date, gender) VALUES (
'Jhon', 'Marston', '14930525004', 'jhon@gmail.com', '11 91111-1111', '$2a$04$JGb96Dqos2v1T8kIG5TdWOv0JoBXySk/1GObr3dFYtfcsjjQoDMXK', '2001-01-30', 'MALE');

INSERT INTO tb_user (first_name, last_name, document, email, phone, password, birth_date, gender) VALUES (
'Arthur', 'Morgan', '72294176006', 'arthur@gmail.com', '11 92222-2222', '$2a$04$JGb96Dqos2v1T8kIG5TdWOv0JoBXySk/1GObr3dFYtfcsjjQoDMXK', '1991-03-17', 'MALE');


INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 4);


INSERT INTO tb_address (street, number, complement, neighborhood, postal_code, city, state, user_id) VALUES (
'Rua Passo Fundo', 123, null, 'Sudoeste', '85507-410', 'Pato Branco', 'Paraná', 4);

INSERT INTO tb_address (street, number, complement, neighborhood, postal_code, city, state, user_id) VALUES (
'Rua Alpes', 123, null, 'Jardim Petrópolis', '74460-430', 'Goiânia', 'Goiás', 4);


INSERT INTO tb_category (name) VALUES ('Brinquedos');
INSERT INTO tb_category (name) VALUES ('Roupas');
INSERT INTO tb_category (name) VALUES ('Ração');
INSERT INTO tb_category (name) VALUES ('Cachorros');
INSERT INTO tb_category (name) VALUES ('Gatos');


INSERT INTO tb_product (name, price, description, image_url, created_at) VALUES (
'Macaco Chalesco Brinquedo Para Cães Pelúcia Marrom', 40, 'Ideal para os pets de todos os portes e idades',
'https://static.petz.com.br/fotos/1531491431642.jpg', NOW());

INSERT INTO tb_product (name, price, description, image_url, created_at) VALUES (
'Brinquedo de Pelúcia Chalesco Crocodilo', 25, 'Divertido e criativo',
'https://static.petz.com.br/fotos/1457992186939.jpg', NOW());

INSERT INTO tb_product (name, price, description, image_url, created_at) VALUES (
'Meia American Pets para Cães e Gatos Tamanho M', 32.99, 'Ótimo para manter as patas quentinhas',
'https://static.petz.com.br/fotos/1623255561001.jpg', NOW());

INSERT INTO tb_product (name, price, description, image_url, created_at) VALUES (
'Casaco Bonito pra Cachorro Maxi Marinho para Cães', 99.99, 'Toda em soft antialérgico',
'https://static.petz.com.br/fotos/1646770429643.jpg', NOW());

INSERT INTO tb_product (name, price, description, image_url, created_at) VALUES (
'Camiseta Piticas Homem Aranha', 49.99, 'Produto original, licenciado e exclusivo Piticas',
'https://static.petz.com.br/fotos/1600801574668.jpg', NOW());

INSERT INTO tb_product (name, price, description, image_url, created_at) VALUES (
'Ração GranPlus Choice para Gatos Adultos Sabor Frango e Carne 10,1kg', 132.50, 'Feita para os paladares mais exigentes',
'https://static.petz.com.br/fotos/1603901892437.jpg', NOW());

INSERT INTO tb_product (name, price, description, image_url, created_at) VALUES (
'Ração Golden Special Sabor Frango e Carne para Cães Adultos', 181.99, 'Fórmula rica em nutrientes e minerais',
'https://static.petz.com.br/fotos/1615377293554.jpg', NOW());


INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 1);

INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 2);

INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 3);

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 4);

INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 5);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 5);
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 5);


INSERT INTO tb_order (client_id, status, moment) VALUES (3, 'SHIPPED', NOW());
INSERT INTO tb_order (client_id, status, moment) VALUES (3, 'DELIVERED', NOW());
INSERT INTO tb_order (client_id, status, moment) VALUES (3, 'CANCELED', NOW());

INSERT INTO tb_order (client_id, status, moment) VALUES (4, 'PAID', NOW());


INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (1, 1, 5, 40);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (2, 2, 1, 25);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (2, 3, 3, 32.99);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (3, 5, 2, 49.99);

INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (4, 5, 2, 49.99);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (4, 6, 1, 132.50);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (4, 7, 3, 181.99);

