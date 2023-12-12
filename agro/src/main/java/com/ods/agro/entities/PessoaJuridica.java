package com.ods.agro.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class PessoaJuridica extends EntityID{

    @Column(name = "razao_social", length = 45)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 45)
    private String nomeFantasia;

    @Column(name = "cnpj", length = 14)
    private String cnpj;

    @Column(name = "endereco", length = 500)
    private String endereco;
}
