package com.github.cursospringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private String nome;
    private String email;
}
