package com.ods.agro.services;

import com.ods.agro.enterprise.ValidationException;
import com.ods.agro.entities.Fornecedor;
import com.ods.agro.repositories.ClienteRepository;
import com.ods.agro.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Fornecedor salvarFornecedor(Fornecedor fornecedor){
        if (fornecedorRepository.findByCnpj(fornecedor.getCnpj()) != null ||
            clienteRepository.findByCnpj(fornecedor.getCnpj()) != null){

            throw new ValidationException("CNPJ já cadastrado na nossa base de dados!");
        }

        if (fornecedorRepository.findByRazaoSocial(fornecedor.getRazaoSocial()) != null ||
            clienteRepository.findByRazaoSocial(fornecedor.getRazaoSocial()) != null){

            throw new ValidationException("Razão social já cadastrada na nossa base dados!");
        }

        if (fornecedorRepository.findByNomeFantasia(fornecedor.getNomeFantasia()) != null ||
            clienteRepository.findByNomeFantasia(fornecedor.getNomeFantasia()) != null){

            throw new ValidationException("Nome fantasia já cadastrado na nossa base de dados!");
        }

        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor buscaPorId(Long id){
        return fornecedorRepository.findById(id).orElse(null);
    }

    public List<Fornecedor> buscaTodos(){
        return fornecedorRepository.findAll();
    }

    public Fornecedor atualizarFornecedor(Long id, Fornecedor modificado){
        Optional<Fornecedor> optional = fornecedorRepository.findById(id);

        if (optional.isPresent()){
            var fornecedor = optional.get();
            fornecedor.setEndereco(modificado.getEndereco());
            return fornecedorRepository.save(fornecedor);
        }
        return null;
    }

    public void deletarPorId(Long id){
        fornecedorRepository.deleteById(id);
    }
}
