package com.ods.agro.controller;

import com.ods.agro.entities.Fornecedor;
import com.ods.agro.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController extends AbstractController {

    @Autowired
    FornecedorService service;

    @PostMapping
    public ResponseEntity salvarFornecedor(@RequestBody Fornecedor fornecedor){
        Fornecedor salvar = service.salvarFornecedor(fornecedor);
        return ResponseEntity.created(URI.create("/api/fornecedor" + fornecedor.getId())).body(salvar);
    }

    @GetMapping
    public ResponseEntity lerFornecedor() {
        List<Fornecedor> fornecedor = service.buscaTodos();
        return ResponseEntity.ok(fornecedor);
    }

    @GetMapping("/{id}")
    public  ResponseEntity buscaporId(@PathVariable("id")Long id){
        Fornecedor fornecedor = service.buscaPorId(id);
        return ResponseEntity.ok(fornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarFornecedor(@PathVariable("id")Long id, @RequestBody Fornecedor entity) {
        return ResponseEntity.ok().body(service.atualizarFornecedor(id, entity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deletarPorId(@PathVariable("id")Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
