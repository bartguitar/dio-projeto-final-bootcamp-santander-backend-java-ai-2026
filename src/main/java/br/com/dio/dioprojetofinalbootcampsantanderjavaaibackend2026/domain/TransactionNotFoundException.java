package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain;


public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(TransactionId id) {
        super("Transação não encontrada: " + id.uuid());
    }
}