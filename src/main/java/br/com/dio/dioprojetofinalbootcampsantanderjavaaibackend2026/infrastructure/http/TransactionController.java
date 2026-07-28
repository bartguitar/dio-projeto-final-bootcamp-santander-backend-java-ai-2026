package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.ListTransactionsByPeriodUseCase;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.*;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output.TransactionSummaryOutput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.Category;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http.request.TransactionRequest;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http.response.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;
    private final ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;
    private final SumTransactionsByCategoryUseCase sumTransactionsByCategoryUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final UpdateTransactionCategoryUseCase updateTransactionCategoryUseCase;
    private final ListTransactionsByPeriodUseCase listTransactionsByPeriodUseCase;

    private final TranscriptionModel transcriptionModel;
    private final ChatClient chatClient;

    private final TextToSpeechModel textToSpeechModel;



    public TransactionController(PersistTransactionUseCase persistTransactionUseCase,
                                 ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase,
                                 SumTransactionsByCategoryUseCase sumTransactionsByCategoryUseCase,
                                 DeleteTransactionUseCase deleteTransactionUseCase,
                                 UpdateTransactionCategoryUseCase updateTransactionCategoryUseCase,
                                 ListTransactionsByPeriodUseCase listTransactionsByPeriodUseCase,
                                 TranscriptionModel transcriptionModel,
                                 @Value("classpath:prompts/system-message.st") Resource systemPrompt,
                                 ChatClient.Builder chatClientBuilder,
                                 TextToSpeechModel textToSpeechModel) throws IOException {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listTransactionsByCategoryUseCase = listTransactionsByCategoryUseCase;
        this.sumTransactionsByCategoryUseCase = sumTransactionsByCategoryUseCase;
        this.deleteTransactionUseCase = deleteTransactionUseCase;
        this.updateTransactionCategoryUseCase = updateTransactionCategoryUseCase;
        this.listTransactionsByPeriodUseCase = listTransactionsByPeriodUseCase;
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatClientBuilder
                .defaultSystem(systemPrompt.getContentAsString(Charset.defaultCharset()))
                .defaultTools(persistTransactionUseCase, listTransactionsByCategoryUseCase, sumTransactionsByCategoryUseCase,
                        deleteTransactionUseCase, updateTransactionCategoryUseCase, listTransactionsByPeriodUseCase)
                .build();
        this.textToSpeechModel = textToSpeechModel;

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest request) {
        var transaction = persistTransactionUseCase.execute(request.toInput());
        return TransactionResponse.from(transaction);
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> readTransactions(@PathVariable Category category) {
        return listTransactionsByCategoryUseCase.execute(category).stream().map(TransactionResponse::from).toList();
    }

    @PostMapping(value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
    ResponseEntity<Resource> transcribe(@RequestParam("file") MultipartFile file) {
        var userMessage = transcriptionModel.transcribe(file.getResource());
        var result  = chatClient.prompt().user(userMessage).call().content();

        byte[] audio = textToSpeechModel.call(result);
        var resource = new ByteArrayResource(audio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())
                .body(resource);
    }

    @GetMapping("/summary")
    public TransactionSummaryOutput summary(@RequestParam Category category,
                                            @RequestParam String month) {
        return sumTransactionsByCategoryUseCase.execute(category, YearMonth.parse(month));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable UUID id) {
        deleteTransactionUseCase.execute(id.toString());
    }

    @PatchMapping("/{id}/category")
    public TransactionResponse updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return TransactionResponse.from(updateTransactionCategoryUseCase.execute(id.toString(), category));
    }

    @GetMapping
    public List<TransactionResponse> readTransactionsByPeriod(@RequestParam String start, @RequestParam String end) {
        return listTransactionsByPeriodUseCase.execute(start, end).stream().map(TransactionResponse::from).toList();
    }
}