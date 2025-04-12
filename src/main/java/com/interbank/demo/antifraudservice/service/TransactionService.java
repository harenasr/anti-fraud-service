package com.interbank.demo.antifraudservice.service;

import com.interbank.demo.antifraudservice.dto.TransactionRequest;
import com.interbank.demo.antifraudservice.dto.TransactionResponse;
import com.interbank.demo.antifraudservice.exception.TransactionTypeNotFoundException;
import com.interbank.demo.antifraudservice.messaging.TransactionPublisher;
import com.interbank.demo.antifraudservice.model.Transaction;
import com.interbank.demo.antifraudservice.model.TransactionStatus;
import com.interbank.demo.antifraudservice.model.TransactionType;
import com.interbank.demo.antifraudservice.repository.TransactionRepository;
import com.interbank.demo.antifraudservice.repository.TransactionStatusRepository;
import com.interbank.demo.antifraudservice.repository.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final TransactionStatusRepository statusRepository;
    private final TransactionTypeRepository typeRepository;
    private final TransactionPublisher publisher;

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request) {
        // Crear transacción
        Transaction tx = new Transaction();
        tx.setAccountExternalIdDebit(request.getAccountExternalIdDebit());
        tx.setAccountExternalIdCredit(request.getAccountExternalIdCredit());
        tx.setTranferTypeId(request.getTranferTypeId());
        tx.setValue(request.getValue());
        tx.setCreatedAt(Instant.now());

        // Establecer estado inicial de la transacción como 'PENDIENTE'
        TransactionStatus status = statusRepository.findByName("PENDIENTE");
        tx.setTransactionStatus(status);

        // Obtener el tipo de transacción, lanzando una excepción si no se encuentra
        TransactionType type = typeRepository.findById(Long.valueOf(request.getTranferTypeId()))
                .orElseThrow(() -> new TransactionTypeNotFoundException("Tipo de transacción no encontrado"));
        tx.setTransactionType(type);

        // Guardar la transacción en la base de datos
        tx = repository.save(tx);

        // Lógica de antifraude: Si el valor es mayor a 1000, rechazar, de lo contrario aprobar
        String finalStatus = request.getValue().compareTo(BigDecimal.valueOf(1000)) > 0 ? "RECHAZADO" : "APROBADO";
        tx.setTransactionStatus(statusRepository.findByName(finalStatus));
        tx = repository.save(tx);

        // Publicar el evento
        publisher.send(tx);

        return toResponse(tx);
    }

    private TransactionResponse toResponse(Transaction tx) {
        return new TransactionResponse(
                tx.getTransactionExternalId(),
                tx.getTransactionStatus().getName(),
                tx.getTransactionType().getName(),
                tx.getValue(),
                tx.getCreatedAt()
        );
    }

    public TransactionResponse getById(UUID id) {
        return repository.findById(id).map(this::toResponse).orElseThrow();
    }
}
