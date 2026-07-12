-- Limpar tudo (ignora tabelas que nao existem)
TRUNCATE TABLE IF EXISTS avaliacao CASCADE;
TRUNCATE TABLE IF EXISTS comentario CASCADE;
TRUNCATE TABLE IF EXISTS lotacao CASCADE;
TRUNCATE TABLE IF EXISTS cliente CASCADE;
TRUNCATE TABLE IF EXISTS funcionario CASCADE;
TRUNCATE TABLE IF EXISTS usuario CASCADE;
TRUNCATE TABLE IF EXISTS informacoes_unidade CASCADE;
TRUNCATE TABLE IF EXISTS unidade CASCADE;
TRUNCATE TABLE IF EXISTS endereco CASCADE;
TRUNCATE TABLE IF EXISTS bairro CASCADE;
TRUNCATE TABLE IF EXISTS cidade CASCADE;
TRUNCATE TABLE IF EXISTS estado CASCADE;
TRUNCATE TABLE IF EXISTS ranking CASCADE;

-- =============================================
-- ESTADO / CIDADE
-- =============================================
INSERT INTO estado (id, nome) VALUES (1, 'Bahia');
INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Salvador', 1);

-- =============================================
-- DADOS BASE
-- =============================================
INSERT INTO ranking (id) VALUES (1);
INSERT INTO usuario (email, nome, senha) VALUES ('alana@gmail.com', 'Alana', '1234');
INSERT INTO cliente (contribuicoes, id, ranking_id, cpf, endereco, telefone) VALUES (0, 1, 1, '12345678901', 'Rua A, 123', '(71) 99999-9999');
INSERT INTO usuario (email, nome, senha) VALUES ('rafael@gmail.com', 'Rafael', '12345');
INSERT INTO funcionario (id, cpf, endereco, telefone) VALUES (2, '12345678901', 'Rua A, 123', '(71) 99999-9999');
