create table users (
    id bigint not null auto_increment,
    nome varchar(255),
    email varchar(255),
    senha varchar(255),
    primary key(id)
);