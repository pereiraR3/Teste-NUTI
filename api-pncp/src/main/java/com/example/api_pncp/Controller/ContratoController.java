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

import com.example.api_pncp.Model.Contrato.ContratoRequestDTO;
import com.example.api_pncp.Model.Contrato.ContratoResponseDTO;
import com.example.api_pncp.Service.ContratoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/contrato")
@Tag(name = "Contrato", description = "Endpoints relacionados aos contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping("/create")
    @Operation(summary = "Cria um novo contrato", description = "Cria um novo contrato com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContratoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    public ResponseEntity<ContratoResponseDTO> create(
            @RequestBody
            @Parameter(description = "Dados para criação do novo contrato", required = true) ContratoRequestDTO request,
            UriComponentsBuilder uriBuilder
    ) {
        ContratoResponseDTO response = contratoService.createContrato(request);
        var uri = uriBuilder.path("/contrato/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um contrato pelo ID", description = "Retorna os dados de um contrato específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContratoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado",
                    content = @Content)
    })
    public ResponseEntity<ContratoResponseDTO> findById(
            @PathVariable
            @Parameter(description = "ID do contrato a ser buscado", required = true) Long id) {
        ContratoResponseDTO response = new ContratoResponseDTO(contratoService.findByIdContrato(id));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findAll")
    @Operation(summary = "Busca todos os contratos", description = "Retorna uma lista de todos os contratos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de contratos encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContratoResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum contrato encontrado",
                    content = @Content)
    })
    public ResponseEntity<List<ContratoResponseDTO>> findAll() {
        List<ContratoResponseDTO> listContratos = contratoService.findAllContratos();
        if(listContratos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(listContratos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um contrato pelo ID", description = "Remove um contrato existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contrato deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado",
                    content = @Content)
    })
    public ResponseEntity<Void> delete(
            @PathVariable
            @Parameter(description = "ID do contrato a ser deletado", required = true) Long id) {
        contratoService.deleteContrato(id);
        return ResponseEntity.noContent().build();
    }
}
