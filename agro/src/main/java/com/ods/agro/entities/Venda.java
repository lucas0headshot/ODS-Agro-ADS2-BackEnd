package com.ods.agro.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "venda")
@Getter
@Setter
public class Venda extends EntityID{

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Column(name = "qtd_vendida")
    private Double qtdVendida;

    @Column(name = "dt_venda")
    private LocalDate dataVenda;

    public Venda(){
        this.dataVenda = LocalDate.now();
    }
}
