-- Passo 1: Criar endereço relacionado
INSERT INTO estado (id, nome) VALUES (1, 'Bahia');
INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Salvador', 1); 

INSERT INTO bairro (id, nome, cidade_id) VALUES (1, 'Centro', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (2, 'Brotas', 1);

INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (1, 1, '40000-000', '123', 'Rua A');
INSERT INTO endereco (id, bairro_id, cep, numero, rua) VALUES (2, 2, '40000-001', '456', 'Rua B');

-- Passo 2: Inserir informações da unidade
INSERT INTO informacoes_unidade (nota, nome, imagemURL, telefone, endereco_id) VALUES (2,'Unidade Salvador', '/imagem.png', '(71) 99999-9999', 1);

INSERT INTO informacoes_unidade (nota, nome, imagemURL, telefone, endereco_id) VALUES (5,'Unidade Brotas', 'https://via.placeholder.com/150', '(71) 99899-9777', 2);

-- Passo 3: Criar unidades com FK funcionando
INSERT INTO unidade (status, informacoes_unidade_id) VALUES ('MUITO_CHEIO', 1);
INSERT INTO unidade (status, informacoes_unidade_id) VALUES ('CHEIO', 2);

INSERT INTO ranking (id) VALUES (1);

INSERT INTO usuario (id, data_nascimento, email, nome, senha) VALUES (1, '2003-11-11', '6M1wN@example.com', 'Alana', '1234');

INSERT INTO cliente (contribuicoes, id, ranking_id, cpf, endereco, telefone) VALUES (0, 1, 1, '12345678901', 'Rua A, 123', '(71) 99999-9999');

INSERT INTO comentario (cliente_id, data_hora, id, unidade_id, texto) VALUES (1, '2023-10-01', 1, 1, 'Ótimo atendimento!');

INSERT INTO avaliacao (nota, cliente_id, comentario_id, id) VALUES (5, 1, 1, 1);