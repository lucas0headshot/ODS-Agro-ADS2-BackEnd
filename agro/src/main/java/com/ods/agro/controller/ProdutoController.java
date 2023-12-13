package com.ods.agro.controller;

import com.ods.agro.entities.Produto;
import com.ods.agro.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController extends AbstractController{

    @Autowired
    ProdutoService service;

    @PostMapping
    public ResponseEntity salvarProduto(@RequestBody Produto produto){
        Produto salvar = service.salvarProduto(produto);
        return ResponseEntity.created(URI.create("/api/produto" + produto.getId())).body(salvar);
    }

    @GetMapping
    public ResponseEntity lerProduto(){
        List<Produto> produto = service.buscaTodos();
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarporId(@PathVariable("id") Long id){
        Produto produto = service.buscaPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarProduto(@PathVariable("id")Long id, @RequestBody Produto entity){
        return ResponseEntity.ok().body(service.atualizarProduto(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removerProduto(@PathVariable("id")Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
