drop table tbg_endereco if exists;
drop table tbg_telefone if exists;
drop table tbg_usuario if exists;
drop table tbg_usuario_telefones if exists;

create table tbg_endereco (
   id bigint generated by default as identity,
	complemento varchar(255),
	endereco varchar(255) not null,
	cidade varchar(255) not null,
	bairro varchar(255) not null,
	cep varchar(255) not null,
	estado varchar(255) not null,
	primary key (id)
);

create table tbg_telefone (
   id bigint generated by default as identity,
	ddd integer not null,
	numero varchar(255) not null,
	tipo varchar(255) not null,
	primary key (id)
);

create table tbg_usuario (
   id bigint generated by default as identity,
	data_nascimento timestamp,
	email varchar(255) not null,
	genero varchar(255),
	estado_civil varchar(255),
	nome varchar(255) not null,
	senha varchar(255) not null,
	data_registro date,
	endereco_id bigint,
	primary key (id)
);

create table tbg_usuario_telefones (
   usuario_id bigint not null,
	telefone_id bigint not null
);

alter table tbg_usuario 
   drop constraint if exists UK_6kcf92crkjj9gvjc896sjx6m3;

alter table tbg_usuario 
   add constraint UK_6kcf92crkjj9gvjc896sjx6m3 unique (email);

alter table tbg_usuario_telefones 
   drop constraint if exists UK_rgpwtsvklchbthm8wyx5v4tye;

alter table tbg_usuario_telefones 
   add constraint UK_rgpwtsvklchbthm8wyx5v4tye unique (telefone_id);

alter table tbg_usuario 
   add constraint FKh9vk00g65t68vi22u4qppxgti 
   foreign key (endereco_id) 
   references tbg_endereco;

alter table tbg_usuario_telefones 
   add constraint FKaukgtoccp3o4jq6jrj5akhc20 
   foreign key (telefone_id) 
   references tbg_telefone;

alter table tbg_usuario_telefones 
   add constraint FKq6do1aegcmtyrxj845yhrum16 
   foreign key (usuario_id) 
   references tbg_usuario;

insert into tbg_telefone (id, ddd, numero, tipo) values (1, 81, '998882555', 'P');
insert into tbg_telefone (id, ddd, numero, tipo) values (2, 81, '30303030', 'H');
insert into tbg_telefone (id, ddd, numero, tipo) values (3, 81, '40404040', 'B');
insert into tbg_telefone (id, ddd, numero, tipo) values (4, 81, '80808080', 'Other');
insert into tbg_telefone (id, ddd, numero, tipo) values (5, 11, '30336578', 'H');

insert into tbg_endereco (id, endereco, complemento, cep, bairro, cidade, estado) 
	values (1, 'RUA JABOATÃO DOS GUARARAPES, 256', 'BLOCO 1074, MÓDULO 332, APARTAMENTO 565', '50550550', 'JABOATÃO VELHO', 'JABOATÃO DOS GUARARAPES', 'PERNAMBUCO');
insert into tbg_endereco (id, endereco, complemento, cep, bairro, cidade, estado) 
	values (2, 'RUA PIEDADE, 221', 'TRAVESSA 4', '85850850', 'PIEDADE', 'RECIFE', 'PERNAMBUCO');

insert into tbg_usuario (id, nome, email, senha, data_registro, genero, estado_civil, data_nascimento, endereco_id) 
	values (1, 'ADMINISTRADOR', 'ADMINISTRADOR_USER@BOL.COM.BR', 'MTIzNDU2', parsedatetime('15-09-2019', 'dd-MM-yyyy'), 'F', 'Married', parsedatetime('20-05-1959', 'dd-MM-yyyy'), 1);
insert into tbg_usuario (id, nome, email, senha, data_registro, genero, estado_civil, data_nascimento, endereco_id) 
	values (2, 'USER OTHER', 'USER_OTHER@OUTLOOK.COM.BR', 'MTIzNDU2', parsedatetime('03-005-2012', 'dd-MM-yyyy'), 'Other', 'Divorced', parsedatetime('19-03-1974', 'dd-MM-yyyy'), 2);

insert into tbg_usuario_telefones (usuario_id, telefone_id) values (1, 1);
insert into tbg_usuario_telefones (usuario_id, telefone_id) values (1, 2);
insert into tbg_usuario_telefones (usuario_id, telefone_id) values (1, 3);
insert into tbg_usuario_telefones (usuario_id, telefone_id) values (1, 4);
insert into tbg_usuario_telefones (usuario_id, telefone_id) values (2, 5);
