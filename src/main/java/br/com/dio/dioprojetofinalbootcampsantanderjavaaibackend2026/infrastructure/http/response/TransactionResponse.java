package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http.response;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output.TransactionOutput;

public record TransactionResponse(String id, String category, String description, double amount) {
    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(output.id(), output.category(), output.description(), output.value());
    }
}