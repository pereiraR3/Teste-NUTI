-- Tabela Contratos
CREATE TABLE IF NOT EXISTS contratos (

    id BIGSERIAL NOT NULL, 
    id_orgao BIGINT NOT NULL 
    data_pesquisa CURRENT_TIMESTAMP NOT NULL, 
    data_ini TIMESTAMP NOT NULL, 
    data_fim TIMESTAMP NOT NULL, 
    valor_total MONEY NOT NULL,
    CONSTRAINT pk_id_in_contratos PRIMARY KEY (id), 
    CONSTRAINT fk_id_orgao_contratos FOREIGN KEY (id_orgao) REFERENCES orgao (id)
    
);


