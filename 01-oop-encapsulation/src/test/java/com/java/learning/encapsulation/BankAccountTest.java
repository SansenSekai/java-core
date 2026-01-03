package com.java.learning.encapsulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("BankAccount: инкапсуляция и защита инвариантов")
class BankAccountTest {
    
    @Nested
    @DisplayName("Создание счёта")
    class CreationTests {
        
        @Test
        @DisplayName("Счёт создаётся с корректным начальным балансом")
        void shouldCreateAccountWithValidBalance() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            assertThat(account.getBalance())
                .isEqualByComparingTo(new BigDecimal("100.00"));
        }
        
        @Test
        @DisplayName("Счёт создаётся с нулевым балансом")
        void shouldCreateAccountWithZeroBalance() {
            BankAccount account = new BankAccount(BigDecimal.ZERO);
            
            assertThat(account.getBalance())
                .isEqualByComparingTo(BigDecimal.ZERO);
        }
        
        @Test
        @DisplayName("Нельзя создать счёт с отрицательным балансом")
        void shouldRejectNegativeInitialBalance() {
            assertThatThrownBy(() -> new BankAccount(new BigDecimal("-100.00")))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать счёт с null балансом")
        void shouldRejectNullInitialBalance() {
            assertThatThrownBy(() -> new BankAccount(null))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("При создании счёта добавляется запись в историю")
        void shouldAddOpeningEntryToHistory() {
            BankAccount account = new BankAccount(new BigDecimal("500.00"));
            
            List<BankAccount.Transaction> history = account.getTransactionHistory();
            
            assertThat(history).hasSize(1);
            assertThat(history.get(0).type())
                .isEqualTo(BankAccount.TransactionType.ACCOUNT_OPENED);
            assertThat(history.get(0).balanceAfter())
                .isEqualByComparingTo(new BigDecimal("500.00"));
        }
    }
    
    @Nested
    @DisplayName("Пополнение счёта")
    class DepositTests {
        
        @Test
        @DisplayName("Пополнение увеличивает баланс")
        void shouldIncreaseBalance() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            account.deposit(new BigDecimal("50.00"));
            
            assertThat(account.getBalance())
                .isEqualByComparingTo(new BigDecimal("150.00"));
        }
        
        @Test
        @DisplayName("Нельзя пополнить на ноль")
        void shouldRejectZeroDeposit() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            assertThatThrownBy(() -> account.deposit(BigDecimal.ZERO))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя пополнить на отрицательную сумму")
        void shouldRejectNegativeDeposit() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            assertThatThrownBy(() -> account.deposit(new BigDecimal("-50.00")))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя пополнить на null")
        void shouldRejectNullDeposit() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            assertThatThrownBy(() -> account.deposit(null))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Пополнение записывается в историю")
        void shouldRecordDepositInHistory() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            account.deposit(new BigDecimal("50.00"));
            
            List<BankAccount.Transaction> history = account.getTransactionHistory();
            assertThat(history).hasSize(2);
            
            BankAccount.Transaction lastTransaction = history.get(1);
            assertThat(lastTransaction.type())
                .isEqualTo(BankAccount.TransactionType.DEPOSIT);
            assertThat(lastTransaction.amount())
                .isEqualByComparingTo(new BigDecimal("50.00"));
            assertThat(lastTransaction.balanceAfter())
                .isEqualByComparingTo(new BigDecimal("150.00"));
        }
    }
    
    @Nested
    @DisplayName("Снятие со счёта")
    class WithdrawalTests {
        
        @Test
        @DisplayName("Снятие уменьшает баланс")
        void shouldDecreaseBalance() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            account.withdraw(new BigDecimal("30.00"));
            
            assertThat(account.getBalance())
                .isEqualByComparingTo(new BigDecimal("70.00"));
        }
        
        @Test
        @DisplayName("Можно снять всю сумму")
        void shouldAllowWithdrawingEntireBalance() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            account.withdraw(new BigDecimal("100.00"));
            
            assertThat(account.getBalance())
                .isEqualByComparingTo(BigDecimal.ZERO);
        }
        
        @Test
        @DisplayName("Нельзя снять больше, чем есть на счёте")
        void shouldRejectOverdraft() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            assertThatThrownBy(() -> account.withdraw(new BigDecimal("150.00")))
                .isInstanceOf(IllegalArgumentException.class);
            
            // Баланс не должен измениться
            assertThat(account.getBalance())
                .isEqualByComparingTo(new BigDecimal("100.00"));
        }
        
        @Test
        @DisplayName("Нельзя снять ноль")
        void shouldRejectZeroWithdrawal() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            assertThatThrownBy(() -> account.withdraw(BigDecimal.ZERO))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя снять отрицательную сумму")
        void shouldRejectNegativeWithdrawal() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            assertThatThrownBy(() -> account.withdraw(new BigDecimal("-50.00")))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Снятие записывается в историю")
        void shouldRecordWithdrawalInHistory() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            
            account.withdraw(new BigDecimal("30.00"));
            
            List<BankAccount.Transaction> history = account.getTransactionHistory();
            BankAccount.Transaction lastTransaction = history.get(history.size() - 1);
            
            assertThat(lastTransaction.type())
                .isEqualTo(BankAccount.TransactionType.WITHDRAWAL);
            assertThat(lastTransaction.amount())
                .isEqualByComparingTo(new BigDecimal("30.00"));
        }
    }
    
    @Nested
    @DisplayName("Защита истории транзакций")
    class HistoryProtectionTests {
        
        @Test
        @DisplayName("Изменение возвращённого списка не влияет на внутреннюю историю")
        void shouldNotAllowModifyingHistoryThroughReturnedList() {
            BankAccount account = new BankAccount(new BigDecimal("100.00"));
            account.deposit(new BigDecimal("50.00"));
            
            List<BankAccount.Transaction> history = account.getTransactionHistory();
            int originalSize = history.size();
            
            // Попытка модифицировать список
            assertThatThrownBy(() -> history.clear())
                .isInstanceOf(UnsupportedOperationException.class);
            
            // Или если clear не бросает исключение, проверяем что оригинал не изменился
            assertThat(account.getTransactionHistory()).hasSize(originalSize);
        }
        
        @Test
        @DisplayName("История хранит все операции в правильном порядке")
        void shouldMaintainCorrectOrderOfOperations() {
            BankAccount account = new BankAccount(new BigDecimal("1000.00"));
            account.deposit(new BigDecimal("200.00"));
            account.withdraw(new BigDecimal("150.00"));
            account.deposit(new BigDecimal("50.00"));
            
            List<BankAccount.Transaction> history = account.getTransactionHistory();
            
            assertThat(history).hasSize(4);
            assertThat(history.get(0).type()).isEqualTo(BankAccount.TransactionType.ACCOUNT_OPENED);
            assertThat(history.get(1).type()).isEqualTo(BankAccount.TransactionType.DEPOSIT);
            assertThat(history.get(2).type()).isEqualTo(BankAccount.TransactionType.WITHDRAWAL);
            assertThat(history.get(3).type()).isEqualTo(BankAccount.TransactionType.DEPOSIT);
        }
    }
}

