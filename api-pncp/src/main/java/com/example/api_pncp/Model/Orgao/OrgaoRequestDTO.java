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
    String cnpj

) {
    
}
