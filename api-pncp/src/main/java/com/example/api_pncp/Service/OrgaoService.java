package com.example.api_pncp.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.api_pncp.Model.Contrato.Contrato;
import com.example.api_pncp.Model.Contrato.ContratoResponseDTO;
import com.example.api_pncp.Model.Orgao.OrgaoListeningDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_pncp.Model.Orgao.Orgao;
import com.example.api_pncp.Model.Orgao.OrgaoRequestDTO;
import com.example.api_pncp.Model.Orgao.OrgaoResponseDTO;
import com.example.api_pncp.Repository.OrgaoRepository;

import jakarta.transaction.Transactional;

@Service
public class OrgaoService {
    
    @Autowired
    private OrgaoRepository orgaoRepository;

    /**
     * Para criar um entidade do tipo órgão
     * @param request
     * @return OrgaoResponseDTO
     */
    @Transactional
    public OrgaoResponseDTO createOrgao(OrgaoRequestDTO request){
            
        Orgao newOrgao = new Orgao(request);

        orgaoRepository.save(newOrgao);

        return new OrgaoResponseDTO(newOrgao);

    }

    /**
     * Para pesquisar por determinado Órgão que foi pesquisado
     * @param id
     * @return Orgao
     */
    public Orgao findByIdOrgao(Long id){

        return orgaoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Órgão não encontrado"));

    }

    /**
     * Busca um órgão pelo seu ID e retorna um stream contendo os dados do órgão e seus contratos associados.
     *
     * @param id O ID do órgão a ser buscado.
     * @return Um stream de {@link OrgaoListeningDTO} contendo as informações do órgão e seus contratos.
     *         - {@link OrgaoResponseDTO}: DTO com os dados do órgão.
     *         - {@link ContratoResponseDTO}: Lista de DTOs com os dados dos contratos associados ao órgão.
     */
    public Stream<OrgaoListeningDTO> findAllByIdWithContratos(Long id){
        return orgaoRepository.findById(id)
                .stream()
                .map(i -> {
                    OrgaoResponseDTO orgaoResponseDTO = new OrgaoResponseDTO(i);
                    List<ContratoResponseDTO> contratoResponseDTOList = i.getContratos().stream().map(ContratoResponseDTO::new).toList();

                    return new OrgaoListeningDTO(orgaoResponseDTO, contratoResponseDTOList);
                });
    }

    /**
     * Busca um órgão pelo seu CNPJ e retorna um stream contendo os dados do órgão e seus contratos associados
     * de persistência de dados no banco.
     *
     * @param cnpj O CNPJ do órgão a ser buscado.
     * @return Um stream de {@link OrgaoListeningDTO} contendo as informações do órgão e seus contratos.
     *         - {@link OrgaoResponseDTO}: DTO com os dados do órgão.
     *         - {@link ContratoResponseDTO}: Lista de DTOs com os dados dos contratos associados ao órgão.
     */
   public Stream<OrgaoListeningDTO> findAllByCnpjWithContratos(String cnpj){
    return orgaoRepository.findByCnpj(cnpj)
            .stream()
            .map(i -> {
                OrgaoResponseDTO orgaoResponseDTO = new OrgaoResponseDTO(i);
                List<ContratoResponseDTO> contratoResponseDTOList = i.getContratos().stream().map(ContratoResponseDTO::new).toList();

                return new OrgaoListeningDTO(orgaoResponseDTO, contratoResponseDTOList);
            });
    }



    /**
     * Para listar todos os Órgãos que foram pesquisados
     * @return List<OrgaoResponseDTO>
     */
    public List<OrgaoResponseDTO> findAllOrgaos(){

        return orgaoRepository.findAll()
        .stream().map(OrgaoResponseDTO::new).collect(Collectors.toList());

    }

}
