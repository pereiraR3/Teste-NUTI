package com.example.api_pncp.Model.Pesquisa;

import com.example.api_pncp.Model.Orgao.Orgao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que será alvo de ORM para a persistência de dados no PostgreSQL
 */

@Entity
@Table(name = "pesquisa")
@Data
@EqualsAndHashCode(of = "id")
public class Pesquisa {

    @Id
    private Long id;
    
    private Orgao orgao;

    private Double valor_total;

    public Pesquisa(PesquisaRequestDTO body, Orgao orgao) {
        this.orgao = orgao;
        this.valor_total = body.valor_total();
    }
    
}
