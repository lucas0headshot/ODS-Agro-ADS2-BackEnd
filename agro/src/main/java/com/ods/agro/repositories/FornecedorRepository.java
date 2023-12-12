package com.ods.agro.repositories;

import com.ods.agro.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    public Fornecedor findByCnpj(String cnpj);
    public Fornecedor findByRazaoSocial(String razaoSocial);
    public Fornecedor findByNomeFantasia(String nomeFantasia);
}
