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
'Rua Passo Fundo', 123, null, 'Sudoeste', '85507-410', 'Pato Branco', 'Paraná', 3);

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


INSERT INTO tb_order (client_id, address_id, status, moment) VALUES (3, 1, 'SHIPPED', NOW());
INSERT INTO tb_order (client_id, address_id, status, moment) VALUES (3, 1, 'DELIVERED', NOW());
INSERT INTO tb_order (client_id, address_id, status, moment) VALUES (3, 1, 'CANCELED', NOW());

INSERT INTO tb_order (client_id, address_id, status, moment) VALUES (4, 2, 'PAID', NOW());


INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (1, 1, 5, 40);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (2, 2, 1, 25);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (2, 3, 3, 32.99);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (3, 5, 2, 49.99);

INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (4, 5, 2, 49.99);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (4, 6, 1, 132.50);
INSERT INTO tb_order_item (order_id, product_id, quantity, price) VALUES (4, 7, 3, 181.99);


INSERT INTO tb_theme(name) VALUES ('Cachorros');
INSERT INTO tb_theme(name) VALUES ('Gatos');
INSERT INTO tb_theme(name) VALUES ('Aves');
INSERT INTO tb_theme(name) VALUES ('Curiosidades');
INSERT INTO tb_theme(name) VALUES ('Animais Silvestres');
INSERT INTO tb_theme(name) VALUES ('Dicas');
INSERT INTO tb_theme(name) VALUES ('Alimentação');


INSERT INTO tb_post(title, summary, theme_id, author_id, created_at) VALUES ('Existe diferença entre boi e touro?',
'Você já entrou em um debate sobre a diferença entre boi e touro? Um questionamento muito comum envolvendo os dois animais é se eles são a mesma coisa ou se apenas pertencem à mesma espécie, mas são animais diferentes. Afinal, qual será a resposta certa?', 4, 3, NOW());

INSERT INTO tb_post_image(post_id, image) VALUES (1, 'https://www.petz.com.br/blog/wp-content/uploads/2022/05/diferenca-entre-boi-e-touro-interna-1.webp');
INSERT INTO tb_post_image(post_id, image) VALUES (1, 'https://www.petz.com.br/blog/wp-content/uploads/2022/05/diferenca-entre-boi-e-touro-topo.webp');
INSERT INTO tb_post_image(post_id, image) VALUES (1, 'https://www.petz.com.br/blog/wp-content/uploads/2022/05/diferenca-entre-boi-e-touro-interna-2.webp');


INSERT INTO tb_article(subtitle, post_id) VALUES ('Por que tem gente que confunde boi e touro?', 1);
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (1, 'A confusão sobre boi e touro vem da nomenclatura. Normalmente, seres que possuem mais de um nome popular tendem a ser chamados por algum apelido e não por nomes tão simples, como os dois que citamos. Até quando são regionalismos é mais fácil perceber!');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (1, 'Nesse caso, boi e touro estão entre os animais terrestres mais conhecidos do mundo e conferem uma só espécie. Na verdade, o que os separa de serem chamados de uma ou outra forma é a capacidade de procriar.');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (1, 'Ambos os nomes se referem ao macho da vaca, no entanto, o touro corresponde a um animal que ainda tem capacidade reprodutiva. Enquanto isso, o boi é aquele que já foi castrado. Ou seja, um boi já pode ter sido um touro, e um touro pode vir a se tornar um boi. Bem simples, não é?');

INSERT INTO tb_article(subtitle, post_id) VALUES ('Diferença entre touro e boi', 1);
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (2, 'Para além da capacidade reprodutiva, esses dois animais têm finalidades diferentes e, consequentemente, hábitos e criação distintos. Essa é uma das formas de identificar a diferença entre boi e touro no campo: a partir das suas funções agrícolas.');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (2, 'O touro é tratado para fins de procriação, sendo criado como gado bovino. Nesse aspecto, ele fica no campo para garantir que vacas férteis sejam inseminadas e possam aumentar o rebanho. Há quem invista pesado na qualidade genética desses animais para melhorar a qualidade das espécies futuras.');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (2, 'Há profissionais e fazendeiros que investem muito nessa qualidade genética e vendem o sêmen de touros para aumentar o rebanho. Dependendo do histórico do animal, a tentativa de reprodução pode custar milhares de reais aos interessados.');

INSERT INTO tb_article(subtitle, post_id) VALUES ('E quanto ao boi?', 1);
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (3, 'Como mencionamos, a diferença entre o boi e o touro está, principalmente, nas suas funções. No pasto, o boi tem duas criações específicas: ou ele é mantido na terra para aragem, ou é criado para ser gado de corte.');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (3, 'Normalmente, essa escolha entre ser boi e touro é feita nos primeiros meses de vida. Isso depende do foco do produtor rural ou de questões genéticas. Se o criador já sabe que o animal tem um bom histórico e vem de um touro com forte potencial reprodutivo, é interessante mantê-lo nessa função, por exemplo.');

INSERT INTO tb_article(subtitle, post_id) VALUES ('Quais são as principais características do gado?', 1);
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (4, 'O boi e o touro são mamíferos e têm sua alimentação feita por capim, feno, pastagem, cana e rações específicas, que normalmente são feitas à base de milho e soja. Isso quer dizer que eles são animais herbívoros.');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (4, 'Essa é uma das espécies de ruminantes mais conhecidas, então, o processo de alimentação dela é diferenciado: ela come, regurgita e segue mastigando pelo resto do dia.');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (4, 'Um fato curioso é que esses animais podem passar cerca de seis horas comendo e outras oito horas somente regurgitando. Assim, são 14 horas do dia mastigando. Já imaginou?');

INSERT INTO tb_article(subtitle, post_id) VALUES ('Como o touro/boi vive?', 1);
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (5, 'Quem procura saber qual a diferença entre boi e touro também tem curiosidade sobre o local de origem e onde o gado vive. Nos primórdios, esses animais vinham dos continentes europeu e asiático. Ao longo dos anos, porém, praticamente todos os países têm essas espécies para criação.');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (5, 'Atualmente, o Brasil é um dos países com maior número de boi e touro no mundo. A espécie foi domesticada pelo homem há anos e hoje consegue fornecer carne e laticínio para a alimentação. ');
INSERT INTO tb_article_paragraph(article_id, paragraph) VALUES (5, 'No entanto, a relação dessa espécie na Índia é diferente. Por lá, a vaca é um animal sagrado e não deve ser consumido por ser um presente da divindade Laksmi. A explicação está justamente em tudo o que a fêmea pode oferecer: leite, queijo, manteiga e coalhada.');