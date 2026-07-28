package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.persistence.repository;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    Page<TransactionEntity> findAllByCategory(Category category, Pageable pageable);
    List<TransactionEntity> findAllByCategoryAndCreatedAtBetween(Category category, Instant start, Instant end);
    Page<TransactionEntity> findAllByCreatedAtBetween(Instant start, Instant end, Pageable pageable);
}