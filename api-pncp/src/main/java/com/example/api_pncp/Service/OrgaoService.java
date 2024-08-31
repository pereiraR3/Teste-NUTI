package com.example.api_pncp.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * Para listar todos os Órgãos que foram pesquisados
     * @return List<OrgaoResponseDTO>
     */
    public List<OrgaoResponseDTO> findAllOrgaos(){

        return orgaoRepository.findAll()
        .stream().map(OrgaoResponseDTO::new).collect(Collectors.toList());

    }

}
