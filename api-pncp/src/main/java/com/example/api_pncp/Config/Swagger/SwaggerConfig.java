package com.example.api_pncp.Config.Swagger; // Declara o pacote onde esta classe está localizada

import io.swagger.v3.oas.models.OpenAPI; // Importa a classe OpenAPI do Swagger para configurar a documentação da API
import io.swagger.v3.oas.models.info.Info; // Importa a classe Info para adicionar informações sobre a API
import org.springframework.context.annotation.Bean; // Importa a anotação @Bean para declarar o método como um bean Spring
import org.springframework.context.annotation.Configuration; // Importa a anotação @Configuration para marcar a classe como uma configuração Spring

@Configuration // Indica que esta classe contém a configuração do Spring
public class SwaggerConfig {

    /**
     * Método que define a configuração personalizada do Swagger/OpenAPI para a aplicação.
     * O método é marcado com @Bean, o que significa que será gerenciado pelo Spring e
     * estará disponível no contexto da aplicação.
     *
     * @return OpenAPI - A configuração personalizada da API para o Swagger.
     */
    @Bean // Declara este método como um bean do Spring que será gerenciado pelo container
    public OpenAPI customOpenAPI() {
        // Cria e retorna uma instância de OpenAPI com informações customizadas sobre a API
        return new OpenAPI()
                .info(new Info()
                        .title("API - Teste Prático - NUTI") // Título da documentação da API
                        .version("1.0") // Versão da API
                        .description("Documentação da API")); // Descrição da API
    }
}
