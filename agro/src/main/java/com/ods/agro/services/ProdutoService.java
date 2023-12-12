package com.ods.agro.services;

import com.ods.agro.enterprise.ValidationException;
import com.ods.agro.entities.Produto;
import com.ods.agro.repositories.ProdutoRepository;
import com.ods.agro.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto){

        if (produtoRepository.findByNome(produto.getNome()) != null){
            throw new ValidationException("Produto já cadastrado no estoque!");
        }
        return produtoRepository.save(produto);
    }

    public Produto buscaPorId(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> buscaTodos(){
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Long id, Produto modificado){
        Optional<Produto> optional = produtoRepository.findById(id);

        if (optional.isPresent()){
            var produto = new Produto();
            produto.setNome(modificado.getNome());
            produto.setPeso(modificado.getPeso());
            produto.setValor(modificado.getValor());
            produto.setDataProducao(modificado.getDataProducao());
            produto.setDataValidade(modificado.getDataValidade());
            produto.setFornecedor(modificado.getFornecedor());
            produto.setQtdEstoque(modificado.getQtdEstoque());
            return produtoRepository.save(produto);
        }

        return  null;
    }

    public void deletarPorId(Long id){
        produtoRepository.deleteById(id);
    }
}
