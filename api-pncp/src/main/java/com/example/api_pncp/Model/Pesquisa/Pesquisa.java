package com.example.api_pncp.Model.Pesquisa;
import java.time.LocalDate;

import com.example.api_pncp.Model.Orgao.Orgao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade que será alvo de ORM para a persistência de dados no PostgreSQL.
 */

@Entity
@Table(name = "pesquisa")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Pesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_orgao", nullable = false)
    private Orgao orgao;

    @Column(name = "data_realizacao", nullable = false)
    private LocalDate dataRealizacao = LocalDate.now();

    @Column(name = "data_ini", nullable = false)
    private LocalDate dataIni;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    public Pesquisa(PesquisaRequestDTO body, Orgao orgao) {
        this.orgao = orgao;
        this.valorTotal = body.valorTotal();
        this.dataIni = body.dataIni();
        this.dataFim = body.dataFim();
    }
}