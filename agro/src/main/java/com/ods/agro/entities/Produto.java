package com.ods.agro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "produto")
@Getter
@Setter
public class Produto extends EntityID{

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private Fornecedor fornecedor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "dt_producao")
    private LocalDate dataProducao;

    @Column(name = "dt_validade")
    private LocalDate dataValidade;
}
