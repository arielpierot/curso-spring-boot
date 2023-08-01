package com.github.cursospringboot.mappers;

import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.models.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target= "id", ignore = true)
    Cliente toModel(ClienteDTO clienteDTO);

    @InheritInverseConfiguration
    ClienteDTO toDTO(Cliente cliente);
}
