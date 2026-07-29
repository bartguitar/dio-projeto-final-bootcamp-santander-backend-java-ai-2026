### Seção 1 - Introdução ao desenvolvendo sua API Inteligente
#### Sobre: API Inteligente com reconhecimento de fala
- Registro de gastos financeiros pessoais, usando STT na entrada e TTS na saida 
### Seção 2 - Spring AI: Setup e Integração com LLMs
- Tecnologias Utilizadas 
- Arquitetura DDD 
- Não foram adicionadas nenhuma dependência para ir adicionando manualmente conforme desenvolvimento \
--Feito Commit-- 
- 2.1 - Criar chave de API da openai e colocar nas configurações do projeto 
- 2.2 - Colocar essa variável da api criada em "application.properties" nas variáveis de ambiente do intellij (edit...configurations/springboot) 
- 2.3 - Adicionar dependências "spring-ai-bom" / "starter-model-openai" 
- 2.4 - Testar aplicação \
--Feito Commit--
### Seção 3 - Explorando o ChatModel e Modelos de Linguagem
- 3.1 - Desenvolver testes de integração, adicionar em "run/debug configurations" gradle para testes em "edit configuration templates" e criar variável
- 3.2 - Criar primeiro teste classe "OpenAiChatModelIT"
- 3.3 - Adicionar configurações no properties "model" / "temperature" / "response-format"
- 3.4 - Dar run na classe "OpenAiChatModellt" dentro de "test" e fazer o teste
- 3.5 - Adicionar dependência "starter-web"
- 3.6 - Criar classe "ChatModelController"
- 3.7 - Testar aplicação GET http://localhost:8080/api/chat-model?prompt=Oi
- 3.8 - Excluir configuração "temperature" de properties \
--Feito Commit--
### Seção 4 - ChatClient:Fluência e Contexto no Spring AI
- 4.1 - Criar classe "OpenAiChatClientIT"
- 4.2 - Testar classe criada
- 4.3 - Criar classe "ChatClientController"
- 4.4 - Testar aplicação
- 4.5 - Alterar classe "DioProjetoFinalBootcampSantanderJavaAiBackend2026Application", metodo "chatChatClient"
- 4.6 - Alterar classe "ChatClientController"
- 4.7 - Testar aplicação http://localhost:8080/api/chat?prompt=BomDia \
--Feito Commit--
### Seção 5 - Tool Calling:Executando Funções Reais com IA
- 5.1 - Criar classe "ToolCallingIT"
- 5.2 - Testar aplicação classe de teste
- 5.3 - Adicionar config "logging.level" em properties
- 5.4 - Testar aplicação classe de teste \
--Feito Commit--
### Seção 6 - Transcription API:Transformando Audio em Texto
- 6.1 - Criar pasta audio e colocar os arquivos dentro
- 6.2 - Criar classe "OpenAiTranscriptionModelIT"
- 6.3 - Adicionar anotações na classe "main" de test
- 6.4 - Configurações no application.properties - "whisper-1" / "language" / "temperature" / "response-format" / "prompt"
- 6.5 - Fazer teste da classe "OpenAiTranscriptionModelIT"
- 6.6 - Criar classe "TranscriptionController"
- 6.7 - Adicionar config. "transcription=openai"
- 6.8 - Testar classe "TranscriptionController"
- 6.8 - Testar um POST http://localhost:8080/api/transcribe \
--Feito Commit--
### Seção 7 - Speech API:Sintetizando voz com TTS
- 7.1 - Criar classe "OpenAiSpeechModelIT"
- 7.2 - Adicionar dependências "audio-speech....."
- 7.3 - Testar classe de teste criada
- 7.4 - Criar classe "TextToSpeechController"
- 7.5 - Testar aplicação http://localhost:8080/api/sinthesize \
--Feito Commit--
### Seção 8 - Integração do Assistente:Orquestrando o fluxo de Budget
- 8.1 - Criar pacotes e classes DDD
- 8.2 - Criar Primeiro as classes de domínio
- 8.3 - Criar serviços pacote application
--Feito Commit--
### Seção 9 - Persistência e Infraestrutura:Configurando o Banco com Docker
- 9.1 - Criar parte de banco de dados, arquivo "compose.yml"
- 9.2 - Adicionar dependência "docker-compose"
- 9.3 - Testar aplicação se subiu o docker
- 9.4 - Adicionar dependência do "spring-jpa" e "connector-j"
- 9.5 - Testar aplicação
- 9.6 - Criar classes pacotes e classes de entity e repository
- 9.7 - Fazer anotações na classe "PersistTransactionUseCase"
- 9.8 - Adicionar config. no properties do jpa "ddl-auto" e "show-sql"
- 9.9 - Testar aplicação se subiu banco de dados \
--Feito Commit--
### Seção 10 - Exposição REST: Implementando o TransactionController
- 10.1 - Implementar classes e pacotes em "http"
- 10.2 - Criar classe "ListTransactionsByCategoryUseCase"
- 10.3 - Testar aplicação com todos endpoints, testar GET E POST \
--Feito Commit--
### Seção 11 - Endpoint de Transcrição:Integrando Audio ao Controller
- 11.1 - Criar endpoint "/ai"
- 11.2 - Adicionar e alterar classe "TransactionController"
- 11.3 - Fazer anotação @tool em "persisttransaction...." e "ListTransactions...."
- 11.4 - Criar pacote "prompts" e arquivos
- 11.5 - Testar aplicação com os endpoints
- 11.6 - Injetar variavel "textToSpeechModel" em controller
- 11.7 - Teste completo da aplicação \
--Feito Commit--
### MELHORIAS
#### Melhoria A: Novos tipos de consulta financeira
**A.1 - Somatório por categoria/período — GET /transactions/summary?category=GROCERIES&month=2026-07 retornando total gasto. É só um SUM novo no TransactionRepository + endpoint. (pequeno)**
**A.2 - Listar por intervalo de valor ou data — ex. "quanto gastei essa semana?" — exige adicionar createdAt na entidade (hoje não existe). (pequeno/médio)** \
**A.3 - Nova tool sumTransactionsByCategory — assim o assistente de voz responde "você gastou R$120 em farmácia este mês" sem você perguntar via REST. (pequeno, reaproveita o padrão que já existe em ListTransactionsByCategoryUseCase)** 
- 1 - Adicionar em "Transaction" o campo "Instant createdAT"
- 2 - Adicionar em "TransacationEntity" o "createdAT"
- 3 - Adicionar lista "findAllByCategoryAndCreatedAtBetween" em "TransactionRepository"
- 4 - Adicionar lista do tópico acima "findAllByCategoryAndCreatedAtBetween" no "TransactionEntityRepository"
- 5 - Adicionar metodo "findAllByCategoryAndCreatedAtBetween" em "JpaTransactionRepository"
- 6 - Criar classe/caso de uso "SumTransactionsByCategoryUseCase"
- 7 - Criar classe record "TransactionSummaryOutput"
- 8 - Injetar SumTransactionsByCategoryUseCase na classe "TransactionController"
- 9 - Colocar endpoint "/summary" no "TransactionController"
- 10 - Nova tool "sum-transactions-by-category", anotar o método execute do SumTransactionsByCategoryUseCase com @Tool
- 11 - Atualizar o "system-message.st" \
--Feito Commit--
#### Melhoria B: Melhorar as respostas da IA
**B.1 - Acrescentar uma seção no prompt orientando o formato da resposta final, separando por tipo de ação**
**B.2 - Adicionar config do chat "temperature" para que as respostas fiquem mais padronizadas, previsíveis e "enxutas"**
- 1 - Reescrever o "system-message.st"
- 2 - Adicionar config "spring.ai.openai.chat.options.temperature=0.2" \
--Feito Commit--
#### Melhoria C: Novas Tools Calling
**C.1 — deleteTransactionUseCase — apagar uma transação** \
**C.2 — updateTransactionCategoryUseCase — corrigir a categoria de uma transação** \
**C.3 — listTransactionsByPeriodUseCase — consultar por período (sem filtrar categoria)**
- 1 - Domínio (TransactionRepository.java) — adicionar metodo "deletebyId"
- 2 - Adicionar metodo "deleteById" em "jpatransaction...."
- 3 - Criar caso de uso "DeleteTransactionUseCase"
- 4 - Adicionar "DeleteMapping" em controller
- 5 - Registrar a tool e injetar o use case "DeleteTransaction...." na classe controller
- 6 - Adicionar em repository o "optional....findById"
- 7 - Adicionar metodo "findById" em "jpa...repository"
- 8 - Adicionar metodo "withCategory" em transaction
- 9 - Criar novo caso de uso "UpdateTransactionCategoryUseCase"
- 10 - Adicionar endpoint "patchmapping"
- 11 - Injetar a classe "UpdateTransaction....." no controller
- 12 - Criar método "findAllByCreatedAtBetween" em "TransactionRepository"
- 13 - Criar método "findAllByCreatedAtBetween" em "JpaRepository....."
- 14 - Criar caso de uso "ListTransactionsByPeriodUseCase"
- 15 - Criar "readTransactionsByPeriod" no controller
- 16 - Injetar a classe "ListTransactionsByPeriodUseCase" no controller \
--Feito Commit--
### Melhoria D: Validações antes de salvar uma transação
**D.1 - Impedir que dado errado entre no sistema usando validação automática do Spring (Bean Validation) 
em todos os pontos de entrada — tanto na criação de transação via REST quanto via assistente de voz — 
além de um tratamento central de erros que padroniza as respostas quando algo é inválido ou não encontrado**
- 1 - Adicionar dependência Bean Validation "starter-validation"
- 2 - Anotar "TransactionRequest" com "NotBlank" / "NotNull" / "Positive"
- 3 - Ativar validação no controller com @Valid
- 4 - Anotar @NotBlank e @Positive em "PersistTransactionInput"
- 5 - Injetar "validator" em "PersistTransactionUseCase....." e incrementar método execute da classe com validator
- 6 - Tratar id inválido/inexistente - trocar @PathVariable String id por @PathVariable UUID id) nos endpoints de DELETE e PATCH
- 7 - Criar "TransactionNotFoundException"
- 8 - Usar a exceção no "UpdateTransactionCategoryUseCase" e no "DeleteTransactionUseCase", substituindo o IllegalArgumentException 
- 9 - Criar "GlobalExceptionHandler" \
--Feito Commit--
### Melhoria E: Endpoints REST
**E.1 - Paginação nos endpoints de listagem**
**E.2 - "Location" header e status code corretos na criação**
**E.3 - Ordenação configurável**
- 1 - Domínio (TransactionRepository.java) — tricar o retorno List<Transaction> por Page<Transaction> nos métodos de listagem
- 2 - Infraestrutura (TransactionEntityRepository.java) — como já extends CrudRepository, basta trocar a assinatura pra aceitar Pageable e retornar Page
- 3 - JpaTransactionRepository.java — o .map() de conversão continua igual, só que agora em cima de Page (que também tem .map(), igual List/Stream
- 4 - Use cases (ListTransactionsByCategoryUseCase, ListTransactionsByPeriodUseCase) — recebem Pageable como parâmetro adicional e repassam pro repositório.
- 5 - Infraestrutura (TransactionEntityRepository.java) — como já extends CrudRepository, basta trocar a assinatura pra aceitar Pageable e retornar Page
- 6 - Controller — receba Pageable como parâmetro do endpoint (o Spring MVC já sabe extrair isso da query string automaticamente, tipo ?page=0&size=10
- 7 - Location header. É uma mudança pequena, só no TransactionController, no método createTransaction \
--Feito Commit--
### Melhoria F: Testes dos fluxos principais
**F.1 - Testes unitários dos use cases** \
Ideia central: mockar o TransactionRepository (não bater em banco real) e testar só a regra de negócio de cada use case — sucesso e principais casos de erro. \
**F.2 — Testes do controller REST (MockMvc)** \
Ideia central: subir só a camada web (@WebMvcTest), sem banco, sem contexto Spring completo, testando o comportamento HTTP observável — status code, corpo da resposta, headers.
- 1 - Criar classe "PersistTransactionUseCaseTest" / "DeleteTransactionUseCaseTest" / "UpdateTransactionCategoryUseCaseTest"
- 2 - Reorganizar as classes e pacotes de testes
- 3 - Criar classe "TransactionControllerTest"
- 4 - Adicionar dependência testImplementation 'org.springframework.boot:spring-boot-starter-webmvc-test' \
--Feito Commit--