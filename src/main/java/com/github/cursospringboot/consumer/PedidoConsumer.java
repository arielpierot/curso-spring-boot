package com.github.cursospringboot.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cursospringboot.controllers.ClienteController;
import com.github.cursospringboot.dto.ClienteDTO;
import com.github.cursospringboot.services.ClienteService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PedidoConsumer {

    @Autowired
    private  ClienteService clienteService;

    private static final Logger logger = LogManager.getLogger(ClienteController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.consumer-group-id}")
    public void consume(ConsumerRecord<String, String> kafkaRecord, Acknowledgment ack) throws JsonProcessingException, JsonMappingException {
        long kafkaRecordOffset = kafkaRecord.offset();
        String value = kafkaRecord.value();
        logger.info("Ack offset: {}, message: {}", kafkaRecordOffset, value);

        try {
            ClienteDTO clienteDTO = objectMapper.readValue(value, ClienteDTO.class);
            clienteService.criar(clienteDTO);
        }
        catch (IOException e) {
            logger.error("Erro ao converter String (JSON) para Objeto.", e);
        }
        ack.acknowledge();
    }
}
