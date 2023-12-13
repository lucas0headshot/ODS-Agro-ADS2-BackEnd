package com.ods.agro.controller;

import com.ods.agro.entities.Venda;
import com.ods.agro.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController extends AbstractController{

    @Autowired
    VendaService service;

    @PostMapping
    public ResponseEntity salvarVenda(@RequestBody Venda venda){
        Venda salvar = service.salvarVenda(venda);
        return ResponseEntity.created(URI.create("/api/venda" + venda.getId())).body(salvar);
    }

    @GetMapping
    public  ResponseEntity lerVenda() {
        List<Venda> advogados = service.buscaTodos();
        return  ResponseEntity.ok(advogados);
    }

    @GetMapping("/{id}")
    public  ResponseEntity trazerporId(@PathVariable("id") Long id){
        Venda Venda = service.buscaPorId(id);
        return ResponseEntity.ok(Venda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarVenda(@PathVariable("id")Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarVenda(@PathVariable("id") Long id, @RequestBody Venda entity) {
        return ResponseEntity.ok().body(service.atualizarVenda(id, entity));
    }
}
