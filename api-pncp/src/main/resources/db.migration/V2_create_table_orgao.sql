-- Tabela Órgãos
CREATE TABLE IF NOT EXISTS web.orgao (
    id BIGSERIAL NOT NULL,             -- Chave primária com autoincremento
                                       -- Tamanho: 8 bytes por registro

    cnpj VARCHAR(14) UNIQUE NOT NULL, -- CNPJ, campo único, até 14 caracteres
                                       -- Tamanho fixo: 14 bytes por registro (CNPJ possui 14 caracteres)
                                       -- Índice UNIQUE no CNPJ: ~14 bytes adicionais por registro

    CONSTRAINT pk_id_in_orgao PRIMARY KEY (id)
);

-- Estimativa de tamanho total por registro: ~36 bytes
-- Considerando 1.000.000 registros:
-- Tamanho total estimado da tabela: ~36 MB
