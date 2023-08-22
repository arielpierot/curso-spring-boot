package com.github.cursospringboot.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

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
