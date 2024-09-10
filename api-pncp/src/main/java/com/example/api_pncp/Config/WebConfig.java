package com.example.api_pncp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// A anotação @Configuration indica que esta classe é uma classe de configuração do Spring.
// Ela será automaticamente escaneada pelo Spring e aplicada na configuração do projeto.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // O método addCorsMappings é sobrescrito da interface WebMvcConfigurer para configurar CORS.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Aqui estamos adicionando configurações de CORS para todos os endpoints da API ("/**" significa qualquer rota).
        registry.addMapping("/**")
                // allowedOrigins define quais origens podem fazer requisições à API.
                .allowedOrigins(
                        "https://teste-nuti.vercel.app/",  // Permite que o domínio teste-nuti possa fazer requisições
                        "http://localhost:5173",            // Permite requisições vindas de localhost na porta 5173
                        "http://localhost:5174"             // Permite localhost com a porta 5174 (talvez outra instância da aplicação)
                )
                // allowedMethods define os métodos HTTP permitidos nas requisições (GET, POST, PUT, DELETE, OPTIONS)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // allowedHeaders define quais cabeçalhos podem ser utilizados na requisição
                .allowedHeaders("*")  // Aqui estamos permitindo todos os cabeçalhos
                // allowCredentials indica se cookies ou outras credenciais podem ser enviadas junto com a requisição
                .allowCredentials(true)
                // maxAge define o tempo que a requisição "preflight" (de verificação de permissões) pode ser cacheada pelo navegador.
                .maxAge(3600); // 3600 segundos = 1 hora
    }
}
