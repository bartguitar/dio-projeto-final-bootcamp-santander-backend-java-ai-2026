package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.ListTransactionsByCategoryUseCase;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.PersistTransactionUseCase;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http.request.TransactionRequest;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http.response.TransactionResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;
    private final ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;

    public TransactionController(PersistTransactionUseCase persistTransactionUseCase,
                                 ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase){
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listTransactionsByCategoryUseCase = listTransactionsByCategoryUseCase;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        var transaction = persistTransactionUseCase.execute(request.toInput());
        return TransactionResponse.from(transaction);
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> readTransactions(@PathVariable Category category) {
        return listTransactionsByCategoryUseCase.execute(category).stream().map(TransactionResponse::from).toList();
    }
}