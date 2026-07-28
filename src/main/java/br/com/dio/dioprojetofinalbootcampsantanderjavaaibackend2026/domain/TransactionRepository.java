package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    List<Transaction> findAllByCategory(Category category);
    List<Transaction> findAllByCategoryAndCreatedAtBetween(Category category, Instant start, Instant end);
    List<Transaction> findAllByCreatedAtBetween(Instant start, Instant end);

    void deleteById(TransactionId id);

    Optional<Transaction> findById(TransactionId id);

}