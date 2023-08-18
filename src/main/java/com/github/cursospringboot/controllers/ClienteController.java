package com.github.cursospringboot.controllers;

import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.models.Cliente;
import com.github.cursospringboot.services.ClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    private static final Logger logger = LogManager.getLogger(ClienteController.class);

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        var listaClientes = service.listarClientes();
        logger.info("Clientes listados com sucesso");
        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteCriado = service.criar(clienteDTO);
        return new ResponseEntity<>(clienteCriado, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> atualizar(@RequestBody ClienteDTO clienteDTO, @PathVariable String id){
        Cliente clienteAtualizado = service.atualizar(id, clienteDTO);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
