insert into tbg_usuario (nome, email, senha) values ('VINCIUS GOMES DA SILVA SOUZA AGUIAR', 'VINIGOMES47@GMAIL.COM', 'e10adc3949ba59abbe56e057f20f883e');
insert into tbg_usuario (nome, email, senha) values ('OTHER USER', 'OTHER_USER@BOL.COM.BR', 'e10adc3949ba59abbe56e057f20f883e');
insert into tbg_usuario (nome, email, senha) values ('USER', 'USER_USER@OUTLOOK.COM.BR', 'e10adc3949ba59abbe56e057f20f883e');

insert into tbg_telefone (ddd, numero, tipo) values (81, '995558813', 'P'); 
insert into tbg_telefone (ddd, numero, tipo) values (81, '999009987', 'P'); 
insert into tbg_telefone (ddd, numero, tipo) values (11, '30336578', 'R');

insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where email = 'VINIGOMES47@GMAIL.COM'), (select id from tbg_telefone where numero = '995558813'));
insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where email = 'OTHER_USER@BOL.COM.BR'), (select id from tbg_telefone where numero = '999009987'));
insert into tbg_usuario_telefones (usuario_id, telefone_id) values ((select id from tbg_usuario where email = 'USER_USER@OUTLOOK.COM.BR'), (select id from tbg_telefone where numero = '30336578'));
