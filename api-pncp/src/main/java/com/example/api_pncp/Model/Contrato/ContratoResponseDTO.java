package com.example.api_pncp.Model.Contrato;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para enviar os dados do contrato para o Front-End, garantindo a visualização dos dados.
 */

@Schema(description = "DTO para visualização dos detalhes de um contrato.")
public record ContratoResponseDTO(

        @Schema(description = "Id do contrato.", example = "1")
        Long id,

        @Schema(description = "Id do órgão à qual o contrato está associado.", example = "1")
        Long idOrgao,

        @Schema(description = "Data de início de vigência do contrato.", example = "2024-01-01")
        LocalDate dataVigenciaIni,

        @Schema(description = "Data de fim de vigência do contrato.", example = "2025-01-01")
        LocalDate dataVigenciaFim,

        @Schema(description = "Razão social do fornecedor.", example = "Fornecedor ABC Ltda.")
        String razaoSocialFornecedor,

        @Schema(description = "Descrição do objeto do contrato.", example = "Fornecimento de materiais de escritório.")
        String objetoContrato,

        @Schema(description = "Valor inicial do contrato.", example = "2300.00")
        Double valorInicial

) {

    public ContratoResponseDTO(Contrato contrato){
        this(
                contrato.getId(),
                contrato.getOrgao().getId(),
                contrato.getDataVigenciaIni(),
                contrato.getDataVigenciaFim(),
                contrato.getRazaoSocialFornecedor(),
                contrato.getObjetoContrato(),
                contrato.getValorInicial()

        );
    }

}
