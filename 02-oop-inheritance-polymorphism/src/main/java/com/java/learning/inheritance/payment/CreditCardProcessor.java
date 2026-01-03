package com.java.learning.inheritance.payment;

/**
 * Процессор для обработки платежей кредитной картой.
 * 
 * TODO: Реализуй этот класс
 */
public class CreditCardProcessor implements PaymentProcessor {
    
    @Override
    public PaymentResult process(Payment payment) {
        // TODO: Реализуй обработку платежа
        // 1. Проверь, что payment.method() == CREDIT_CARD
        // 2. Извлеки CreditCardDetails из payment.details()
        // 3. Валидируй номер карты алгоритмом Луна
        // 4. Если валидация прошла — верни success с сгенерированным transactionId
        // 5. Если нет — верни failure с соответствующим ErrorCode
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean supports(PaymentMethod method) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет номер карты алгоритмом Луна.
     * 
     * Алгоритм Луна:
     * 1. Начиная с последней цифры, удваивай каждую вторую цифру
     * 2. Если результат > 9, вычти 9
     * 3. Сложи все цифры
     * 4. Если сумма делится на 10 без остатка — номер валиден
     * 
     * @param cardNumber номер карты (только цифры)
     * @return true, если номер валиден
     */
    public boolean validateCardNumber(String cardNumber) {
        // TODO: Реализуй алгоритм Луна
        // Подсказка: сначала удали все нецифровые символы
        throw new UnsupportedOperationException("TODO: implement");
    }
}

