package com.java.learning.inheritance.payment;

/**
 * Процессор для обработки криптовалютных платежей.
 * 
 * TODO: Реализуй этот класс
 */
public class CryptoProcessor implements PaymentProcessor {
    
    @Override
    public PaymentResult process(Payment payment) {
        // TODO: Реализуй обработку платежа
        // 1. Проверь, что payment.method() == CRYPTO
        // 2. Извлеки CryptoDetails из payment.details()
        // 3. Валидируй адрес кошелька
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
     * Проверяет формат адреса криптокошелька.
     * 
     * Упрощённая валидация:
     * - Bitcoin: начинается с 1, 3 или bc1, длина 26-35 символов
     * - Ethereum: начинается с 0x, длина 42 символа (включая 0x)
     * 
     * @param address адрес кошелька
     * @param currency криптовалюта (BTC или ETH)
     * @return true, если адрес валиден
     */
    public boolean validateWalletAddress(String address, String currency) {
        // TODO: Реализуй валидацию адреса
        throw new UnsupportedOperationException("TODO: implement");
    }
}

