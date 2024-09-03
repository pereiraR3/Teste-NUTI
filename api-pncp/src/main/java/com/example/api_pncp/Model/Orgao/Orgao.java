package com.example.api_pncp.Model.Orgao;

import java.util.List;

import com.example.api_pncp.Model.Contrato.Contrato;

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
 * Entidade que será alvo de ORM para a persistência de dados no PostgreSQL.
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

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "uf_nome", nullable = false)
    private String ufNome;

    @Column(name = "nome_unidade", nullable = false)
    private String nomeUnidade;

    @Column(name = "codigo_unidade", nullable = false)
    private String codigoUnidade;

    @Column(name = "uf_sigla", nullable = false)
    private String ufSigla;

    @Column(name = "municipio_nome", nullable = false)
    private String municipioNome;

    @Column(name = "codigo_ibge", nullable = false)
    private String codigoIbge;

    @OneToMany(mappedBy = "orgao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrato> contratos;

    public Orgao(OrgaoRequestDTO body) {
        this.cnpj = body.cnpj();
        this.razaoSocial = body.razaoSocial();
        this.ufNome = body.ufNome();
        this.nomeUnidade = body.nomeUnidade();
        this.codigoUnidade = body.codigoUnidade();
        this.ufSigla = body.ufSigla();
        this.municipioNome = body.municipioNome();
        this.codigoIbge = body.codigoIbge();
    }

    public void addContratos(List<Contrato> contratos) {
        this.contratos.addAll(contratos);
    }

}
