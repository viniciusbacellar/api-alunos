INSERT INTO tb_endereco(bairro, cidade, complemento, estado, logradouro) VALUES ('Jardim America', 'Rio de Janeiro', 'Casa', 'RJ', 'Rua Atílio Parim');

INSERT INTO tb_escola(nome, endereco_id) VALUES ('São Matheus', 1);

INSERT INTO tb_turma(capacidade, nome, escola_id) VALUES (20, 'A502', 1);
INSERT INTO tb_turma(capacidade, nome, escola_id) VALUES (20, 'B501', 1);

INSERT INTO tb_alunos(data_de_nascimento, nome, turma_id) VALUES ('2005-01-27', 'José Ribeiro', 1);
INSERT INTO tb_alunos(data_de_nascimento, nome, turma_id) VALUES ('2004-06-03', 'Luiz Roberto', 1);
INSERT INTO tb_alunos(data_de_nascimento, nome, turma_id) VALUES ('2005-08-15', 'Julia Silva', 2);

