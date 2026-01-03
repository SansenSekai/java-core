package com.java.learning.exceptions;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * Сервис перевода денег между счетами.
 * 
 * Демонстрирует правильную обработку исключений:
 * - Валидация входных данных
 * - Атомарность операций
 * - Информативные исключения
 * 
 * TODO: Реализуй этот класс
 */
public class MoneyTransferService {
    
    // TODO: Добавь поле для хранения счетов (Map<String, Account>)
    
    public MoneyTransferService(Map<String, Account> accounts) {
        // TODO: Реализуй конструктор
        // ВАЖНО: сделай defensive copy!
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Переводит деньги с одного счёта на другой.
     * 
     * @param fromAccountNumber номер счёта отправителя
     * @param toAccountNumber номер счёта получателя
     * @param amount сумма перевода
     * @return ID транзакции
     * @throws AccountNotFoundException если счёт не найден
     * @throws AccountBlockedException если счёт заблокирован
     * @throws InsufficientFundsException если недостаточно средств
     * @throws InvalidAmountException если сумма некорректна
     */
    public String transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        // TODO: Реализуй перевод денег
        // 
        // 1. Валидируй входные данные:
        //    - amount должен быть положительным
        //    - номера счетов не должны быть пустыми
        //    - нельзя переводить на тот же счёт
        //
        // 2. Найди оба счёта (бросай AccountNotFoundException если не найден)
        //
        // 3. Проверь, что оба счёта не заблокированы
        //
        // 4. Проверь, что на счёте отправителя достаточно средств
        //
        // 5. Выполни перевод АТОМАРНО:
        //    - Сначала сними со счёта отправителя
        //    - Если что-то пошло не так — откати операцию!
        //    - Затем зачисли на счёт получателя
        //
        // 6. Верни ID транзакции (используй UUID.randomUUID())
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет баланс счёта.
     * 
     * @param accountNumber номер счёта
     * @return баланс
     * @throws AccountNotFoundException если счёт не найден
     */
    public BigDecimal getBalance(String accountNumber) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Пополняет счёт.
     */
    public void deposit(String accountNumber, BigDecimal amount) {
        // TODO: Реализуй метод
        // Не забудь про валидацию!
        throw new UnsupportedOperationException("TODO: implement");
    }
}

