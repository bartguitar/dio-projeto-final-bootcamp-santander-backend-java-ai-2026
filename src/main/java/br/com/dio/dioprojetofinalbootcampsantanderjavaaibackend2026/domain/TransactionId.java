package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain;

import java.util.UUID;

public record TransactionId(UUID uuid) {
    public TransactionId() {
        this(UUID.randomUUID());
    }
}
