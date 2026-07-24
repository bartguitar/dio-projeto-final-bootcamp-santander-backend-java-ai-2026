package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.input;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record PersistTransactionInput(@ToolParam(description = "Descrição do gasto") String description,
                                      @ToolParam(description = "Valor do gasto (em centavos)") long amount,
                                      @ToolParam(description = "Categoria de uma transação") Category category) {
}