package com.example.api_pncp.Model.Pesquisa;

import java.time.LocalDate;

import com.example.api_pncp.Model.Orgao.OrgaoResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO criado para retornar os dados sobre determinada "Pesquisa" requerida pelo FrontEnd, isso de acordo com os
 * dados que já foram persistidos.
 */

@Schema(description = "DTO para retornar os dados de uma pesquisa realizada sobre contratos de um órgão público.")
public record PesquisaResponseDTO (

    @Schema(description = "Identificador único da pesquisa.", example = "1")
    Long id, 
    
    @Schema(description = "Dados do órgão relacionado à pesquisa.")
    OrgaoResponseDTO orgao,

    @Schema(description = "Data de realização da pesquisa.", example = "2024-08-31")
    LocalDate dataRealizacao,

    @Schema(description = "Data de início do período da pesquisa.", example = "2024-01-01")
    LocalDate dataIni,

    @Schema(description = "Data de fim do período da pesquisa.", example = "2024-08-01")
    LocalDate dataFim, 

    @Schema(description = "Valor total dos contratos obtidos na pesquisa.", example = "2300.00")
    Double valorTotal

) {

    public PesquisaResponseDTO(Pesquisa pesquisa) {
        this(
            pesquisa.getId(),
            new OrgaoResponseDTO(pesquisa.getOrgao()),
            pesquisa.getDataRealizacao(),
            pesquisa.getDataIni(),
            pesquisa.getDataFim(),
            pesquisa.getValorTotal()
        );
    }
    
}
