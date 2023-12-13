package com.ods.agro.services;

import com.ods.agro.enterprise.ValidationException;
import com.ods.agro.entities.Cliente;
import com.ods.agro.repositories.ClienteRepository;
import com.ods.agro.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Cliente salvarCliente(Cliente cliente){

        if (clienteRepository.findByCnpj(cliente.getCnpj()) != null ||
            fornecedorRepository.findByCnpj(cliente.getCnpj()) != null){

            throw new ValidationException("CNPJ já existente na nossa base de dados!");
        }

        if (clienteRepository.findByRazaoSocial(cliente.getRazaoSocial()) != null ||
            fornecedorRepository.findByRazaoSocial(cliente.getRazaoSocial()) != null){

            throw new ValidationException("Razão social já cadastrada na nossa base de dados");
        }

        if (clienteRepository.findByNomeFantasia(cliente.getNomeFantasia()) != null ||
            fornecedorRepository.findByNomeFantasia(cliente.getNomeFantasia()) != null){

            throw new ValidationException("Nome fantasia já cadastrado na nossa base dados!");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente buscaPorId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public List<Cliente> buscaTodos(){
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(Long id, Cliente modificado){
        Optional<Cliente> optional = clienteRepository.findById(id);

        if (optional.isPresent()){
            var cliente = optional.get();
            cliente.setEndereco(modificado.getEndereco());
            return clienteRepository.save(cliente);
        }

        return null;
    }

    public void deletarPorId(Long id){
        clienteRepository.deleteById(id);
    }
}
