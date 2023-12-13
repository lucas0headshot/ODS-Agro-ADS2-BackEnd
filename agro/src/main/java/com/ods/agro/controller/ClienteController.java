package com.ods.agro.controller;

import com.ods.agro.entities.Cliente;
import com.ods.agro.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping
public class ClienteController extends AbstractController {

    @Autowired
    ClienteService service;

    @PostMapping
    public ResponseEntity salvarCliente(@RequestBody Cliente cliente){
        Cliente salvar = service.salvarCliente(cliente);
        return ResponseEntity.created(URI.create("/api/cliente" + cliente.getId())).body(salvar);
    }

    @GetMapping
    public ResponseEntity lerCliente() {
        List<Cliente> cliente = service.buscaTodos();
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity bucscarporId(@PathVariable("id")Long id){
        Cliente cliente = service.buscaPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarCliente(@PathVariable("id")Long id,@RequestBody Cliente entity){
        return ResponseEntity.ok().body(service.atualizarCliente(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPorId(@PathVariable("id")Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
