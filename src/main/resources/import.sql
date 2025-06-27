-- Passo 1: Criar endereço relacionado
INSERT INTO estado (id, nome) VALUES (1, 'Bahia');
INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Salvador', 1); 

INSERT INTO bairro (id, nome, cidade_id) VALUES (1, 'Centro', 1);
INSERT INTO bairro (id, nome, cidade_id) VALUES (2, 'Brotas', 1);

INSERT INTO endereco (id, bairro_id) VALUES (1, 1);
INSERT INTO endereco (id, bairro_id) VALUES (2, 2);

-- Passo 2: Inserir informações da unidade
INSERT INTO informacoes_unidade (nome, imagemURL, telefone, endereco_id) VALUES ('Unidade Salvador', '/imagem.png', '(71) 99999-9999', 1);

INSERT INTO informacoes_unidade (nome, imagemURL, telefone, endereco_id) VALUES ('Unidade Brotas', 'https://via.placeholder.com/150', '(71) 99899-9777', 2);

-- Passo 3: Criar unidades com FK funcionando
INSERT INTO unidade (status, informacoes_unidade_id) VALUES ('CHEIO', 1);
INSERT INTO unidade (status, informacoes_unidade_id) VALUES ('CHEIO', 2);
