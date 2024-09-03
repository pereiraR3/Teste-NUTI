package com.example.api_pncp.Controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.api_pncp.Model.Orgao.OrgaoRequestDTO;
import com.example.api_pncp.Model.Orgao.OrgaoResponseDTO;
import com.example.api_pncp.Model.Orgao.OrgaoListeningDTO;
import com.example.api_pncp.Service.OrgaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/orgao")
@Tag(name = "Orgao", description = "Endpoints relacionados aos órgãos")
public class OrgaoController {

    @Autowired
    private OrgaoService orgaoService;

    @PostMapping("/create")
    @Operation(summary = "Cria um novo órgão", description = "Cria um novo órgão com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Órgão criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrgaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    public ResponseEntity<OrgaoResponseDTO> create(
            @RequestBody
            @Parameter(description = "Dados para criação do novo órgão", required = true) OrgaoRequestDTO request,
            UriComponentsBuilder uriBuilder
    ) {
        OrgaoResponseDTO response = orgaoService.createOrgao(request);
        var uri = uriBuilder.path("/orgao/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um órgão pelo ID", description = "Retorna os dados de um órgão específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrgaoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado",
                    content = @Content)
    })
    public ResponseEntity<OrgaoResponseDTO> findById(
            @PathVariable
            @Parameter(description = "ID do órgão a ser buscado", required = true) Long id) {
        OrgaoResponseDTO response = new OrgaoResponseDTO(orgaoService.findByIdOrgao(id));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findAllByIdWithContratos/{id}")
    @Operation(summary = "Busca um órgão e seus contratos pelo ID", description = "Retorna os dados de um órgão específico pelo ID, juntamente com seus contratos associados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão e contratos encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrgaoListeningDTO.class))),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado",
                    content = @Content)
    })
    public ResponseEntity<Stream<OrgaoListeningDTO>> findAllByIdWithContratos(
            @PathVariable
            @Parameter(description = "ID do órgão a ser buscado", required = true) Long id) {
        Stream<OrgaoListeningDTO> response = orgaoService.findAllByIdWithContratos(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findAllByCnpjWithContratos/{cnpj}")
    @Operation(summary = "Busca um órgão e seus contratos pelo CNPJ",
            description = "Retorna os dados de um órgão específico pelo CNPJ, juntamente com seus contratos associados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão e contratos encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrgaoListeningDTO.class))),
            @ApiResponse(responseCode = "404", description = "Órgão não encontrado",
                    content = @Content)
    })
    public ResponseEntity<Stream<OrgaoListeningDTO>> findAllByCnpjWithContratos(
            @PathVariable
            @Parameter(description = "CNPJ do órgão a ser buscado", required = true) String cnpj) {
        Stream<OrgaoListeningDTO> response = orgaoService.findAllByCnpjWithContratos(cnpj);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findAll")
    @Operation(summary = "Busca todos os órgãos", description = "Retorna uma lista de todos os órgãos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de órgãos encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrgaoResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum órgão encontrado",
                    content = @Content)
    })
    public ResponseEntity<List<OrgaoResponseDTO>> findAll() {
        List<OrgaoResponseDTO> listOrgaos = orgaoService.findAllOrgaos();
        if(listOrgaos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(listOrgaos);
    }
}
