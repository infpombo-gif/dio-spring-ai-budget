package com.dio.budget.tools;

import com.dio.budget.entity.Transaction;
import com.dio.budget.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Configuration
public class TransactionTools {

    private final TransactionRepository repository;

    public TransactionTools(TransactionRepository repository) {
        this.repository = repository;
    }

    public record TransactionRequest(String description, BigDecimal amount, String type, String category) {}

    @Bean
    @Description("Registra uma nova transação financeira (receita ou despesa). O valor deve ser sempre positivo.")
    public Function<TransactionRequest, String> registrarTransacao() {
        return request -> {
            // EVOLUÇÃO/MELHORIA: Validação de Regra de Negócio antes de salvar
            if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
                return "Erro: O valor da transação deve ser maior que zero.";
            }

            Transaction transaction = new Transaction(
                    request.description(), 
                    request.amount(), 
                    request.type(), 
                    request.category(), 
                    LocalDate.now()
            );
            repository.save(transaction);
            return "Transação salva com sucesso: " + request.description() + " no valor de R$" + request.amount();
        };
    }

    public record CategoryRequest(String category) {}

    @Bean
    @Description("Consulta o total gasto ou recebido em uma categoria específica")
    public Function<CategoryRequest, String> consultarPorCategoria() {
        return request -> {
            List<Transaction> transacoes = repository.findByCategoryIgnoreCase(request.category());
            if (transacoes.isEmpty()) {
                return "Nenhuma transação encontrada para a categoria " + request.category();
            }
            BigDecimal total = transacoes.stream()
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            return "O total movimentado na categoria " + request.category() + " é R$" + total;
        };
    }
}