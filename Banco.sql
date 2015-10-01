create database mydb;
use mydb;

CREATE TABLE vendedor(
	idvendedor INT(11) NOT NULL AUTO_INCREMENT,
	nome VARCHAR(45) NULL DEFAULT NULL,
	comissao DECIMAL(16,2) NULL DEFAULT NULL,
	PRIMARY KEY (idvendedor)
);

create table equipe(
idequipe integer not null auto_increment,
descricao varchar(45),
primary key (idequipe)
);


create table equipevendedor
(
idequipe_vendedor integer not null auto_increment,


primary key(idequipe_vendedor)
);


create table produto(
idproduto integer not null auto_increment,
valor decimal (16,2),
quantidade integer,
descricao varchar(45),
primary key (idproduto)
);


create table venda 
(
idvenda integer not null auto_increment,
data date,
vendacol varchar(45),
idequipe_vendedor integer,
PRIMARY KEY (idvenda),
CONSTRAINT 
FOREIGN key (idequipe_vendedor)
references equipevendedor (idequipe_vendedor)
);

create table venda_produto
(
idvenda integer,
idproduto integer,
quantidade integer,
valor_pago decimal(16,2),
FOREIGN key (idvenda)
references venda (idvenda),
FOREIGN key (idproduto)
references produto (idproduto)
);


create table meta
(
idmeta integer not null auto_increment,
idequipe integer,
valor_meta decimal(16,2),
data_referencia date,
primary key (idmeta),
FOREIGN key (idequipe)
references equipe (idequipe)
);



