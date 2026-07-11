-- Passo 1: Criar endereço relacionado
INSERT INTO estado (id, nome) VALUES (1, 'Bahia');
INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Salvador', 1); 

INSERT INTO bairro (id, nome, cidade_id) VALUES (1, 'Centro', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (2, 'Brotas', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (3, 'Itaigara', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (4, 'Barra', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (5, 'Piata', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (6, 'Sussuarana', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (7, 'Cabula', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (8, 'Pituba', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (9, 'Ondina', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (10, 'Rio Vermelho', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (11, 'Patamares', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (12, 'Boca do Rio', 1);



INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (1, 1, '40000-000', '123', 'Rua A');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (2, 2, '40000-001', '456', 'Rua B');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (3, 3, '40000-002', '789', 'Rua C');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (4, 4, '40000-003', '987', 'Rua D');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (5, 5, '40000-003', '654', 'Rua G');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (6, 6, '40000-003', '321', 'Rua F');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (7, 7, '40000-004', '111', 'Rua H');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (8, 8, '40000-005', '222', 'Rua I');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (9, 9, '40000-006', '333', 'Rua J');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (10, 10, '40000-007', '444', 'Rua K');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (11, 11, '40000-008', '555', 'Rua L');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (12, 12, '40000-009', '666', 'Rua M');


-- Passo 2: Criar unidades com FK funcionando
INSERT INTO unidade (status) VALUES ('SEM_INFORMACAO');
INSERT INTO unidade (status) VALUES ('SEM_INFORMACAO');
INSERT INTO unidade (status) VALUES ('POUCO_VAZIO');
INSERT INTO unidade (status) VALUES ('MODERADO');
INSERT INTO unidade (status) VALUES ('MUITO_CHEIO');
INSERT INTO unidade (status) VALUES ('CHEIO');
INSERT INTO unidade (status) VALUES ('MUITO_CHEIO');
INSERT INTO unidade (status) VALUES ('MUITO_CHEIO');
INSERT INTO unidade (status) VALUES ('POUCO_VAZIO');
INSERT INTO unidade (status) VALUES ('MODERADO');
INSERT INTO unidade (status) VALUES ('POUCO_VAZIO');
INSERT INTO unidade (status) VALUES ('VAZIO');


-- Passo 3: Inserir informações da unidade
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (2,'Unidade Salvador', 'https://midias.correio24horas.com.br/2023/03/09/-1153319.jpg', -12.95, -38.45, '(71) 99999-9999', 1, 1, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (5,'Unidade Brotas', 'https://midias.correio24horas.com.br/2023/03/09/-1153319.jpg', -12.97, -38.50, '(71) 99899-9777', 2, 2, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (4,'Unidade Itaigara', 'https://s3saude.org.br/wp-content/uploads/2025/06/img_974FDB696CD079D8822731280A6CC9F0C7A3CC53_420x870-1-960x720.jpeg', -12.99, -38.47, '(71) 98765-4321', 3, 3, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (4,'Unidade Barra', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPcmfMmMjMLtjMi0kUgUik5juuX5zbymeqcQ&s', -13.00, -38.52, '(71) 91234-5678', 4, 4, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (3,'Unidade Piatã', 'https://piatafm.com.br/wp-content/uploads/2025/04/wp-header-logo-798.png', -12.95, -38.39, '(71) 98787-8787', 5, 5, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (4,'Unidade Sussuarana', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGxwYR5d-LfFI1R-ZBVNpbrt9f0m7PSgPcHQ&s', -12.97, -38.46, '(71) 98989-8989', 6, 6, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (5,'Unidade Cabula', 'https://midias.correio24horas.com.br/2023/03/09/-1153319.jpg', -12.95, -38.46, '(71) 99666-6666', 7, 7, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (3,'Unidade Pituba', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPcmfMmMjMLtjMi0kUgUik5juuX5zbymeqcQ&s', -13.00, -38.45, '(71) 99555-5555', 8, 8, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (4,'Unidade Ondina', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGxwYR5d-LfFI1R-ZBVNpbrt9f0m7PSgPcHQ&s', -13.01, -38.50, '(71) 99444-4444', 9, 9, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (5,'Unidade Rio Vermelho', 'https://piatafm.com.br/wp-content/uploads/2025/04/wp-header-logo-798.png', -13.01, -38.48, '(71) 99333-3333', 10, 10, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (4,'Unidade Patamares', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGxwYR5d-LfFI1R-ZBVNpbrt9f0m7PSgPcHQ&s', -12.96, -38.39, '(71) 99222-2222', 11, 11, now());
INSERT INTO informacoes_unidade (nota, nome, imagemURL, lat, lng, telefone, endereco_id, unidade_id, ultima_atualizacao) VALUES (3,'Unidade Boca do Rio', 'https://midias.correio24horas.com.br/2023/03/09/-1153319.jpg', -12.97, -38.42, '(71) 99111-1111', 12, 12, now());


INSERT INTO ranking (id) VALUES (1);

INSERT INTO usuario ( email, nome, senha) VALUES ( 'alana@gmail.com', 'Alana', '1234');

INSERT INTO cliente (contribuicoes, id, ranking_id, cpf, endereco, telefone) VALUES (0, 1, 1, '12345678901', 'Rua A, 123', '(71) 99999-9999');

INSERT INTO usuario ( email, nome, senha) VALUES ( 'rafael@gmail.com', 'Rafael', '12345');

INSERT INTO funcionario (id, cpf, endereco, telefone) VALUES (2, '12345678901', 'Rua A, 123', '(71) 99999-9999');

INSERT INTO comentario (cliente_id, data_hora, id, texto, informacoes_unidade_id) VALUES (1, '2023-10-01', 1, 'Ótimo atendimento!', 1);

INSERT INTO avaliacao (nota, cliente_id, informacoes_unidade_id, id, data_hora) VALUES (5, 1, 1, 1, '2023-10-01');


