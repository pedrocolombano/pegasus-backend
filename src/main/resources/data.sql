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
