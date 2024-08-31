package com.example.api_pncp.Model.Orgao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que será alvo de ORM para a persistência de dados no PostgreSQL
 */

@Entity
@Table(name = "contratos")
@Data
@EqualsAndHashCode(of = "id")
public class Orgao {
    
    @Id
    private Long id;

    private String nome;

    public Orgao(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
