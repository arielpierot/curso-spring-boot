package com.github.cursospringboot.controllers;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.models.Cliente;
import com.github.cursospringboot.services.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService service;

    @InjectMocks
    private ClienteController controller;

    @BeforeEach
    void setup(){
        FixtureFactoryLoader.loadTemplates("fixtures");
    }

    @Test
    void testListar() {
        Cliente cliente = mock(Cliente.class);
        when(service.listarClientes()).thenReturn(List.of(cliente));
        var response = controller.listar();
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertSame(Cliente.class, response.getBody().get(0).getClass());
        verify(service, times(1)).listarClientes();
    }

    @Test
    void testCriar(){
        ClienteDTO clienteDTO = mock(ClienteDTO.class);
        Cliente clienteMock = Fixture.from(Cliente.class).gimme("base");
        when(service.criar(clienteDTO)).thenReturn(clienteMock);
        var response = controller.criar(clienteDTO);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(response.getBody());
        verify(service, times(1)).criar(clienteDTO);
    }

}
