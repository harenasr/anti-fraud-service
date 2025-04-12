package com.interbank.demo.antifraudservice.repository;

import com.interbank.demo.antifraudservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {}
