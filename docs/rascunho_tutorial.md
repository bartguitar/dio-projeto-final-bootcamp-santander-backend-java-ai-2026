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