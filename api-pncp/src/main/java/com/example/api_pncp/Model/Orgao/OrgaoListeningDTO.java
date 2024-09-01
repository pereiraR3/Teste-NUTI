package com.example.api_pncp.Model.Orgao;

import com.example.api_pncp.Model.Contrato.ContratoResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO que encapsula as informações de um órgão e sua lista de contratos associados.")
public record OrgaoListeningDTO(

        @Schema(description = "Informações do órgão.", implementation = OrgaoResponseDTO.class)
        OrgaoResponseDTO orgaoResponseDTO,

        @Schema(description = "Lista de contratos associados ao órgão.", implementation = ContratoResponseDTO.class)
        List<ContratoResponseDTO> contratoResponseDTOList

) {
}
