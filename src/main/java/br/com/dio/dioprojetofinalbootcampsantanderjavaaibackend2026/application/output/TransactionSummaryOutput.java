package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record TransactionSummaryOutput(String category, String month, double total, int count) {
    public static TransactionSummaryOutput from(String category, String month, long amountInCents, int count) {
        var value = BigDecimal.valueOf(amountInCents).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return new TransactionSummaryOutput(category, month, value, count);
    }
}