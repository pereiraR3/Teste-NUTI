package com.example.api_pncp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Classe PncpResponse que mapeia a resposta JSON recebida da API PNCP.
 * Essa classe usa o Lombok para gerar automaticamente getters, setters, e construtores sem parâmetros.
 */
@Data
@NoArgsConstructor
public class PncpResponse {

    /**
     * Lista de objetos ContratoData que contém os dados dos contratos.
     */
    private List<ContratoData> data;  // Lista de contratos

    /**
     * Classe aninhada ContratoData que mapeia cada contrato individual na resposta JSON.
     * Cada contrato contém várias informações, como dados do órgão, tipo de contrato, fornecedor, etc.
     */
    @Data
    @NoArgsConstructor
    public static class ContratoData {
        private String codigoPaisFornecedor;  // Código do país do fornecedor
        private String numeroControlePncpCompra;  // Número de controle do PNCP para a compra
        private OrgaoEntidade orgaoEntidade;  // Dados da entidade/órgão responsável
        private int anoContrato;  // Ano do contrato
        private TipoContrato tipoContrato;  // Tipo de contrato
        private String numeroContratoEmpenho;  // Número do contrato de empenho
        private String dataAssinatura;  // Data de assinatura do contrato
        private String dataVigenciaInicio;  // Data de início da vigência do contrato
        private String dataVigenciaFim;  // Data de fim da vigência do contrato
        private String niFornecedor;  // Número de identificação do fornecedor
        private String tipoPessoa;  // Tipo de pessoa (física ou jurídica)
        private CategoriaProcesso categoriaProcesso;  // Categoria do processo
        private String dataPublicacaoPncp;  // Data de publicação no PNCP
        private String dataAtualizacao;  // Data da última atualização
        private int sequencialContrato;  // Número sequencial do contrato
        private UnidadeOrgao unidadeOrgao;  // Unidade do órgão responsável
        private String informacaoComplementar;  // Informações complementares sobre o contrato
        private String processo;  // Processo relacionado ao contrato
        private String nomeRazaoSocialFornecedor;  // Nome ou razão social do fornecedor
        private String niFornecedorSubContratado;  // Número de identificação do fornecedor subcontratado
        private String nomeFornecedorSubContratado;  // Nome do fornecedor subcontratado
        private double valorParcela;  // Valor de cada parcela
        private double valorGlobal;  // Valor global do contrato
        private double valorAcumulado;  // Valor acumulado do contrato
        private int numeroRetificacao;  // Número de retificações do contrato
        private String identificadorCipi;  // Identificador do CIPI (Cadastro de Informações de Produtos e Serviços)
        private String urlCipi;  // URL para o CIPI
        private String numeroControlePNCP;  // Número de controle no PNCP
        private boolean receita;  // Indica se o contrato gera receita
        private String tipoPessoaSubContratada;  // Tipo de pessoa do fornecedor subcontratado
        private String objetoContrato;  // Objeto do contrato
        private double valorInicial;  // Valor inicial do contrato
        private int numeroParcelas;  // Número de parcelas
        private String usuarioNome;  // Nome do usuário que cadastrou ou atualizou o contrato

        /**
         * Classe aninhada OrgaoEntidade que mapeia as informações da entidade/órgão responsável pelo contrato.
         */
        @Data
        @NoArgsConstructor
        public static class OrgaoEntidade {
            private String cnpj;  // CNPJ do órgão
            private String razaoSocial;  // Razão social do órgão
            private String poderId;  // Identificação do poder (executivo, legislativo, etc.)
            private String esferaId;  // Identificação da esfera (federal, estadual, municipal)
        }

        /**
         * Classe aninhada TipoContrato que mapeia o tipo de contrato.
         */
        @Data
        @NoArgsConstructor
        public static class TipoContrato {
            private int id;  // ID do tipo de contrato
            private String nome;  // Nome do tipo de contrato
        }

        /**
         * Classe aninhada CategoriaProcesso que mapeia a categoria do processo relacionado ao contrato.
         */
        @Data
        @NoArgsConstructor
        public static class CategoriaProcesso {
            private int id;  // ID da categoria do processo
            private String nome;  // Nome da categoria do processo
        }

        /**
         * Classe aninhada UnidadeOrgao que mapeia informações sobre a unidade do órgão responsável.
         */
        @Data
        @NoArgsConstructor
        public static class UnidadeOrgao {
            private String ufNome;  // Nome da Unidade Federativa (estado)
            private String codigoUnidade;  // Código da unidade do órgão
            private String nomeUnidade;  // Nome da unidade
            private String ufSigla;  // Sigla da Unidade Federativa
            private String municipioNome;  // Nome do município
            private String codigoIbge;  // Código IBGE do município
        }
    }
}
