package com.interbank.demo.antifraudservice.repository;

import com.interbank.demo.antifraudservice.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
