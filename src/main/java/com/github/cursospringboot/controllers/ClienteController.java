package com.github.cursospringboot.controllers;

import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.models.Cliente;
import com.github.cursospringboot.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<?> listar(){
        var listaClientes = service.listarClientes();
        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteCriado = service.criar(clienteDTO);
        return new ResponseEntity<>(clienteCriado, HttpStatus.OK);
    }
}
