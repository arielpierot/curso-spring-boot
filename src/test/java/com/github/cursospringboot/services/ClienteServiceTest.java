package com.github.cursospringboot.services;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.models.Cliente;
import com.github.cursospringboot.repositories.ClienteRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @BeforeEach
    void setup(){
        FixtureFactoryLoader.loadTemplates("fixtures");
    }

    @Test
    void testListar(){
        Cliente cliente = Mockito.mock(Cliente.class);
        Mockito.when(repository.findAll()).thenReturn(List.of(cliente));
        List<Cliente> lista = service.listarClientes();
        Assertions.assertEquals(1, lista.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void testCriar(){
        ClienteDTO clienteDTO = Mockito.mock(ClienteDTO.class);
        Cliente clienteMock = Fixture.from(Cliente.class).gimme("outros");
        Mockito.when(repository.insert(Mockito.any(Cliente.class))).thenReturn(clienteMock);
        Cliente clienteSalvo = service.criar(clienteDTO);
        Assertions.assertEquals("Paulo", clienteSalvo.getNome());
        Assertions.assertEquals(clienteMock.getEmail(), clienteSalvo.getEmail());
        Assertions.assertEquals(clienteMock.getId(), clienteSalvo.getId());
        Mockito.verify(repository, Mockito.times(1)).insert(Mockito.any(Cliente.class));
    }

}
