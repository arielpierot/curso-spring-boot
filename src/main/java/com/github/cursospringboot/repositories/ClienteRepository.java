package com.github.cursospringboot.repositories;

import ch.qos.logback.core.net.server.Client;
import com.github.cursospringboot.models.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    @Query("{ 'id': '?0' }")
    Optional<Cliente> findById(String id);

    @Query(value = "{'id' : '?0'}", delete = true)
    void deleteById(String id);
}
