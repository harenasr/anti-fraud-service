package com.interbank.demo.antifraudservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
public class TransactionResponse {
    private UUID transactionExternalId;
    private String transactionStatus;
    private String transactionType;
    private BigDecimal value;
    private Instant createdAt;
    private UUID accountExternalIdDebit;

    public TransactionResponse(UUID transactionExternalId, String name, String name1, BigDecimal value, Instant createdAt) {
        this.transactionExternalId = transactionExternalId;
        this.transactionStatus = name;
        this.transactionType = name1;
        this.value = value;
        this.createdAt = createdAt;
    }
}
