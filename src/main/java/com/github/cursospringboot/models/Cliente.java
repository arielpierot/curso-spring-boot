package com.github.cursospringboot.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Cliente {

    @Id
    private String id;
    private String nome;
    private String email;
}
