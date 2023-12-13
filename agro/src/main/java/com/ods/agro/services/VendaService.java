package com.ods.agro.services;

import com.ods.agro.enterprise.ValidationException;
import com.ods.agro.entities.Produto;
import com.ods.agro.entities.Venda;
import com.ods.agro.repositories.ProdutoRepository;
import com.ods.agro.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda salvarVenda(Venda venda){
        Optional<Produto> byId = produtoRepository.findById(venda.getProduto().getId());
        if (venda.getQtdVendida() > byId.get().getQtdEstoque()){
            throw new ValidationException("Não há estoque suficiente");
        }

        if (venda.getQtdVendida() == 0){
            throw new ValidationException("Quantidade da venda deve ser maior que zero");
        }

        venda.getProduto().setQtdEstoque(byId.get().getQtdEstoque() - venda.getQtdVendida());
        return vendaRepository.save(venda);
    }

    public Venda buscaPorId(Long id){
        return vendaRepository.findById(id).orElse(null);
    }

    public List<Venda> buscaTodos(){
        return  vendaRepository.findAll();
    }

    public Venda atualizarVenda(Long id, Venda modificado){
        Optional<Venda> optional = vendaRepository.findById(id);

        if (optional.isPresent()){
            var venda = optional.get();
            venda.setDataVenda(modificado.getDataVenda());
            venda.setProduto(modificado.getProduto());
            venda.setCliente(modificado.getCliente());
            venda.setQtdVendida(modificado.getQtdVendida());
            return vendaRepository.save(venda);
        }
        return null;
    }

    public void deletarPorId(Long id){
        vendaRepository.deleteById(id);
    }
}
