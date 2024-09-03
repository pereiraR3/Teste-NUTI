package com.example.api_pncp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PncpResponse {

    private List<ContratoData> data;  // Lista de contratos

    @Data
    @NoArgsConstructor
    public static class ContratoData {
        private String codigoPaisFornecedor;
        private String numeroControlePncpCompra;
        private OrgaoEntidade orgaoEntidade;
        private int anoContrato;
        private TipoContrato tipoContrato;
        private String numeroContratoEmpenho;
        private String dataAssinatura;
        private String dataVigenciaInicio;
        private String dataVigenciaFim;
        private String niFornecedor;
        private String tipoPessoa;
        private CategoriaProcesso categoriaProcesso;
        private String dataPublicacaoPncp;
        private String dataAtualizacao;
        private int sequencialContrato;
        private UnidadeOrgao unidadeOrgao;
        private String informacaoComplementar;
        private String processo;
        private String nomeRazaoSocialFornecedor;
        private String niFornecedorSubContratado;
        private String nomeFornecedorSubContratado;
        private double valorParcela;
        private double valorGlobal;
        private double valorAcumulado;
        private int numeroRetificacao;
        private String identificadorCipi;
        private String urlCipi;
        private String numeroControlePNCP;
        private boolean receita;
        private String tipoPessoaSubContratada;
        private String objetoContrato;
        private double valorInicial;
        private int numeroParcelas;
        private String usuarioNome;

        // Classes aninhadas para os sub-objetos da resposta JSON
        @Data
        @NoArgsConstructor
        public static class OrgaoEntidade {
            private String cnpj;
            private String razaoSocial;
            private String poderId;
            private String esferaId;
        }

        @Data
        @NoArgsConstructor
        public static class TipoContrato {
            private int id;
            private String nome;
        }

        @Data
        @NoArgsConstructor
        public static class CategoriaProcesso {
            private int id;
            private String nome;
        }

        @Data
        @NoArgsConstructor
        public static class UnidadeOrgao {
            private String ufNome;
            private String codigoUnidade;
            private String nomeUnidade;
            private String ufSigla;
            private String municipioNome;
            private String codigoIbge;
        }
    }
}
