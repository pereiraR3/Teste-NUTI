package com.example.api_pncp.Service;

import com.example.api_pncp.Model.Contrato.Contrato;
import com.example.api_pncp.Model.Orgao.Orgao;
import com.example.api_pncp.Model.PncpResponse;
import com.example.api_pncp.Repository.ContratoRepository;
import com.example.api_pncp.Repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

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

        // Extrai e salva as informações do órgão e seus contratos no banco de dados
        assert response != null;
        PncpResponse.Orgao orgaoEntidade = response.getOrgaoEntidade();

        // Cria a entidade Orgao e popula com os dados recebidos
        Orgao orgao = new Orgao();
        orgao.setCnpj(orgaoEntidade.getCnpj());
        orgao.setRazaoSocial(orgaoEntidade.getRazaoSocial());
        orgao.setUfNome(orgaoEntidade.getUfNome());
        orgao.setNomeUnidade(orgaoEntidade.getNomeUnidade());
        orgao.setCodigoUnidade(orgaoEntidade.getCodigoUnidade());
        orgao.setUfSigla(orgaoEntidade.getUfSigla());
        orgao.setMunicipioNome(orgaoEntidade.getMunicipioNome());
        orgao.setCodigoIbge(orgaoEntidade.getCodigoIbge());

        // Extrai os contratos e associa ao órgão
        List<Contrato> contratos = response.getContratos().stream().map(contratoResponse -> {
            Contrato contrato = new Contrato();
            contrato.setDataVigenciaIni(LocalDate.parse(contratoResponse.getDataVigenciaInicio()));
            contrato.setDataVigenciaFim(LocalDate.parse(contratoResponse.getDataVigenciaFim()));
            contrato.setRazaoSocialFornecedor(contratoResponse.getNomeRazaoSocialFornecedor());
            contrato.setObjetoContrato(contratoResponse.getObjetoContrato());
            contrato.setValorInicial(contratoResponse.getValorInicial());
            contrato.setOrgao(orgao);
            return contrato;
        }).toList();

        // Associa os contratos ao órgão
        orgao.setContratos(contratos);

        // Salva o órgão e os contratos no banco de dados
        return orgaoRepository.save(orgao);
    }

    private void cleanDatabase() {
        contratoRepository.deleteAll();  // Limpa todos os registros da tabela contrato
        orgaoRepository.deleteAll();     // Limpa todos os registros da tabela orgao
    }

}
