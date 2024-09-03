package com.example.api_pncp.Scheduled;

import com.example.api_pncp.Service.DatabaseInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDatabaseInsertTask {

    @Autowired
    private DatabaseInsertService databaseInsertService;

//    // Executa o insert 10 segundos após a inicialização
//    @Scheduled(initialDelay = 10000, fixedDelay = Long.MAX_VALUE)  // Executa apenas uma vez, 10 segundos após o início
//    public void executeInsertTask() {
//        databaseInsertService.insertOrgaosAndContratos();
//        System.out.println("Dados inseridos no banco de dados.");
//    }

}
