package com.interbank.demo.antifraudservice.repository;

import com.interbank.demo.antifraudservice.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Long> {
    TransactionStatus findByName(String name);
}
