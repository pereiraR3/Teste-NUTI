package com.example.api_pncp.Model.Contrato;

import java.time.LocalDate;

import com.example.api_pncp.Model.Orgao.Orgao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um contrato obtido em uma pesquisa.
 */

@Entity
@Table(name = "contrato")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_orgao", nullable = false)
    private Orgao orgao;

    @Column(name = "data_vigencia_ini", nullable = false)
    private LocalDate dataVigenciaIni;

    @Column(name = "data_vigencia_fim", nullable = false)
    private LocalDate dataVigenciaFim;

    @Column(name = "razao_social_fornecedor", nullable = false, columnDefinition = "TEXT")
    private String razaoSocialFornecedor;

    @Column(name = "objeto_contrato", nullable = false, columnDefinition = "TEXT")
    private String objetoContrato;

    @Column(name = "valor_inicial", nullable = false)
    private Double valorInicial;

    public Contrato(ContratoRequestDTO body, Orgao orgao) {
        this.dataVigenciaIni = body.dataVigenciaIni();
        this.dataVigenciaFim = body.dataVigenciaFim();
        this.razaoSocialFornecedor = body.razaoSocialFornecedor();
        this.objetoContrato = body.objetoContrato();
        this.valorInicial = body.valorInicial();
        this.orgao = orgao;
    }
}
