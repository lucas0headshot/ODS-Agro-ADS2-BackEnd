package com.ods.agro.repositories;

import com.ods.agro.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findByNome(String nome);
}
