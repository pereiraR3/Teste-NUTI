
# Requisitos Funcionais - Obrigatórios

1. **Consumo da API do PNCP**:
   - Implementar a funcionalidade que consome a API REST do Portal Nacional de Contratações Públicas (PNCP) para obter informações sobre contratos.
   - A busca deve ser realizada utilizando o CNPJ de um órgão público e um intervalo de datas (data inicial e final).

2. **Exibição das Informações do Órgão**:
   - Exibir na página web as informações detalhadas do órgão público associado ao CNPJ fornecido.
   
3. **Listagem de Contratos**:
   - Listar todos os contratos do órgão que possuem a data de início de vigência dentro do período especificado.
   - Para cada contrato listado, devem ser exibidos os seguintes detalhes:
     - Data de vigência inicial.
     - Data de vigência final.
     - Razão social do fornecedor.
     - Objeto do contrato.
     - Valor inicial do contrato.

4. **Cálculo e Exibição do Valor Total**:
   - Calcular e exibir o valor total somado de todos os contratos obtidos dentro do período especificado.

5. **Armazenamento em Banco de Dados**:
   - Inserir todas as informações coletadas dos contratos e do órgão em um banco de dados para posterior consulta.

6. **Documentação e Comentários**:
   - Comentar o código-fonte de forma a garantir uma fácil compreensão por parte dos avaliadores.

7. **Entrega e Envio**:
   - Enviar o código desenvolvido junto com screenshots da aplicação para o e-mail especificado até a data limite de 02/09/2024.

# Requisitos Não Funcionais

Este projeto foi desenvolvido utilizando tecnologias modernas e ferramentas robustas, tanto no Front-End quanto no Back-End, visando garantir desempenho, escalabilidade e facilidade de manutenção.

## Front-End

Para o desenvolvimento do Front-End, foram utilizadas as seguintes tecnologias:

- **Linguagem de Programação**:
  - **JavaScript**: A linguagem principal para o desenvolvimento das funcionalidades dinâmicas da interface.

- **Linguagens de Marcação e Estilo**:
  - **HTML5**: Para a estruturação semântica do conteúdo das páginas.
  - **CSS3**: Para a estilização visual e responsividade da interface.

- **Frameworks**:
  - **Vite + ReactJS**: Utilizado para construir uma aplicação modular, rápida e eficiente. Vite oferece um ambiente de desenvolvimento ágil, enquanto ReactJS possibilita a criação de componentes reutilizáveis e a construção de interfaces de usuário interativas.

## Back-End

O Back-End foi desenvolvido utilizando tecnologias robustas que permitem a construção de uma API segura e escalável:

- **Linguagem de Programação**:
  - **Java**: Linguagem amplamente utilizada para o desenvolvimento de aplicações corporativas, conhecida por sua robustez e capacidade de escalabilidade.

- **Frameworks**:
  - **Spring Boot**: Framework que facilita o desenvolvimento de aplicações Java, proporcionando um ambiente simplificado para a configuração e implementação de APIs RESTful, além de oferecer suporte a diversas funcionalidades, como segurança e persistência de dados.

# Front-End





# Back-End
Abaixo está tudo relativo ao Back-End da aplicação, que foi feito para implementar a parte de persistência de dados do teste prático.

## Modelagem Conceitual

### DER (Diagrama Entidade-Relacionamento)
![Diagrama de Entidade-Relacionamento](./info_db/der/der.jpg)

#### Dicionário Entidade & Relacionamento

Logo abaixo, tem-se todas as informações sobre as Entidades e seus respectivos relacionamentos. Essas informações estão disponíveis para facilitar o entendimento da Modelagem de Dados.

- **Órgão**: Representa os órgãos que estão sendo cadastrados no sistema. Cada órgão possui um nome e um CNPJ único.
- **Pesquisa**: Armazena as pesquisas realizadas por cada órgão. Cada pesquisa está associada a um órgão, possui datas de início e término, um valor total, e uma data de pesquisa que, por padrão, é a data atual.

#### Dicionário Tabela & Atributo

A seguir estão todas as informações sobre as Entidades e seus respectivos atributos. Essas informações estão disponíveis para facilitar o entendimento da Modelagem de Dados.

- **Órgão**
  - `id` (BIGSERIAL, PRIMARY KEY): Identificador único do órgão.
  - `nome` (VARCHAR(255), NOT NULL): Nome do órgão, até 255 caracteres.
  - `cnpj` (VARCHAR(255), UNIQUE, NOT NULL): CNPJ do órgão, único, até 255 caracteres.

  **Estimativa de tamanho por registro:**
  - `id`: 8 bytes por registro.
  - `nome`: ~100 bytes por registro (assumindo uma média de 100 caracteres).
  - `cnpj`: 14 bytes por registro (considerando o CNPJ com 14 caracteres).
  - **Tamanho total estimado por registro:** ~136 bytes.

- **Pesquisa**
  - `id` (BIGSERIAL, PRIMARY KEY): Identificador único da pesquisa.
  - `id_orgao` (BIGINT, NOT NULL): Chave estrangeira que referencia o id do órgão na tabela `orgao`.
  - `data_pesquisa` (DATE, DEFAULT CURRENT_DATE): Data em que a pesquisa foi realizada, com valor padrão para a data atual.
  - `data_ini` (DATE, NOT NULL): Data de início da pesquisa.
  - `data_fim` (DATE, NOT NULL): Data de término da pesquisa.
  - `valor_total` (MONEY, NOT NULL): Valor total da pesquisa.

  **Estimativa de tamanho por registro:**
  - `id`: 8 bytes por registro.
  - `id_orgao`: 8 bytes por registro.
  - `data_pesquisa`: 4 bytes por registro.
  - `data_ini`: 4 bytes por registro.
  - `data_fim`: 4 bytes por registro.
  - `valor_total`: 8 bytes por registro.
  - **Tamanho total estimado por registro:** ~36 bytes.


## Modelagem Lógica

![Modelo Lógico](./info_db/logic/model_logic.png)

## Modelagem Física 

### SQL

```sql

CREATE SCHEMA IF NOT EXISTS web;

---------

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

---------

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

---------

```