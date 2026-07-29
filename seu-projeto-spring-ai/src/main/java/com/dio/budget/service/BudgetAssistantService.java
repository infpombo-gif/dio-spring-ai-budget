package com.dio.budget.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class BudgetAssistantService {

    private final ChatClient chatClient;

    public BudgetAssistantService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("Você é um assistente financeiro inteligente. " +
                        "Sua função é interpretar mensagens dos usuários para registrar transações ou consultar o saldo. " +
                        "Sempre responda de forma educada, curta e direta.")
                .defaultFunctions("registrarTransacao", "consultarPorCategoria") // Aciona as Tools
                .build();
    }

    public String processCommand(String textCommand) {
        return this.chatClient
                .prompt()
                .user(textCommand)
                .call()
                .content();
    }
}