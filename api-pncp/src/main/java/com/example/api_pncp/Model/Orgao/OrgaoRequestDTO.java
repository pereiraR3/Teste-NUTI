package com.example.api_pncp.Model.Orgao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO responsável por receber e validar os dados do Front-End, garantindo a integridade das
 * informações antes de serem persistidas no banco de dados.
 */

@Schema(description = "DTO para a persistência de órgãos que foram alvos de pesquisas.")
public record OrgaoRequestDTO (

        @Schema(description = "CNPJ do órgão, utilizado para identificação única. Deve ter 14 caracteres.", example = "12345678000195")
        @NotBlank(message = "O CNPJ não pode estar em branco.")
        @Size(min = 14, max = 14, message = "O CNPJ deve ter exatamente 14 caracteres.")
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter apenas números.")
        String cnpj,

        @Schema(description = "Razão social do órgão.", example = "Orgão Público Federal")
        @NotBlank(message = "A razão social não pode estar em branco.")
        String razaoSocial,

        @Schema(description = "Nome da unidade federativa (estado) do órgão.", example = "São Paulo")
        @NotBlank(message = "O nome da unidade federativa não pode estar em branco.")
        String ufNome,

        @Schema(description = "Nome da unidade do órgão.", example = "Secretaria de Administração")
        @NotBlank(message = "O nome da unidade não pode estar em branco.")
        String nomeUnidade,

        @Schema(description = "Código da unidade do órgão.", example = "001")
        @NotBlank(message = "O código da unidade não pode estar em branco.")
        String codigoUnidade,

        @Schema(description = "Sigla da unidade federativa (estado) do órgão.", example = "SP")
        @NotBlank(message = "A sigla da unidade federativa não pode estar em branco.")
        String ufSigla,

        @Schema(description = "Nome do município do órgão.", example = "São Paulo")
        @NotBlank(message = "O nome do município não pode estar em branco.")
        String municipioNome,

        @Schema(description = "Código IBGE do município.", example = "3550308")
        @NotBlank(message = "O código IBGE do município não pode estar em branco.")
        String codigoIbge

) {}
