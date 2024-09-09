package com.example.api_pncp.Scheduled;

import com.example.api_pncp.Repository.OrgaoRepository;
import com.example.api_pncp.Service.DatabaseInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDatabaseInsertTask {

    @Autowired
    private DatabaseInsertService databaseInsertService;

    @Autowired
    private OrgaoRepository orgaoRepository;

    // Executa o insert 10 segundos após a inicialização
    @Scheduled(initialDelay = 10000, fixedDelay = Long.MAX_VALUE)  // Executa apenas uma vez, 10 segundos após o início
    public void executeInsertTask() {

        if(!orgaoRepository.findByCnpj("11111111111111").isPresent()){
            databaseInsertService.insertOrgaosAndContratos();
            System.out.println("Dados inseridos no banco de dados.");
        }else{
            System.out.println("Dados já foram inseridos.");
        }

    }

}