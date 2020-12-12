create table transacao(
 id bigint not null auto_increment,
 status varchar(50),
 transaction_id varchar(100),
 create_transacao date,
 update_transacao date,
 primary key (id)
)engine=InnoDB default charset=utf8;