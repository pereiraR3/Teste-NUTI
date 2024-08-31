package com.example.api_pncp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.api_pncp.Model.Orgao.OrgaoRequestDTO;
import com.example.api_pncp.Model.Orgao.OrgaoResponseDTO;
import com.example.api_pncp.Service.OrgaoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
