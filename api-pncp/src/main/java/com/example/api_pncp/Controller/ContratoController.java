package com.example.api_pncp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.api_pncp.Model.Pesquisa.PesquisaRequestDTO;
import com.example.api_pncp.Model.Pesquisa.PesquisaResponseDTO;
import com.example.api_pncp.Service.ContratoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/pesquisa")
@Tag(name = "Pesquisa", description = "Endpoints relacionados às pesquisas")
public class ContratoController {
    
    @Autowired
    private ContratoService contratoService;

    @PostMapping("/create")
    @Operation(summary = "Cria uma nova pesquisa", description = "Cria uma nova pesquisa com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pesquisa criada com sucesso", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = PesquisaResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", 
                     content = @Content)
    })
    public ResponseEntity<PesquisaResponseDTO> create(
        @RequestBody 
        @Parameter(description = "Dados para criação da nova pesquisa", required = true) PesquisaRequestDTO request,
        UriComponentsBuilder uriBuilder
    ) {
        PesquisaResponseDTO response = contratoService.createPesquisa(request);
        var uri = uriBuilder.path("/pesquisa/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Busca uma pesquisa pelo ID", description = "Retorna os dados de uma pesquisa específica pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pesquisa encontrada", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = PesquisaResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Pesquisa não encontrada", 
                     content = @Content)
    })
    public ResponseEntity<PesquisaResponseDTO> findById(
        @PathVariable 
        @Parameter(description = "ID da pesquisa a ser buscada", required = true) Long id) {
        PesquisaResponseDTO response = new PesquisaResponseDTO(contratoService.findByIdPesquisa(id));
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/findAll")
    @Operation(summary = "Busca todas as pesquisas", description = "Retorna uma lista de todas as pesquisas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pesquisas encontrada", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = PesquisaResponseDTO.class))),
        @ApiResponse(responseCode = "204", description = "Nenhuma pesquisa encontrada", 
                     content = @Content)
    })
    public ResponseEntity<List<PesquisaResponseDTO>> findAll() {
        List<PesquisaResponseDTO> listOrgaos = contratoService.findAllPesquisas();
        if(listOrgaos.isEmpty())   
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(listOrgaos);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma pesquisa pelo ID", description = "Remove uma pesquisa existente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pesquisa deletada com sucesso", 
                     content = @Content),
        @ApiResponse(responseCode = "404", description = "Pesquisa não encontrada", 
                     content = @Content)
    })
    public ResponseEntity<Void> delete(
        @PathVariable 
        @Parameter(description = "ID da pesquisa a ser deletada", required = true) Long id) {
        contratoService.deletePesquisa(id);
        return ResponseEntity.noContent().build();
    }
}
