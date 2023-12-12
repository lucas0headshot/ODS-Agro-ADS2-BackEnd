package com.ods.agro.entities;

import com.ods.agro.enums.Movimentos;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "estoque")
@Getter
@Setter
public class Estoque extends EntityID{

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Column(name = "movimento")
    @Enumerated(EnumType.STRING)
    private Movimentos movimento;

    @Column(name = "qtd_movimentada")
    private Integer qtdMovimentada;

    @Column(name = "dt_movimento")
    private LocalDate dataMovimento;
}
