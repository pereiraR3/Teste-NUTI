package com.example.api_pncp.Service;

import com.example.api_pncp.Model.Contrato.Contrato;
import com.example.api_pncp.Model.Orgao.Orgao;
import com.example.api_pncp.Model.PncpResponse;
import com.example.api_pncp.Repository.ContratoRepository;
import com.example.api_pncp.Repository.OrgaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PncpService {

    @Autowired
    private OrgaoRepository orgaoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    private final WebClient webClient;

    public PncpService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://pncp.gov.br").build();
    }

    /**
     * Método feito para realizar a busca pelos dados seguido pela persistência de dados
     * @param cnpjOrgao
     * @param dataInicial
     * @param dataFinal
     * @return Orgao
     */

    @Transactional
    public Orgao getOrgaoAndSave(String cnpjOrgao, String dataInicial, String dataFinal) {

        // Limpa os registros das tabelas orgao e contrato antes de processar a nova requisição
        cleanDatabase();

        String uri = String.format("/api/consulta/v1/contratos?dataInicial=%s&dataFinal=%s&cnpjOrgao=%s&pagina=1",
                dataInicial, dataFinal, cnpjOrgao);

        // Realiza a requisição à API
        PncpResponse response = this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(PncpResponse.class)
                .block(); // Operação síncrona

        // Verifica se a resposta é nula
        if (response == null || response.getOrgaoEntidade() == null) {
            throw new IllegalArgumentException("A resposta da API está vazia ou o órgão não foi encontrado.");
        }

        // Extrai e salva as informações do órgão e seus contratos no banco de dados
        PncpResponse.Orgao orgaoEntidade = response.getOrgaoEntidade();

        // Verifica se o órgão já existe no banco de dados
        Optional<Orgao> orgaoOptional = orgaoRepository.findByCnpj(orgaoEntidade.getCnpj());
        Orgao orgao;

        if (orgaoOptional.isEmpty()) {
            // Cria a entidade Orgao e popula com os dados recebidos
            orgao = new Orgao();
            orgao.setCnpj(orgaoEntidade.getCnpj());
            orgao.setRazaoSocial(orgaoEntidade.getRazaoSocial());
            orgao.setUfNome(orgaoEntidade.getUfNome());
            orgao.setNomeUnidade(orgaoEntidade.getNomeUnidade());
            orgao.setCodigoUnidade(orgaoEntidade.getCodigoUnidade());
            orgao.setUfSigla(orgaoEntidade.getUfSigla());
            orgao.setMunicipioNome(orgaoEntidade.getMunicipioNome());
            orgao.setCodigoIbge(orgaoEntidade.getCodigoIbge());
        } else {
            // Utiliza o órgão existente
            orgao = orgaoOptional.get();
        }

        // Extrai os contratos e associa ao órgão
        List<Contrato> contratos = response.getContratos().stream().map(contratoResponse -> {
            Contrato contrato = new Contrato();
            contrato.setDataVigenciaIni(LocalDate.parse(contratoResponse.getDataVigenciaInicio()));
            contrato.setDataVigenciaFim(LocalDate.parse(contratoResponse.getDataVigenciaFim()));
            contrato.setRazaoSocialFornecedor(contratoResponse.getNomeRazaoSocialFornecedor());
            contrato.setObjetoContrato(contratoResponse.getObjetoContrato());
            contrato.setValorInicial(contratoResponse.getValorInicial());
            contrato.setOrgao(orgao);  // Associa o contrato ao órgão
            return contrato;
        }).toList();

        // Associa os contratos ao órgão
        orgao.setContratos(contratos);

        // Salva o órgão e os contratos no banco de dados
        orgaoRepository.save(orgao);
        contratoRepository.saveAll(contratos);

        return orgao;
    }

    private void cleanDatabase() {
        contratoRepository.deleteAll();  // Limpa todos os registros da tabela contrato
    }

}
