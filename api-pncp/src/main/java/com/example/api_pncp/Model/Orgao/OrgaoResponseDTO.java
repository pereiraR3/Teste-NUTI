package com.example.api_pncp.Model.Orgao;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO criado para retornar os dados sobre determinado órgão relacionado a uma pesquisa.
 */

@Schema(description = "DTO para retornar os dados de um órgão relacionado a uma pesquisa.")
public record OrgaoResponseDTO(

        @Schema(description = "Identificador único do órgão.", example = "1")
        Long id,

        @Schema(description = "CNPJ do órgão.", example = "12345678000195")
        String cnpj,

        @Schema(description = "Razão social do órgão.", example = "Orgão Público Federal")
        String razaoSocial,

        @Schema(description = "Nome da unidade federativa (estado) do órgão.", example = "São Paulo")
        String ufNome,

        @Schema(description = "Nome da unidade do órgão.", example = "Secretaria de Administração")
        String nomeUnidade,

        @Schema(description = "Código da unidade do órgão.", example = "001")
        String codigoUnidade,

        @Schema(description = "Sigla da unidade federativa (estado) do órgão.", example = "SP")
        String ufSigla,

        @Schema(description = "Nome do município do órgão.", example = "São Paulo")
        String municipioNome,

        @Schema(description = "Código IBGE do município.", example = "3550308")
        String codigoIbge

) {
    public OrgaoResponseDTO(Orgao orgao) {
        this(
                orgao.getId(),
                orgao.getCnpj(),
                orgao.getRazaoSocial(),
                orgao.getUfNome(),
                orgao.getNomeUnidade(),
                orgao.getCodigoUnidade(),
                orgao.getUfSigla(),
                orgao.getMunicipioNome(),
                orgao.getCodigoIbge()
        );
    }
}
