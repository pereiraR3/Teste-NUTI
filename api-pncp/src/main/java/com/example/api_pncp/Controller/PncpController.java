package com.example.api_pncp.Controller;

import com.example.api_pncp.Model.Orgao.Orgao;
import com.example.api_pncp.Service.PncpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/pncp")
@Tag(name = "PNCP", description = "Endpoints para interagir com o Portal Nacional de Contratações Públicas (PNCP)")
public class PncpController {

    @Autowired
    private PncpService pncpService;

    @CrossOrigin(origins = "https://web-react-pncp-pw3vm2x0e-anthony-ricardos-projects.vercel.app/")
    @PostMapping("/orgao")
    @Operation(summary = "Obtém e salva as informações de um órgão público",
            description = "Busca informações de um órgão público usando o CNPJ e um período específico, e as salva no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações do órgão retornadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Orgao.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos fornecidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    public ResponseEntity<Orgao> getOrgaoAndSave(
            @RequestParam
            @Parameter(description = "CNPJ do órgão a ser buscado", example = "12345678000195", required = true) String cnpjOrgao,
            @RequestParam
            @Parameter(description = "Data inicial do período da pesquisa no formato 'YYYY-MM-DD'", example = "2024-01-01", required = true) String dataInicial,
            @RequestParam
            @Parameter(description = "Data final do período da pesquisa no formato 'YYYY-MM-DD'", example = "2024-12-31", required = true) String dataFinal) {

        Orgao orgao = pncpService.getOrgaoAndSave(cnpjOrgao, dataInicial, dataFinal);
        return ResponseEntity.ok(orgao);
    }
}
