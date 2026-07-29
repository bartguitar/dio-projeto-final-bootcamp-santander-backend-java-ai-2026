package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.input.PersistTransactionInput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PersistTransactionUseCaseTest {

    private final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final PersistTransactionUseCase useCase = new PersistTransactionUseCase(transactionRepository, validator);

    @Test
    void devePersistirTransacaoValida() {
        var input = new PersistTransactionInput("Mercado", 5000, Category.GROCERIES);
        when(transactionRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var output = useCase.execute(input);

        assertThat(output.description()).isEqualTo("Mercado");
        assertThat(output.category()).isEqualTo("GROCERIES");
    }

    @Test
    void deveRejeitarTransacaoComDescricaoVazia() {
        var input = new PersistTransactionInput("", 1000, Category.GROCERIES);

        assertThatThrownBy(() -> useCase.execute(input))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void deveRejeitarTransacaoComValorNegativo() {
        var input = new PersistTransactionInput("Mercado", -100, Category.GROCERIES);

        assertThatThrownBy(() -> useCase.execute(input))
                .isInstanceOf(ConstraintViolationException.class);
    }
}