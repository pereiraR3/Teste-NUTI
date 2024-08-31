package com.example.api_pncp.Model.Pesquisa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * DTO criado para obter os dados do FrontEnd e validar a transferência de dados até chegar ao
 * processo de persistência de dados.
 */

@Schema(description = "DTO para persistencia do valor total de contratos obtido em uma pesquisa")
public record PesquisaRequestDTO(

    @Schema(description = "Id relativo ao orgão em que a pesquisa está associada", example = "1L")
    @NotNull
    Long id_orgao,

    @Schema(description = "Valor total de contratos obtido ao pesquisar por um CNPJ em determinada DataIni e DataFim",
        example = "2300"
    )
    @NotNull
    Double valor_total

) {
    
}
