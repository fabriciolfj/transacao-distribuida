create table pedido (
id bigint  not null auto_increment,
descricao  varchar(100) not null,
transacao   varchar(100) not null,
cliente  varchar(100) not null,
status varchar(20) not null,
valor decimal(14,2) not null,
constraint pk_pedido primary key (id)
)engine=InnoDB default charset=utf8;