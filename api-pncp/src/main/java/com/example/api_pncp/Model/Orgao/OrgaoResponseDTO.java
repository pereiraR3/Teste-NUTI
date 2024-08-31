package com.example.api_pncp.Model.Orgao;

/**
 * DTO criado para retornar os dados sobre determinada "Pesquisa" requerida pelo FrontEnd, isso de acordo com os
 * dados que já foram persistidos.
 */

public record OrgaoResponseDTO(

    Long id, 
    String nome

) {
} 
