-- Tabela Órgãos
CREATE TABLE IF NOT EXISTS web.orgao (
    id BIGSERIAL NOT NULL,           -- Chave primária com autoincremento
                                     -- Tamanho: 8 bytes por registro

    nome VARCHAR(255) NOT NULL,      -- Nome do órgão, até 255 caracteres
                                     -- Tamanho estimado: ~100 bytes por registro (assumindo uma média de 100 caracteres)

    cnpj VARCHAR(255) UNIQUE NOT NULL, -- CNPJ, campo único, até 255 caracteres
                                       -- Tamanho fixo: 14 bytes por registro (CNPJ possui 14 caracteres)
                                       -- Índice UNIQUE no CNPJ: ~14 bytes adicionais por registro

    CONSTRAINT pk_id_in_orgao PRIMARY KEY (id)
);

-- Estimativa de tamanho total por registro: ~136 bytes
-- Considerando 1.000.000 registros:
-- Tamanho total estimado da tabela: ~136 MB
