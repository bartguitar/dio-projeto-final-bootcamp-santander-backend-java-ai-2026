package br.com.dio.dioprojetofinalbootcampsantanderjavaaibackend2026;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DioProjetoFinalBootcampSantanderJavaAiBackend2026Application {

    @Bean
    ChatClient chatChatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(DioProjetoFinalBootcampSantanderJavaAiBackend2026Application.class, args);
    }

}
