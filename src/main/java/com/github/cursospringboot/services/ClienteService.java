package com.github.cursospringboot.services;

import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.mappers.ClienteMapper;
import com.github.cursospringboot.models.Cliente;
import com.github.cursospringboot.repositories.ClienteRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente criar(ClienteDTO dto) {
        return repository.insert(ClienteMapper.INSTANCE.toModel(dto));
    }

    public Cliente atualizar(String id, ClienteDTO dto){
        Query query = new Query();
        query.addCriteria(Criteria.where(Cliente.Fields.id).is(id));
        Update update = new Update();
        update.set(Cliente.Fields.nome, dto.getNome());
        mongoTemplate.updateFirst(query, update, Cliente.class);
        return mongoTemplate.findOne(query, Cliente.class);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}
