package com.interbank.demo.antifraudservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interbank.demo.antifraudservice.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TransactionPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(Transaction tx) {
        Map<String, Object> msg = Map.of(
                "transactionExternalId", tx.getTransactionExternalId().toString(),
                "status", tx.getTransactionStatus().getName()
        );
        try {
            kafkaTemplate.send("transactions", new ObjectMapper().writeValueAsString(msg));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
