package com.example.api_pncp.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.api_pncp.Model.Contrato.Contrato;
import com.example.api_pncp.Model.Contrato.ContratoRequestDTO;
import com.example.api_pncp.Model.Contrato.ContratoResponseDTO;
import com.example.api_pncp.Model.Orgao.Orgao;
import com.example.api_pncp.Repository.ContratoRepository;
import com.example.api_pncp.Service.OrgaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

@Service
public class ContratoService {

    @Autowired
    private OrgaoService orgaoService;

    @Autowired
    private ContratoRepository contratoRepository;

    /**
     * Cria um novo contrato e o persiste no banco de dados.
     *
     * @param request Objeto DTO que contém os dados necessários para criar um novo contrato.
     * @return ContratoResponseDTO contendo os dados do contrato recém-criado.
     */
    @Transactional
    public ContratoResponseDTO createContrato(ContratoRequestDTO request) {

        // Encontra o órgão relacionado ao contrato pelo ID
        Orgao orgao = orgaoService.findByIdOrgao(request.idOrgao());

        // Cria uma nova instância de Contrato com os dados fornecidos
        Contrato contrato = new Contrato(request, orgao);

        // Salva o novo contrato no banco de dados
        contratoRepository.save(contrato);

        // Retorna os dados do contrato em um DTO de resposta
        return new ContratoResponseDTO(contrato);
    }

    /**
     * Busca um contrato pelo seu ID.
     *
     * @param id O ID do contrato a ser encontrado.
     * @return Contrato encontrado com o ID fornecido.
     * @throws ResponseStatusException se o contrato não for encontrado.
     */
    public Contrato findByIdContrato(Long id) {

        // Tenta encontrar o contrato pelo ID. Se não encontrar, lança uma exceção de status 404.
        return contratoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contrato não encontrado"));
    }

    /**
     * Busca todos os contratos no banco de dados.
     *
     * @return Uma lista de ContratoResponseDTO contendo os dados de todos os contratos.
     */
    public List<ContratoResponseDTO> findAllContratos() {

        // Encontra todos os contratos e os converte para uma lista de DTOs de resposta
        return contratoRepository.findAll()
                .stream().map(ContratoResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * Exclui um contrato pelo seu ID.
     *
     * @param id O ID do contrato a ser excluído.
     */
    @Transactional
    public void deleteContrato(Long id) {

        // Busca o contrato pelo ID. Se encontrado, o exclui do banco de dados.
        Contrato contrato = findByIdContrato(id);
        contratoRepository.delete(contrato);
    }
}
