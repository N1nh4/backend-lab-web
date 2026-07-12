-- Limpar tudo
TRUNCATE TABLE avaliacao CASCADE;
TRUNCATE TABLE comentario CASCADE;
TRUNCATE TABLE lotacao CASCADE;
TRUNCATE TABLE cliente CASCADE;
TRUNCATE TABLE funcionario CASCADE;
TRUNCATE TABLE usuario CASCADE;
TRUNCATE TABLE informacoes_unidade CASCADE;
TRUNCATE TABLE unidade CASCADE;
TRUNCATE TABLE endereco CASCADE;
TRUNCATE TABLE bairro CASCADE;
TRUNCATE TABLE cidade CASCADE;
TRUNCATE TABLE estado CASCADE;
TRUNCATE TABLE ranking CASCADE;

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
