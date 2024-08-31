package com.example.api_pncp.Model.Pesquisa;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.AssertTrue;

/**
 * DTO criado para receber e validar os dados do Front-End, garantindo a integridade das informações
 * antes de proceder com o processo de persistência de dados.
 */

@Schema(description = "DTO para persistência do valor total de contratos obtido em uma pesquisa, incluindo o momento em que foi realizada e os parâmetros utilizados.")
public record PesquisaRequestDTO(

    @Schema(description = "Id relativo ao órgão ao qual a pesquisa está associada.", example = "1")
    @NotNull(message = "O id do órgão é obrigatório.")
    Long idOrgao,

    @Schema(description = "Data de início do intervalo da pesquisa.", example = "2024-01-01")
    @NotNull(message = "A data de início é obrigatória.")
    LocalDate dataIni,

    @Schema(description = "Data de fim do intervalo da pesquisa. Deve ser posterior à data de início.", example = "2024-08-01")
    @NotNull(message = "A data de fim é obrigatória.")
    LocalDate dataFim,

    @Schema(description = "Valor total dos contratos obtidos ao pesquisar por um CNPJ no intervalo de datas fornecido.", example = "2300.00")
    @NotNull(message = "O valor total é obrigatório.")
    @Positive(message = "O valor total deve ser positivo.")
    Double valorTotal

) {

    /**
     * Valida se a data de fim é posterior à data de início.
     * 
     * @return true se dataFim for posterior a dataIni, caso contrário, false.
     */
    @AssertTrue(message = "A data de fim deve ser posterior à data de início.")
    public boolean isDataFimPosterior() {
        if (dataIni != null && dataFim != null) {
            return dataFim.isAfter(dataIni);
        }
        return true; // Consideramos como válido se uma das datas for nula, pois a validação de null já é feita.
    }
}
