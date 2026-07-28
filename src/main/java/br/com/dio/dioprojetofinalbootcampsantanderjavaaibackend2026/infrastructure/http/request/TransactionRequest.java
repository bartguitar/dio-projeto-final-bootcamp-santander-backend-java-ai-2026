package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http.request;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.input.PersistTransactionInput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(
    @NotBlank(message = "A descrição é obrigatória") String description,
    @NotNull(message = "A categoria é obrigatória") Category category,
    @Positive(message = "O valor deve ser maior que zero") long amount) {
    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }
}