package com.example.api_pncp.Model.Orgao;

import java.util.List;

import com.example.api_pncp.Model.Pesquisa.Pesquisa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade que será alvo de ORM para a persistência de dados no PostgreSQL
 */

@Entity
@Table(name = "orgao")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Orgao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "orgao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pesquisa> pesquisa;

    public Orgao(OrgaoRequestDTO body) {
        this.cnpj = body.cnpj();
    }
    
}
