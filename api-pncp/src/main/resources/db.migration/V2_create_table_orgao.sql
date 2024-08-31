-- Tabela Orgãos
CREATE TABLE IF NOT EXISTS orgao (

    id BIGSERIAL NOT NULL, 
    nome VARCHAR(255) NOT NULL, 
    cnpj VARCHAR(255) UNIQUE NOT NULL,
    CONSTRAINT pk_id_in_orgao PRIMARY KEY (id)

);


