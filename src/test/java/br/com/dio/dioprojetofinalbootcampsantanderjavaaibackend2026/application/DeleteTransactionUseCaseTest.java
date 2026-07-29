package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeleteTransactionUseCaseTest {

    private final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
    private final DeleteTransactionUseCase useCase = new DeleteTransactionUseCase(transactionRepository);

    @Test
    void deveExcluirTransacaoExistente() {
        var id = new TransactionId(UUID.randomUUID());
        var transaction = new Transaction(id, "Mercado", 5000, Category.GROCERIES, Instant.now());
        when(transactionRepository.findById(any())).thenReturn(Optional.of(transaction));

        useCase.execute(id.uuid().toString());

        verify(transactionRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoTransacaoNaoExiste() {
        var idInexistente = UUID.randomUUID().toString();
        when(transactionRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(idInexistente))
                .isInstanceOf(TransactionNotFoundException.class);
    }
}
