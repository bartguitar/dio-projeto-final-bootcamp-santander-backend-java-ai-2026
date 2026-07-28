package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.input.PersistTransactionInput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output.TransactionOutput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Transaction;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    private final Validator validator;


    public PersistTransactionUseCase(TransactionRepository transactionRepository, Validator validator) {
        this.transactionRepository = transactionRepository;
        this.validator = validator;

    }

    @Tool(name = "persist-transaction", description = "Persiste uma nova transação financeira")
    public TransactionOutput execute(PersistTransactionInput input) {
        var violations = validator.validate(input);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        var transaction = transactionRepository.save(
                new Transaction(input.description(), input.amount(), input.category()));

        return TransactionOutput.from(transaction);
    }
}