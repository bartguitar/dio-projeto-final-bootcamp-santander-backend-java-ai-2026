package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UpdateTransactionCategoryUseCaseTest {

    private final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
    private final UpdateTransactionCategoryUseCase useCase = new UpdateTransactionCategoryUseCase(transactionRepository);

    @Test
    void deveAtualizarCategoriaDeTransacaoExistente() {
        var id = new TransactionId(UUID.randomUUID());
        var transaction = new Transaction(id, "Remédio", 3000, Category.GROCERIES, Instant.now());

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var output = useCase.execute(id.uuid().toString(), Category.PHARMA);

        assertThat(output.category()).isEqualTo("PHARMA");
    }

    @Test
    void deveLancarExcecaoQuandoTransacaoNaoExiste() {
        var idInexistente = UUID.randomUUID().toString();
        when(transactionRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(idInexistente, Category.PHARMA))
                .isInstanceOf(TransactionNotFoundException.class);
    }
}
