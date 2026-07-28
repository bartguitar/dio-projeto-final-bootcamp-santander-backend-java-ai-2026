package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output.TransactionOutput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ListTransactionsByPeriodUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionsByPeriodUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "list-transactions-by-period", description = "Lista todas as transações registradas entre duas datas, de qualquer categoria")
    public List<TransactionOutput> execute(
            @ToolParam(description = "Data inicial, no formato AAAA-MM-DD") String start,
            @ToolParam(description = "Data final, no formato AAAA-MM-DD") String end) {

        var startInstant = LocalDate.parse(start).atStartOfDay(ZoneOffset.UTC).toInstant();
        var endInstant = LocalDate.parse(end).atTime(23, 59, 59).atZone(ZoneOffset.UTC).toInstant();

        return transactionRepository.findAllByCreatedAtBetween(startInstant, endInstant)
                .stream().map(TransactionOutput::from).toList();
    }
}
