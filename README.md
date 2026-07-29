# 🚀 API de Orçamento Inteligente com Spring AI

Este é o projeto final do desafio da trilha **Spring Boot Learning Track** da DIO. Trata-se de uma API inteligente de orçamento que utiliza a Inteligência Artificial (OpenAI) para interpretar comandos de linguagem natural, acionar funções no back-end (Tool Calling) e gerenciar transações financeiras.

## 📌 O que o projeto faz?
A aplicação permite que o usuário envie comandos de texto naturais (ex: *"Gastei 50 reais no mercado"* ou *"Quanto eu já gastei com alimentação?"*). A IA interpreta a intenção do usuário e executa métodos em Java automaticamente para salvar dados no banco H2 ou consultar informações, devolvendo uma resposta amigável gerada pela própria IA.

## ✨ Qual melhoria foi implementada? (Evolução)
Seguindo a proposta do desafio de evoluir a solução base, implementei as seguintes melhorias na camada de **Tool Calling**:
1. **Validação de Regras de Negócio:** Adicionei uma verificação dentro da função `registrarTransacao`. Agora, se o modelo tentar enviar um valor negativo ou zerado, a aplicação barra o processo e orienta a IA a informar o erro ao usuário.
2. **Nova Funcionalidade de Consulta:** Criei uma nova Tool chamada `consultarPorCategoria`. Agora, o usuário pode perguntar, por exemplo, *"Qual o meu saldo de gastos em Lazer?"*, e a IA executará a query correta no banco para somar as transações dessa categoria.

## 🛠️ Tecnologias Utilizadas
* **Java 17+**
* **Spring Boot 3.3.x**
* **Spring AI (OpenAI)**
* **Spring Data JPA**
* **Banco de Dados H2 (Em memória)**
* **Maven**


