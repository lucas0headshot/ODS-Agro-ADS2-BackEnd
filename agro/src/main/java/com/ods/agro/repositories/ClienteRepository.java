package com.ods.agro.repositories;

import com.ods.agro.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByCnpj(String cnpj);
    public Cliente findByRazaoSocial(String razaoSocial);
    public Cliente findByNomeFantasia(String nomeFantasia);
}
