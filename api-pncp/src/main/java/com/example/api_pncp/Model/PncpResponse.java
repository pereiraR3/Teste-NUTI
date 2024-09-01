package com.example.api_pncp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PncpResponse {

    private Orgao orgaoEntidade;  // Informações do órgão
    private List<ContratoData> contratos;  // Lista de contratos

    @Data
    @NoArgsConstructor
    public static class Orgao {
        private String cnpj;
        private String razaoSocial;
        private String ufNome;
        private String nomeUnidade;
        private String codigoUnidade;
        private String ufSigla;
        private String municipioNome;
        private String codigoIbge;
    }

    @Data
    @NoArgsConstructor
    public static class ContratoData {
        private String dataVigenciaInicio;
        private String dataVigenciaFim;
        private String nomeRazaoSocialFornecedor;
        private String objetoContrato;
        private double valorInicial;
    }
}
