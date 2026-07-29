package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.infrastructure.http;

import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.ListTransactionsByPeriodUseCase;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.*;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.application.output.TransactionOutput;
import br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean private PersistTransactionUseCase persistTransactionUseCase;
    @MockitoBean private ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;
    @MockitoBean private ListTransactionsByPeriodUseCase listTransactionsByPeriodUseCase;
    @MockitoBean private SumTransactionsByCategoryUseCase sumTransactionsByCategoryUseCase;
    @MockitoBean private DeleteTransactionUseCase deleteTransactionUseCase;
    @MockitoBean private UpdateTransactionCategoryUseCase updateTransactionCategoryUseCase;
    @MockitoBean private TranscriptionModel transcriptionModel;
    @MockitoBean private TextToSpeechModel textToSpeechModel;
    @MockitoBean private ChatClient.Builder chatClientBuilder;

    @Test
    void deveCriarTransacaoComSucesso() throws Exception {
        var output = new TransactionOutput("123", "Mercado", "GROCERIES", 50.0);
        when(persistTransactionUseCase.execute(any())).thenReturn(output);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description": "Mercado", "category": "GROCERIES", "amount": 5000}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.description").value("Mercado"));
    }

    @Test
    void deveRejeitarTransacaoInvalida() throws Exception {
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description": "", "category": "GROCERIES", "amount": -10}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void deveRetornar404AoExcluirTransacaoInexistente() throws Exception {
        var id = java.util.UUID.randomUUID();
        org.mockito.Mockito.doThrow(new TransactionNotFoundException(new TransactionId(id)))
                .when(deleteTransactionUseCase).execute(id.toString());

        mockMvc.perform(delete("/transactions/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar400ParaIdMalFormado() throws Exception {
        mockMvc.perform(delete("/transactions/{id}", "nao-e-um-uuid"))
                .andExpect(status().isBadRequest());
    }
}
