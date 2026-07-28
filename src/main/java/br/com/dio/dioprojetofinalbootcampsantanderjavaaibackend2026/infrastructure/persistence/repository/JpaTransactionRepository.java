package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.persistence.repository;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Transaction;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionId;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionRepository;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaTransactionRepository implements TransactionRepository {
    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return transactionEntityRepository.findAllByCategory(category)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }

    @Override
    public List<Transaction> findAllByCategoryAndCreatedAtBetween(Category category, Instant start, Instant end) {
        return transactionEntityRepository.findAllByCategoryAndCreatedAtBetween(category, start, end)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }

    @Override
    public List<Transaction> findAllByCreatedAtBetween(Instant start, Instant end) {
        return transactionEntityRepository.findAllByCreatedAtBetween(start, end)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(TransactionId id) {
        transactionEntityRepository.deleteById(id.uuid());
    }

    @Override
    public Optional<Transaction> findById(TransactionId id) {
        return transactionEntityRepository.findById(id.uuid()).map(TransactionEntity::toDomain);
    }

}