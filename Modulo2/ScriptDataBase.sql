GRANT CONNECT TO PETSHOP;

GRANT CONNECT, RESOURCE, DBA TO PETSHOP;

GRANT CREATE SESSION TO PETSHOP;

GRANT DBA TO PETSHOP;

GRANT CREATE VIEW, CREATE PROCEDURE, CREATE SEQUENCE TO PETSHOP;

GRANT UNLIMITED TABLESPACE TO PETSHOP;
GRANT CREATE MATERIALIZED VIEW TO PETSHOP;
GRANT CREATE TABLE TO PETSHOP;
GRANT GLOBAL QUERY REWRITE TO PETSHOP;
GRANT SELECT ANY TABLE TO PETSHOP;

CREATE TABLE CLIENTE(
ID_CLIENTE NUMBER (11) NOT NULL,
NOME VARCHAR(100) NOT NULL,
QUANTIDADE_PEDIDOS NUMBER(3) NOT NULL,
PRIMARY KEY (ID_CLIENTE)
);

CREATE TABLE LOGIN(
ID_CLIENTE NUMBER (11) NOT NULL,
USUARIO VARCHAR(50) UNIQUE NOT NULL,
SENHA NUMBER(38) NOT NULL,
CONSTRAINT PK_LOGIN_ID_LOGIN PRIMARY KEY (USUARIO, ID_CLIENTE),
CONSTRAINT FK_CLIENTE_ID_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE (ID_CLIENTE)
);

CREATE TABLE PETSHOP.CONTATO(
ID_CONTATO NUMBER (11) NOT NULL,
ID_CLIENTE NUMBER (11) NOT NULL,
TELEFONE NUMBER (12) NOT NULL,
DESCRICAO VARCHAR (100) NOT NULL,
CONSTRAINT PK_CONTATO_ID_CONTATO PRIMARY KEY (ID_CONTATO, ID_CLIENTE),
CONSTRAINT FK_CONTATO_ID_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE (ID_CLIENTE)
);

CREATE TABLE PETSHOP.ENDERECO(
ID_ENDERECO NUMBER (11) NOT NULL,
ID_CLIENTE NUMBER (11) NOT NULL,
CEP CHAR (9) NOT NULL,
LOGRADOURO VARCHAR (255) NOT NULL,
CIDADE VARCHAR (100) NOT NULL,
BAIRRO VARCHAR (100) NOT NULL,
NUMERO NUMBER (8) NOT NULL,
COMPLEMENTO VARCHAR (100) NOT NULL,
CONSTRAINT PK_ENDERECO_ID_ENDERECO PRIMARY KEY (ID_ENDERECO, ID_CLIENTE),
CONSTRAINT FK_ENDERECO_ID_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE (ID_CLIENTE)
);

CREATE TABLE PETSHOP.ANIMAL(
ID_ANIMAL NUMBER (11) NOT NULL,
ID_CLIENTE NUMBER (11) NOT NULL,
NOME VARCHAR (50) NOT NULL,
TIPO VARCHAR(10) NOT NULL,
RACA VARCHAR (50) NOT NULL,
PELAGEM NUMBER(1) NOT NULL,
PORTE NUMBER(1) NOT NULL,
IDADE NUMBER(10) 
CONSTRAINT PK_ANIMAL_ID_ANIMAL PRIMARY KEY (ID_ANIMAL, ID_CLIENTE),
CONSTRAINT FK_ANIMAL_ID_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE (ID_CLIENTE)
);

CREATE TABLE PETSHOP.PEDIDO(
ID_PEDIDO NUMBER(11) NOT NULL,
ID_CLIENTE NUMBER (11) NOT NULL,
ID_ANIMAL NUMBER(11) NOT NULL,
VALOR NUMBER (10) NOT NULL,
DESCRICAO VARCHAR(30) NOT NULL,
CONSTRAINT PK_PEDIDO_ID_PEDIDO PRIMARY KEY (ID_PEDIDO),
CONSTRAINT FK_PEDIDO_ID_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE),
CONSTRAINT FK_PEDIDO_ID_ANIMAL FOREIGN KEY (ID_ANIMAL, ID_CLIENTE) REFERENCES ANIMAL(ID_ANIMAL, ID_CLIENTE)
);

CREATE SEQUENCE SEQ_ID_CLIENTE
START WITH 1
INCREMENT BY 1
NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_ID_ANIMAL
START WITH 1
INCREMENT BY 1
NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_ID_ENDERECO
START WITH 1
INCREMENT BY 1
NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_ID_CONTATO
START WITH 1
INCREMENT BY 1
NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_ID_LOGIN
START WITH 1
INCREMENT BY 1
NOCYCLE NOCACHE;

CREATE SEQUENCE SEQ_ID_PEDIDO
START WITH 1
INCREMENT BY 1
NOCYCLE NOCACHE;