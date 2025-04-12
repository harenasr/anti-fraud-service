package com.interbank.demo.antifraudservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TransactionType {
    @Id
    private Long id;
    private String name;
}
