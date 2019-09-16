insert into tbg_telefone (ddd, numero, tipo) values (81, '998882555', 'P');
insert into tbg_telefone (ddd, numero, tipo) values (81, '30303030', 'H');
insert into tbg_telefone (ddd, numero, tipo) values (81, '40404040', 'B');
insert into tbg_telefone (ddd, numero, tipo) values (81, '80808080', 'Other');
insert into tbg_telefone (ddd, numero, tipo) values (81, '999009987', 'P'); 
insert into tbg_telefone (ddd, numero, tipo) values (11, '30336578', 'H');

insert into tbg_endereco (endereco, complemento, cep, bairro, cidade, estado) values ('RUA JABOATÃO DOS GUARARAPES, 256', 'BLOCO 1074, MÓDULO 332, APARTAMENTO 565', '50550550', 'JABOATÃO VELHO', 'JABOATÃO DOS GUARARAPES', 'PERNAMBUCO');
insert into tbg_endereco (endereco, complemento, cep, bairro, cidade, estado) values ('RUA PIEDADE, 221', 'TRAVESSA 4', '85850850', 'PIEDADE', 'RECIFE', 'PERNAMBUCO');
insert into tbg_endereco (endereco, complemento, cep, bairro, cidade, estado) values ('RUA AREIAS, 446', '', '95950950', 'AREIAS', 'RECIFE', 'PERNAMBUCO');

insert into tbg_usuario (nome, email, senha, data_registro, genero, estado_civil, data_nascimento, endereco_id) values ('VINCIUS GOMES DA SILVA SOUZA AGUIAR', 'VINIGOMES47@GMAIL.COM', 'e10adc3949ba59abbe56e057f20f883e', parsedatetime('20-03-2019', 'dd-MM-yyyy'), 'M', 'Not Married', parsedatetime('22-11-1989', 'dd-MM-yyyy'), (select id from tbg_endereco where upper(endereco) = 'RUA AREIAS, 446'));
insert into tbg_usuario (nome, email, senha, data_registro, genero, estado_civil, data_nascimento, endereco_id) values ('OTHER USER', 'OTHER_USER@BOL.COM.BR', 'e10adc3949ba59abbe56e057f20f883e', parsedatetime('15-09-2019', 'dd-MM-yyyy'), 'F', 'Married', parsedatetime('20-05-1959', 'dd-MM-yyyy'), (select id from tbg_endereco where upper(endereco) = 'RUA JABOATÃO DOS GUARARAPES, 256'));
insert into tbg_usuario (nome, email, senha, data_registro, genero, estado_civil, data_nascimento, endereco_id) values ('USER', 'USER_USER@OUTLOOK.COM.BR', 'e10adc3949ba59abbe56e057f20f883e', parsedatetime('03-005-2012', 'dd-MM-yyyy'), 'Other', 'Divorced', parsedatetime('19-03-1974', 'dd-MM-yyyy'), (select id from tbg_endereco where upper(endereco) = 'RUA PIEDADE, 221'));

insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where upper(email) = 'VINIGOMES47@GMAIL.COM'), (select id from tbg_telefone where numero = '998882555'));
insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where upper(email) = 'VINIGOMES47@GMAIL.COM'), (select id from tbg_telefone where numero = '30303030'));
insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where upper(email) = 'VINIGOMES47@GMAIL.COM'), (select id from tbg_telefone where numero = '40404040'));
insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where upper(email) = 'VINIGOMES47@GMAIL.COM'), (select id from tbg_telefone where numero = '80808080'));
insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where upper(email) = 'OTHER_USER@BOL.COM.BR'), (select id from tbg_telefone where numero = '999009987'));
insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where upper(email) = 'USER_USER@OUTLOOK.COM.BR'), (select id from tbg_telefone where numero = '30336578'));
