package com.example.api_pncp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInsertService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertOrgaosAndContratos() {
        // Inserção dos órgãos
        String insertOrgaosSql = "INSERT INTO public.orgao (cnpj, razao_social, uf_nome, nome_unidade, codigo_unidade, uf_sigla, municipio_nome, codigo_ibge) VALUES " +
                "('15084338000146', 'Orgão A', 'São Paulo', 'Unidade A', 'UA001', 'SP', 'São Paulo', '3550308')," +
                "('33004540000100', 'Orgão B', 'Rio de Janeiro', 'Unidade B', 'UB001', 'RJ', 'Rio de Janeiro', '3304557');";

        jdbcTemplate.execute(insertOrgaosSql);

        // Inserção dos contratos para o Orgão A
        String insertContratosOrgaoA = "INSERT INTO public.contrato (id_orgao, data_vigencia_ini, data_vigencia_fim, razao_social_fornecedor, objeto_contrato, valor_inicial) VALUES " +
                "(1, '2024-01-01', '2024-12-31', 'Fornecedor A1', 'Serviço de manutenção A1', 50000)," +
                "(1, '2024-02-01', '2024-12-31', 'Fornecedor A2', 'Serviço de consultoria A2', 120000)," +
                "(1, '2024-03-01', '2024-12-31', 'Fornecedor A3', 'Fornecimento de material A3', 75000)," +
                "(1, '2024-04-01', '2024-12-31', 'Fornecedor A4', 'Serviço de limpeza A4', 30000)," +
                "(1, '2024-05-01', '2024-12-31', 'Fornecedor A5', 'Serviço de transporte A5', 45000)," +
                "(1, '2024-06-01', '2024-12-31', 'Fornecedor A6', 'Fornecimento de equipamentos A6', 150000)," +
                "(1, '2024-07-01', '2024-12-31', 'Fornecedor A7', 'Serviço de segurança A7', 20000)," +
                "(1, '2024-08-01', '2024-12-31', 'Fornecedor A8', 'Serviço de TI A8', 80000)," +
                "(1, '2024-09-01', '2024-12-31', 'Fornecedor A9', 'Serviço de catering A9', 10000)," +
                "(1, '2024-10-01', '2024-12-31', 'Fornecedor A10', 'Consultoria financeira A10', 60000)," +
                "(1, '2024-11-01', '2024-12-31', 'Fornecedor A11', 'Serviço de publicidade A11', 90000);";

        jdbcTemplate.execute(insertContratosOrgaoA);

        // Inserção dos contratos para o Orgão B
        String insertContratosOrgaoB = "INSERT INTO public.contrato (id_orgao, data_vigencia_ini, data_vigencia_fim, razao_social_fornecedor, objeto_contrato, valor_inicial) VALUES " +
                "(2, '2024-01-01', '2024-12-31', 'Fornecedor B1', 'Serviço de manutenção B1', 55000)," +
                "(2, '2024-02-01', '2024-12-31', 'Fornecedor B2', 'Serviço de consultoria B2', 130000)," +
                "(2, '2024-03-01', '2024-12-31', 'Fornecedor B3', 'Fornecimento de material B3', 80000)," +
                "(2, '2024-04-01', '2024-12-31', 'Fornecedor B4', 'Serviço de limpeza B4', 35000)," +
                "(2, '2024-05-01', '2024-12-31', 'Fornecedor B5', 'Serviço de transporte B5', 48000)," +
                "(2, '2024-06-01', '2024-12-31', 'Fornecedor B6', 'Fornecimento de equipamentos B6', 160000)," +
                "(2, '2024-07-01', '2024-12-31', 'Fornecedor B7', 'Serviço de segurança B7', 25000)," +
                "(2, '2024-08-01', '2024-12-31', 'Fornecedor B8', 'Serviço de TI B8', 85000)," +
                "(2, '2024-09-01', '2024-12-31', 'Fornecedor B9', 'Serviço de catering B9', 12000)," +
                "(2, '2024-10-01', '2024-12-31', 'Fornecedor B10', 'Consultoria financeira B10', 70000)," +
                "(2, '2024-11-01', '2024-12-31', 'Fornecedor B11', 'Serviço de publicidade B11', 95000);";

        jdbcTemplate.execute(insertContratosOrgaoB);
    }
}
