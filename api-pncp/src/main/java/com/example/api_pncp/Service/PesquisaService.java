package com.example.api_pncp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_pncp.Model.Orgao.Orgao;
import com.example.api_pncp.Model.Pesquisa.Pesquisa;
import com.example.api_pncp.Model.Pesquisa.PesquisaRequestDTO;
import com.example.api_pncp.Model.Pesquisa.PesquisaResponseDTO;
import com.example.api_pncp.Repository.PesquisaRepository;

import jakarta.transaction.Transactional;

@Service
public class PesquisaService {
    
    @Autowired
    private OrgaoService orgaoService;

    @Autowired
    private PesquisaRepository pesquisaRepository;

    /**
     * Cria uma nova pesquisa e a persiste no banco de dados.
     * 
     * @param request Objeto DTO que contém os dados necessários para criar uma nova pesquisa.
     * @return PesquisaResponseDTO contendo os dados da pesquisa recém-criada.
     */
    @Transactional
    public PesquisaResponseDTO createPesquisa(PesquisaRequestDTO request) {

        // Encontra o órgão relacionado à pesquisa pelo ID
        Orgao orgao = orgaoService.findByIdOrgao(request.idOrgao());

        // Cria uma nova instância de Pesquisa com os dados fornecidos
        Pesquisa pesquisa = new Pesquisa(request, orgao);

        // Salva a nova pesquisa no banco de dados
        pesquisaRepository.save(pesquisa);

        // Retorna os dados da pesquisa em um DTO de resposta
        return new PesquisaResponseDTO(pesquisa);

    }

    /**
     * Busca uma pesquisa pelo seu ID.
     * 
     * @param id O ID da pesquisa a ser encontrada.
     * @return Pesquisa encontrada com o ID fornecido.
     * @throws ResponseStatusException se a pesquisa não for encontrada.
     */
    public Pesquisa findByIdPesquisa(Long id) {

        // Tenta encontrar a pesquisa pelo ID. Se não encontrar, lança uma exceção de status 404.
        return pesquisaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pesquisa não encontrada"));

    }

    /**
     * Busca todas as pesquisas no banco de dados.
     * 
     * @return Uma lista de PesquisaResponseDTO contendo os dados de todas as pesquisas.
     */
    public List<PesquisaResponseDTO> findAllPesquisas() {

        // Encontra todas as pesquisas e as converte para uma lista de DTOs de resposta
        return pesquisaRepository.findAll()
            .stream().map(PesquisaResponseDTO::new).collect(Collectors.toList());

    }

    /**
     * Exclui uma pesquisa pelo seu ID.
     * 
     * @param id O ID da pesquisa a ser excluída.
     */
    @Transactional
    public void deletePesquisa(Long id) {

        // Busca a pesquisa pelo ID. Se encontrada, a exclui do banco de dados.
        Pesquisa pesquisa = findByIdPesquisa(id);
        pesquisaRepository.delete(pesquisa);

    }

}
