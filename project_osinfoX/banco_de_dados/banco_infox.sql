-- Create by Daniel Alencar
create database dbinfox;

use dbinfox;


create table tbusuarios(
	iduser int primary key,
    usuario varchar(50) not null,
    fone varchar(15),
    login varchar(15) not null unique,
    senha varchar(15) not null
);

describe tbusuarios;

-- Crud 
insert into tbusuarios(iduser,usuario,fone,login,senha)
values (1,'Daniel Alencar','9999-9999','daniel','12345');

insert into tbusuarios(iduser,usuario,fone,login,senha)
values (2,'Aline Oliveira','5555-5959','aline','123');

insert into tbusuarios(iduser,usuario,fone,login,senha)
values (3,'Prefeito Pablo','3333-3339','pablo','345');

-- Visualizar
select * from tbusuarios;

-- a linha abaixo modifica registro da tabelas
-- update por tabela
update tbusuarios set fone='8888-8888' where iduser=2;

-- apagar registro da tabela 

delete from tbusuarios where iduser = 3;

create table tbclientes(
	idcli int primary key auto_increment,
    nomecli varchar(50) not null,
    endecli varchar(100),
    fonecli varchar(50) not null,
    emailcli varchar(50)
    );

describe tbclientes;

insert into tbclientes(nomecli,endecli,fonecli,emailcli)
values('Jairão dos lanche','Q.10 lote 4','3434-33-23','jairoalanchao@gmail.com');

select * from tbclientes;

-- Data e hora do servidor
create table tbos(
	os int primary key auto_increment,
    data_os timestamp default current_timestamp,
	equipamento varchar(50) not null,
    defeito varchar(150) not null,
    servico varchar(150),
    tecnico varchar(50),
    valor decimal(10,2),
    idcli int not null,
    foreign key(idcli)references tbclientes(idcli)
    
);

select * from tbos;  

-- Insert values

insert into tbos(equipamento, defeito, servico, tecnico,valor,idcli)
values('Notebook','Nãoliga','Troca da fonte','Zé',87.50,1);

-- Trazer informações de 2 tablelas

select 
O.os,equipamento,defeito,servico,valor,
C.nomecli,fonecli
from tbos as O
inner join tbclientes as C on (O.idcli = C.idcli);

-- Alter table

alter table tbusuarios add column perfil varchar(20) not null;

-- Drop column table
alter table tbusuarios drop column perfil;

-- Update perfil column

update tbusuarios set perfil='admin' where iduser=1;
update tbusuarios set perfil='user' where iduser=2;
update tbusuarios set perfil='user' where iduser=3;

select * from tbclientes;

delete from tbclientes where idcli=6;

-- use database
use dbinfox;
-- instruction select use like
select * from tbclientes where nomecli like 'J%';

-- select table
select * from tbclientes;

-- Consult avanced

select idcli,nomecli,fonecli from tbclientes where nomecli like 'jo%';

-- Line create name apelido aos campos

select idcli as ID, nomecli as Nome, fonecli as Telefone from tbclientes where nomecli like 'a%';

describe tbos;

-- Alter table data _ OS after
alter table tbos add tipo varchar(15) not null after data_os;

-- Alter table  situation
alter table tbos add situacao varchar(20) not null after tipo;

select * from tbos;

-- Select and Order name all clients
select * from tbclientes order by nomecli;

-- Inner join
-- Variable OSER table OS and CLI variable table clientes

select
OSER.os,data_os,tipo,situacao,equipamento,valor,
CLI.nomecli,fonecli
from tbos as OSER
inner join tbclientes as CLI 
on (CLI.idcli= OSER.idcli);