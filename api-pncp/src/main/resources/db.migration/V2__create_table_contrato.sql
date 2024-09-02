-- Tabela Contrato
CREATE TABLE IF NOT EXISTS contrato (
    id BIGSERIAL NOT NULL,               -- Chave primária com autoincremento
                                         -- Tamanho: 8 bytes por registro

    id_orgao BIGINT NOT NULL,         -- Chave estrangeira referenciando a tabela orgao
                                         -- Tamanho: 8 bytes por registro (BIGINT)

    data_vigencia_ini DATE NOT NULL,     -- Data de início de vigência do contrato
                                         -- Tamanho: 4 bytes por registro (DATE)

    data_vigencia_fim DATE NOT NULL,     -- Data de fim de vigência do contrato
                                         -- Tamanho: 4 bytes por registro (DATE)

    razao_social_fornecedor VARCHAR(255) NOT NULL,  -- Razão social do fornecedor
                                                    -- Tamanho: variável (VARCHAR)

    objeto_contrato TEXT NOT NULL,       -- Descrição do objeto do contrato
                                         -- Tamanho: variável (TEXT)

    valor_inicial MONEY NOT NULL,        -- Valor inicial do contrato
                                         -- Tamanho: 8 bytes por registro (MONEY)

    CONSTRAINT pk_id_contrato PRIMARY KEY (id),
    CONSTRAINT fk_id_orgao_contrato FOREIGN KEY (id_orgao) REFERENCES orgao (id)
);

-- Estimativa de tamanho total por registro: ~44 bytes
-- Considerando 1.000.000 registros:
-- Tamanho total estimado da tabela: ~44 MB
