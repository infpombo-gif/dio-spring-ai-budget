package com.dio.budget.controller;

import com.dio.budget.service.BudgetAssistantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    private final BudgetAssistantService service;

    public AssistantController(BudgetAssistantService service) {
        this.service = service;
    }

    @PostMapping("/command")
    public ResponseEntity<String> processTextCommand(@RequestBody String command) {
        String aiResponse = service.processCommand(command);
        return ResponseEntity.ok(aiResponse);
    }
}