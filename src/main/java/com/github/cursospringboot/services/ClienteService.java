package com.github.cursospringboot.services;

import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.mappers.ClienteMapper;
import com.github.cursospringboot.models.Cliente;
import com.github.cursospringboot.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente criar(ClienteDTO dto) {
        return repository.insert(ClienteMapper.INSTANCE.toModel(dto));
    }

    public Cliente atualizar(String id, ClienteDTO dto){
        Cliente cliente = repository.findById(id).orElseThrow();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        repository.save(cliente);
        return cliente;
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}
