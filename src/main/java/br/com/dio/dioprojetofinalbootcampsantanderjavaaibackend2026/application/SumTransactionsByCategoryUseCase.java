package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output.TransactionSummaryOutput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Transaction;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.ZoneOffset;

@Service
public class SumTransactionsByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public SumTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "sum-transactions-by-category", description = "Soma o total gasto em uma categoria em um determinado mês")
    public TransactionSummaryOutput execute(
            @ToolParam(description = "Categoria de uma transação") Category category,
            @ToolParam(description = "Mês de referência, no formato AAAA-MM") YearMonth month) {

        var start = month.atDay(1).atStartOfDay(ZoneOffset.UTC).toInstant();
        var end = month.atEndOfMonth().atTime(23, 59, 59).atZone(ZoneOffset.UTC).toInstant();

        var transactions = transactionRepository.findAllByCategoryAndCreatedAtBetween(category, start, end);
        long total = transactions.stream().mapToLong(Transaction::getAmount).sum();

        return new TransactionSummaryOutput(category.name(), month.toString(), total, transactions.size());
    }
}