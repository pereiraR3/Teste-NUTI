package com.example.api_pncp.Model.Contrato;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO para receber e validar os dados do contrato enviados pelo Front-End.
 */

@Schema(description = "DTO para criação de um novo contrato, incluindo informações de vigência, fornecedor, objeto e valor.")
public record ContratoRequestDTO(

        @Schema(description = "Id do Órgão à qual o contrato está associado.", example = "1")
        @NotNull(message = "O id do órgão é obrigatório.")
        Long idOrgao,

        @Schema(description = "Data de início de vigência do contrato.", example = "2024-01-01")
        @NotNull(message = "A data de vigência inicial é obrigatória.")
        LocalDate dataVigenciaIni,

        @Schema(description = "Data de fim de vigência do contrato.", example = "2025-01-01")
        @NotNull(message = "A data de vigência final é obrigatória.")
        LocalDate dataVigenciaFim,

        @Schema(description = "Razão social do fornecedor.", example = "Fornecedor ABC Ltda.")
        @NotNull(message = "A razão social do fornecedor é obrigatória.")
        @Size(max = 255, message = "A razão social do fornecedor não pode ter mais de 255 caracteres.")
        String razaoSocialFornecedor,

        @Schema(description = "Descrição do objeto do contrato.", example = "Fornecimento de materiais de escritório.")
        @NotNull(message = "O objeto do contrato é obrigatório.")
        String objetoContrato,

        @Schema(description = "Valor inicial do contrato.", example = "2300.00")
        @NotNull(message = "O valor inicial do contrato é obrigatório.")
        @Positive(message = "O valor inicial deve ser positivo.")
        Double valorInicial

) {
}
