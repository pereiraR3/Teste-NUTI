-- Tabela Pesquisa
CREATE TABLE IF NOT EXISTS web.pesquisa (
    id BIGSERIAL NOT NULL,               -- Chave primária com autoincremento
                                         -- Tamanho: 8 bytes por registro

    id_orgao BIGINT NOT NULL,            -- Chave estrangeira referenciando a tabela orgao
                                         -- Tamanho: 8 bytes por registro (BIGINT)

    data_pesquisa DATE DEFAULT CURRENT_DATE,  -- Data da pesquisa, com valor padrão a data atual
                                              -- Tamanho: 4 bytes por registro (DATE)

    data_ini DATE NOT NULL,              -- Data de início da pesquisa
                                         -- Tamanho: 4 bytes por registro (DATE)

    data_fim DATE NOT NULL,              -- Data de término da pesquisa
                                         -- Tamanho: 4 bytes por registro (DATE)

    valor_total MONEY NOT NULL,          -- Valor total da pesquisa
                                         -- Tamanho: 8 bytes por registro (MONEY)

    CONSTRAINT pk_id_in_contratos PRIMARY KEY (id), 
    CONSTRAINT fk_id_orgao_contratos FOREIGN KEY (id_orgao) REFERENCES web.orgao (id)
);

-- Estimativa de tamanho total por registro: ~36 bytes
-- Considerando 1.000.000 registros:
-- Tamanho total estimado da tabela: ~36 MB
