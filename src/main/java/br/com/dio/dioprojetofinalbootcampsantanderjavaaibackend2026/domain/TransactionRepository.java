package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Page<Transaction> findAllByCategory(Category category, Pageable pageable);
    List<Transaction> findAllByCategoryAndCreatedAtBetween(Category category, Instant start, Instant end);
    Page<Transaction> findAllByCreatedAtBetween(Instant start, Instant end, Pageable pageable);

    void deleteById(TransactionId id);

    Optional<Transaction> findById(TransactionId id);

}