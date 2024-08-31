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
    String cnpj

) {

    public OrgaoResponseDTO(Orgao orgao) {
        this(
            orgao.getId(),
            orgao.getCnpj()
        );
    }

}
