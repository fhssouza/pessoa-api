INSERT INTO estado (nome) VALUES ('Pará');
INSERT INTO estado (nome) VALUES ('Amazonas');

INSERT INTO cidade (nome, estado_id) VALUES ('Belém', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 2);

INSERT INTO pessoa (nome, data_nascimento) VALUES ('Maria Silva', '1979-04-26');
INSERT INTO pessoa (nome, data_nascimento) VALUES ('Fábio Souza', '1983-07-01');

INSERT INTO endereco (logradouro, cep, numero, principal, cidade_id, pessoa_id) VALUES ('Av. Almirante Barroso', '66000-000','100', true, '1', '1');
INSERT INTO endereco (logradouro, cep, numero, principal, cidade_id, pessoa_id) VALUES ('Av. Castro Silva', '66000-250','150', false, '2', '1');
INSERT INTO endereco (logradouro, cep, numero, principal, cidade_id, pessoa_id) VALUES ('Av. Julio Cezar', '66000-000', '300', true, '1', '2');
INSERT INTO endereco (logradouro, cep, numero, principal, cidade_id, pessoa_id) VALUES ('Av. Fernando Silva', '66000-251', '800', false, '1', '2');