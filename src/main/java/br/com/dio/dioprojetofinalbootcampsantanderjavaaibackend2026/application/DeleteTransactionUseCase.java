package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionId;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionNotFoundException;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public DeleteTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "delete-transaction", description = "Exclui uma transação financeira pelo seu identificador")
    public void execute(@ToolParam(description = "Identificador (UUID) da transação a ser excluída") String transactionId) {
        var id = new TransactionId(UUID.fromString(transactionId));

        if (transactionRepository.findById(id).isEmpty()) {
            throw new TransactionNotFoundException(id);
        }

        transactionRepository.deleteById(id);
    }
}