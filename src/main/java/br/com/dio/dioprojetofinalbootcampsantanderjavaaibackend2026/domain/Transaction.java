package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Transaction {
    private TransactionId id;
    private String description;
    private long amount;
    private Category category;
    private Instant createdAt;

    public Transaction(String description, long amount, Category category) {
        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.createdAt = Instant.now();
    }

    public Transaction withCategory(Category newCategory) {
        return new Transaction(this.id, this.description, this.amount, newCategory, this.createdAt);
    }

}
