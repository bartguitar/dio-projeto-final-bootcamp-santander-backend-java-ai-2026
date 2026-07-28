package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output.TransactionOutput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionId;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionNotFoundException;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTransactionCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public UpdateTransactionCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "update-transaction-category", description = "Corrige a categoria de uma transação já registrada")
    public TransactionOutput execute(@ToolParam(description = "Identificador (UUID) da transação") String transactionId,
                                     @ToolParam(description = "Nova categoria da transação") Category newCategory) {
        var id = new TransactionId(UUID.fromString(transactionId));
        var transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        var updated = transactionRepository.save(transaction.withCategory(newCategory));
        return TransactionOutput.from(updated);
    }
}