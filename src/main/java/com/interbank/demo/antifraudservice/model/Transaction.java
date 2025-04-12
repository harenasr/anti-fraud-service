package com.interbank.demo.antifraudservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue
    private UUID transactionExternalId;

    private UUID accountExternalIdDebit;
    private UUID accountExternalIdCredit;
    private Integer tranferTypeId;
    private BigDecimal value;
    private Instant createdAt;

    @ManyToOne
    private TransactionStatus transactionStatus;

    @ManyToOne
    private TransactionType transactionType;
}
