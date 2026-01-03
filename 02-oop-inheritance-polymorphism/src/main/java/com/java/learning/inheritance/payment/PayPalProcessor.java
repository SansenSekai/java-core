package com.java.learning.inheritance.payment;

/**
 * Процессор для обработки платежей через PayPal.
 * 
 * TODO: Реализуй этот класс
 */
public class PayPalProcessor implements PaymentProcessor {
    
    @Override
    public PaymentResult process(Payment payment) {
        // TODO: Реализуй обработку платежа
        // 1. Проверь, что payment.method() == PAYPAL
        // 2. Извлеки PayPalDetails из payment.details()
        // 3. Валидируй email
        // 4. Если валидация прошла — верни success
        // 5. Если нет — верни failure
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean supports(PaymentMethod method) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет формат email.
     * 
     * Простая валидация:
     * - Содержит ровно один символ @
     * - До @ есть хотя бы один символ
     * - После @ есть хотя бы один символ, точка, и ещё символы
     * 
     * @param email email для проверки
     * @return true, если email валиден
     */
    public boolean validateEmail(String email) {
        // TODO: Реализуй валидацию email
        // Можно использовать regex или ручную проверку
        throw new UnsupportedOperationException("TODO: implement");
    }
}

