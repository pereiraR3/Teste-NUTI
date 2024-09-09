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

    @Transactional
    public Orgao getOrgaoAndSave(String cnpjOrgao, String dataInicial, String dataFinal) {

        // Limpa os registros das tabela contrato e órgao antes de processar a nova requisição -> Manter apenas 1 pesquisa no banco de dados
        cleanDatabase();

        String uri = String.format("/api/consulta/v1/contratos?dataInicial=%s&dataFinal=%s&cnpjOrgao=%s&pagina=1",
                dataInicial, dataFinal, cnpjOrgao);

        // Realiza a requisição à API
        PncpResponse response = this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(PncpResponse.class)
                .block(); // Operação síncrona

        // Verifica se a resposta é nula ou se não há contratos
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            throw new IllegalArgumentException("A resposta da API está vazia ou o órgão não foi encontrado.");
        }

        // Processa os dados retornados e salva no banco de dados
        PncpResponse.ContratoData firstContract = response.getData().get(0);
        PncpResponse.ContratoData.OrgaoEntidade orgaoEntidade = firstContract.getOrgaoEntidade();

        // Verifica se o órgão já existe no banco de dados
        Optional<Orgao> orgaoOptional = orgaoRepository.findByCnpj(orgaoEntidade.getCnpj());
        Orgao orgao;

        Boolean flag = false;

        if (orgaoOptional.isEmpty()) {

            // Cria a entidade Orgao e popula com os dados recebidos
            orgao = new Orgao();
            orgao.setCnpj(orgaoEntidade.getCnpj());
            orgao.setRazaoSocial(orgaoEntidade.getRazaoSocial());
            orgao.setUfNome(firstContract.getUnidadeOrgao().getUfNome());
            orgao.setNomeUnidade(firstContract.getUnidadeOrgao().getNomeUnidade());
            orgao.setCodigoUnidade(firstContract.getUnidadeOrgao().getCodigoUnidade());
            orgao.setUfSigla(firstContract.getUnidadeOrgao().getUfSigla());
            orgao.setMunicipioNome(firstContract.getUnidadeOrgao().getMunicipioNome());
            orgao.setCodigoIbge(firstContract.getUnidadeOrgao().getCodigoIbge());

        } else {
            // Utiliza o órgão existente
            orgao = orgaoOptional.get();
        }

        // Extrai os contratos e associa ao órgão
        List<Contrato> contratos = response.getData().stream().map(contratoResponse -> {
            Contrato contrato = new Contrato();
            contrato.setDataVigenciaIni(LocalDate.parse(contratoResponse.getDataVigenciaInicio()));
            contrato.setDataVigenciaFim(LocalDate.parse(contratoResponse.getDataVigenciaFim()));
            contrato.setRazaoSocialFornecedor(contratoResponse.getNomeRazaoSocialFornecedor());
            contrato.setObjetoContrato(contratoResponse.getObjetoContrato());
            contrato.setValorInicial(contratoResponse.getValorInicial());
            contrato.setOrgao(orgao);  // Associa o contrato ao órgão
            return contrato;
        }).toList();

        orgao.setContratos(contratos);

        // Salva o órgão e os contratos no banco de dados
        orgaoRepository.save(orgao);
        contratoRepository.saveAll(contratos);

        return orgao;
    }

    private void cleanDatabase() {
        orgaoRepository.deleteAll();
    }
}
