package com.java.learning.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Exceptions Mastery")
class ExceptionsTest {
    
    @Nested
    @DisplayName("Доменные исключения")
    class DomainExceptionsTests {
        
        @Test
        @DisplayName("BankingException хранит сообщение и причину")
        void bankingExceptionShouldStoreCause() {
            Exception cause = new RuntimeException("Original error");
            BankingException exception = new BankingException("Banking failed", cause);
            
            assertThat(exception.getMessage()).contains("Banking failed");
            assertThat(exception.getCause()).isEqualTo(cause);
        }
        
        @Test
        @DisplayName("AccountNotFoundException содержит номер счёта")
        void accountNotFoundExceptionShouldContainAccountNumber() {
            AccountNotFoundException exception = new AccountNotFoundException("ACC-12345");
            
            assertThat(exception.getAccountNumber()).isEqualTo("ACC-12345");
            assertThat(exception.getMessage()).contains("ACC-12345");
        }
        
        @Test
        @DisplayName("InsufficientFundsException содержит суммы")
        void insufficientFundsShouldContainAmounts() {
            InsufficientFundsException exception = new InsufficientFundsException(
                "ACC-001",
                new BigDecimal("1000.00"),
                new BigDecimal("500.00")
            );
            
            assertThat(exception.getAccountNumber()).isEqualTo("ACC-001");
            assertThat(exception.getRequested()).isEqualByComparingTo("1000.00");
            assertThat(exception.getAvailable()).isEqualByComparingTo("500.00");
            assertThat(exception.getShortfall()).isEqualByComparingTo("500.00");
        }
        
        @Test
        @DisplayName("AccountBlockedException содержит причину блокировки")
        void accountBlockedShouldContainReason() {
            AccountBlockedException exception = new AccountBlockedException(
                "ACC-001",
                "Fraud suspected"
            );
            
            assertThat(exception.getBlockReason()).isEqualTo("Fraud suspected");
            assertThat(exception.getMessage()).contains("Fraud suspected");
        }
        
        @Test
        @DisplayName("TransactionDeclinedException содержит причину отклонения")
        void transactionDeclinedShouldContainReason() {
            TransactionDeclinedException exception = new TransactionDeclinedException(
                "TXN-001",
                TransactionDeclinedException.DeclineReason.FRAUD_SUSPECTED
            );
            
            assertThat(exception.getTransactionId()).isEqualTo("TXN-001");
            assertThat(exception.getReason())
                .isEqualTo(TransactionDeclinedException.DeclineReason.FRAUD_SUSPECTED);
        }
        
        @Test
        @DisplayName("ValidationException содержит имя поля")
        void validationExceptionShouldContainFieldName() {
            ValidationException exception = new ValidationException(
                "Invalid value",
                "amount"
            );
            
            assertThat(exception.getFieldName()).isEqualTo("amount");
        }
        
        @Test
        @DisplayName("InvalidAmountException содержит некорректную сумму")
        void invalidAmountShouldContainAmount() {
            InvalidAmountException exception = new InvalidAmountException(
                new BigDecimal("-100.00")
            );
            
            assertThat(exception.getAmount()).isEqualByComparingTo("-100.00");
            assertThat(exception.getMessage()).contains("-100");
        }
    }
    
    @Nested
    @DisplayName("MoneyTransferService")
    class MoneyTransferServiceTests {
        
        private MoneyTransferService service;
        private Map<String, Account> accounts;
        
        @BeforeEach
        void setUp() {
            accounts = new HashMap<>();
            accounts.put("ACC-001", new Account("ACC-001", new BigDecimal("1000.00")));
            accounts.put("ACC-002", new Account("ACC-002", new BigDecimal("500.00")));
            
            Account blocked = new Account("ACC-003", new BigDecimal("2000.00"));
            blocked.block("Fraud investigation");
            accounts.put("ACC-003", blocked);
            
            service = new MoneyTransferService(accounts);
        }
        
        @Test
        @DisplayName("Успешный перевод денег")
        void shouldTransferMoney() {
            String txnId = service.transfer("ACC-001", "ACC-002", new BigDecimal("300.00"));
            
            assertThat(txnId).isNotNull().isNotEmpty();
            assertThat(service.getBalance("ACC-001")).isEqualByComparingTo("700.00");
            assertThat(service.getBalance("ACC-002")).isEqualByComparingTo("800.00");
        }
        
        @Test
        @DisplayName("Ошибка при переводе с несуществующего счёта")
        void shouldThrowWhenFromAccountNotFound() {
            assertThatThrownBy(() -> 
                service.transfer("UNKNOWN", "ACC-002", new BigDecimal("100.00"))
            ).isInstanceOf(AccountNotFoundException.class)
             .extracting("accountNumber")
             .isEqualTo("UNKNOWN");
        }
        
        @Test
        @DisplayName("Ошибка при переводе на несуществующий счёт")
        void shouldThrowWhenToAccountNotFound() {
            assertThatThrownBy(() -> 
                service.transfer("ACC-001", "UNKNOWN", new BigDecimal("100.00"))
            ).isInstanceOf(AccountNotFoundException.class);
        }
        
        @Test
        @DisplayName("Ошибка при недостатке средств")
        void shouldThrowWhenInsufficientFunds() {
            assertThatThrownBy(() -> 
                service.transfer("ACC-001", "ACC-002", new BigDecimal("2000.00"))
            ).isInstanceOf(InsufficientFundsException.class)
             .satisfies(e -> {
                 InsufficientFundsException ex = (InsufficientFundsException) e;
                 assertThat(ex.getRequested()).isEqualByComparingTo("2000.00");
                 assertThat(ex.getAvailable()).isEqualByComparingTo("1000.00");
             });
        }
        
        @Test
        @DisplayName("Ошибка при переводе с заблокированного счёта")
        void shouldThrowWhenFromAccountBlocked() {
            assertThatThrownBy(() -> 
                service.transfer("ACC-003", "ACC-002", new BigDecimal("100.00"))
            ).isInstanceOf(AccountBlockedException.class)
             .extracting("blockReason")
             .isEqualTo("Fraud investigation");
        }
        
        @Test
        @DisplayName("Ошибка при отрицательной сумме")
        void shouldThrowWhenAmountNegative() {
            assertThatThrownBy(() -> 
                service.transfer("ACC-001", "ACC-002", new BigDecimal("-100.00"))
            ).isInstanceOf(InvalidAmountException.class);
        }
        
        @Test
        @DisplayName("Ошибка при нулевой сумме")
        void shouldThrowWhenAmountZero() {
            assertThatThrownBy(() -> 
                service.transfer("ACC-001", "ACC-002", BigDecimal.ZERO)
            ).isInstanceOf(InvalidAmountException.class);
        }
        
        @Test
        @DisplayName("Атомарность: баланс не меняется при ошибке")
        void shouldBeAtomic() {
            BigDecimal originalBalance = service.getBalance("ACC-001");
            
            assertThatThrownBy(() -> 
                service.transfer("ACC-001", "UNKNOWN", new BigDecimal("100.00"))
            ).isInstanceOf(AccountNotFoundException.class);
            
            // Баланс не должен измениться
            assertThat(service.getBalance("ACC-001")).isEqualByComparingTo(originalBalance);
        }
    }
    
    @Nested
    @DisplayName("FileProcessor")
    class FileProcessorTests {
        
        @TempDir
        Path tempDir;
        
        private FileProcessor processor;
        
        @BeforeEach
        void setUp() {
            processor = new FileProcessor();
        }
        
        @Test
        @DisplayName("Обработка файла построчно")
        void shouldProcessFileLineByLine() throws IOException {
            Path file = tempDir.resolve("test.txt");
            Files.write(file, List.of("line1", "line2", "line3"));
            
            List<String> result = processor.processFile(file, String::toUpperCase);
            
            assertThat(result).containsExactly("LINE1", "LINE2", "LINE3");
        }
        
        @Test
        @DisplayName("Ошибка при чтении несуществующего файла")
        void shouldThrowWhenFileNotFound() {
            Path nonExistent = tempDir.resolve("not-exists.txt");
            
            assertThatThrownBy(() -> 
                processor.processFile(nonExistent, s -> s)
            ).isInstanceOf(FileProcessor.FileProcessingException.class)
             .hasCauseInstanceOf(IOException.class);
        }
        
        @Test
        @DisplayName("Копирование с трансформацией")
        void shouldCopyWithTransform() throws IOException {
            Path source = tempDir.resolve("source.txt");
            Path dest = tempDir.resolve("dest.txt");
            Files.write(source, List.of("hello", "world"));
            
            processor.copyWithTransform(source, dest, String::toUpperCase);
            
            assertThat(Files.readAllLines(dest)).containsExactly("HELLO", "WORLD");
        }
        
        @Test
        @DisplayName("FileProcessingException содержит путь к файлу")
        void exceptionShouldContainFilePath() {
            Path file = tempDir.resolve("error.txt");
            
            try {
                processor.processFile(file, s -> s);
                fail("Expected exception");
            } catch (FileProcessor.FileProcessingException e) {
                assertThat(e.getFilePath()).isEqualTo(file);
                assertThat(e.getMessage()).contains(file.toString());
            }
        }
    }
}

