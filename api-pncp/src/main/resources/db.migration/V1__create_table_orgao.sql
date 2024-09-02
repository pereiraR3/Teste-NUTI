-- Tabela Órgãos
CREATE TABLE IF NOT EXISTS orgao (
    id BIGSERIAL NOT NULL,                -- Chave primária com autoincremento
                                          -- Tamanho: 8 bytes por registro

    cnpj VARCHAR(14) UNIQUE NOT NULL,     -- CNPJ, campo único, até 14 caracteres
                                          -- Tamanho fixo: 14 bytes por registro (CNPJ possui 14 caracteres)
                                          -- Índice UNIQUE no CNPJ: ~14 bytes adicionais por registro

    razao_social VARCHAR(255) NOT NULL,   -- Razão social do órgão, até 255 caracteres
                                          -- Tamanho máximo: 255 bytes por registro

    uf_nome VARCHAR(100) NOT NULL,        -- Nome da unidade federativa (estado), até 100 caracteres
                                          -- Tamanho máximo: 100 bytes por registro

    nome_unidade VARCHAR(255) NOT NULL,   -- Nome da unidade do órgão, até 255 caracteres
                                          -- Tamanho máximo: 255 bytes por registro

    codigo_unidade VARCHAR(20) NOT NULL,  -- Código da unidade do órgão, até 20 caracteres
                                          -- Tamanho máximo: 20 bytes por registro

    uf_sigla VARCHAR(2) NOT NULL,         -- Sigla da unidade federativa (estado), 2 caracteres
                                          -- Tamanho fixo: 2 bytes por registro

    municipio_nome VARCHAR(100) NOT NULL, -- Nome do município do órgão, até 100 caracteres
                                          -- Tamanho máximo: 100 bytes por registro

    codigo_ibge VARCHAR(7) NOT NULL,      -- Código IBGE do município, até 7 caracteres
                                          -- Tamanho máximo: 7 bytes por registro

    CONSTRAINT pk_id_in_orgao PRIMARY KEY (id)
);

-- Estimativa de tamanho total por registro: ~800 bytes
-- Considerando 1.000.000 registros:
-- Tamanho total estimado da tabela: ~800 MB
