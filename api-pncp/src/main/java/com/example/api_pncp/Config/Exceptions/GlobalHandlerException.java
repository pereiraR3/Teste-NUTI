package com.example.api_pncp.Config.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerException.class);

    /**
     * Trata exceções de validação geradas automaticamente pelos dados inconsistentes verificados por @Validation.
     * Retorna um status HTTP 400 (Bad Request) com detalhes dos campos e mensagens de erro.
     *
     * @param ex a exceção MethodArgumentNotValidException lançada quando os argumentos do método são inválidos
     * @return uma ResponseEntity contendo um mapa de campos e mensagens de erro
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.error("Erro de validação: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Trata exceções lançadas quando uma mensagem HTTP não é legível, como quando o JSON está malformado.
     * Retorna um status HTTP 400 (Bad Request) com uma mensagem de erro específica.
     *
     * @param ex a exceção HttpMessageNotReadableException lançada quando a mensagem HTTP não pode ser lida
     * @return uma ResponseEntity contendo uma mensagem de erro e detalhes da exceção
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", "Requisição JSON malformada");
        error.put("mensagem", ex.getLocalizedMessage());
        logger.error("Mensagem HTTP ilegível: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Trata exceções de violação de integridade de dados, como quando há tentativa de criar um recurso que já existe.
     * Retorna um status HTTP 409 (Conflict) com uma mensagem de erro específica.
     *
     * @param ex a exceção DataIntegrityViolationException lançada quando há uma violação de integridade de dados
     * @return uma ResponseEntity contendo uma mensagem de erro e detalhes da exceção
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", "Violação de integridade de dados");
        error.put("mensagem", "O recurso que você está tentando criar já existe.");
        logger.error("Violação de integridade de dados: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Trata exceções lançadas quando uma entidade não é encontrada.
     * Retorna um status HTTP 404 (Not Found) com uma mensagem de erro específica.
     *
     * @param ex a exceção EntityNotFoundException lançada quando a entidade não é encontrada
     * @return uma ResponseEntity contendo uma mensagem de erro e detalhes da exceção
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", "Entidade não encontrada");
        error.put("mensagem", ex.getLocalizedMessage());
        logger.error("Entidade não encontrada: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Trata exceções lançadas quando um argumento ilegal é fornecido.
     * Retorna um status HTTP 400 (Bad Request) com uma mensagem de erro específica.
     *
     * @param ex a exceção IllegalArgumentException lançada quando um argumento ilegal é fornecido
     * @return uma ResponseEntity contendo uma mensagem de erro e detalhes da exceção
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", "Argumento ilegal");
        error.put("mensagem", ex.getLocalizedMessage());
        logger.error("Argumento ilegal: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}
