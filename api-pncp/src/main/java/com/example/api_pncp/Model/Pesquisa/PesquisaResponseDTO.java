package com.example.api_pncp.Model.Pesquisa;

import com.example.api_pncp.Model.Orgao.OrgaoResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO criado para retornar os dados sobre determinada "Pesquisa" requerida pelo FrontEnd, isso de acordo com os
 * dados que já foram persistidos.
 */

@Schema(description = "")
public record PesquisaResponseDTO (

    Long id, 
    OrgaoResponseDTO orgao,
    Double valorTotal

) {
    
}
