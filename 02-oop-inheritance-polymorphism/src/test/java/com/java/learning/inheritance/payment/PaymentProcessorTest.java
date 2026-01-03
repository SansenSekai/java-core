package com.java.learning.inheritance.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Payment Processors: интерфейсы и полиморфизм")
class PaymentProcessorTest {
    
    @Nested
    @DisplayName("CreditCardProcessor")
    class CreditCardProcessorTests {
        
        private final CreditCardProcessor processor = new CreditCardProcessor();
        
        @Test
        @DisplayName("Поддерживает только CREDIT_CARD")
        void shouldSupportOnlyCreditCard() {
            assertThat(processor.supports(PaymentMethod.CREDIT_CARD)).isTrue();
            assertThat(processor.supports(PaymentMethod.PAYPAL)).isFalse();
            assertThat(processor.supports(PaymentMethod.CRYPTO)).isFalse();
        }
        
        @Test
        @DisplayName("Алгоритм Луна: валидные номера карт")
        void shouldValidateCorrectCardNumbers() {
            // Тестовые номера карт (проходят алгоритм Луна)
            assertThat(processor.validateCardNumber("4532015112830366")).isTrue();
            assertThat(processor.validateCardNumber("4916338506082832")).isTrue();
            assertThat(processor.validateCardNumber("5425233430109903")).isTrue();
        }
        
        @Test
        @DisplayName("Алгоритм Луна: невалидные номера карт")
        void shouldRejectIncorrectCardNumbers() {
            assertThat(processor.validateCardNumber("4532015112830367")).isFalse();
            assertThat(processor.validateCardNumber("1234567890123456")).isFalse();
            assertThat(processor.validateCardNumber("0000000000000000")).isFalse();
        }
        
        @Test
        @DisplayName("Алгоритм Луна: игнорирует пробелы и тире")
        void shouldIgnoreSpacesAndDashes() {
            assertThat(processor.validateCardNumber("4532-0151-1283-0366")).isTrue();
            assertThat(processor.validateCardNumber("4532 0151 1283 0366")).isTrue();
        }
        
        @Test
        @DisplayName("Успешная обработка валидного платежа")
        void shouldProcessValidPayment() {
            Payment payment = new Payment(
                "PAY-001",
                new BigDecimal("100.00"),
                "USD",
                PaymentMethod.CREDIT_CARD,
                new CreditCardDetails("4532015112830366", "John Doe", "12/25", "123")
            );
            
            PaymentResult result = processor.process(payment);
            
            assertThat(result.success()).isTrue();
            assertThat(result.getTransactionId()).isPresent();
        }
        
        @Test
        @DisplayName("Отклонение платежа с невалидной картой")
        void shouldRejectInvalidCardPayment() {
            Payment payment = new Payment(
                "PAY-002",
                new BigDecimal("100.00"),
                "USD",
                PaymentMethod.CREDIT_CARD,
                new CreditCardDetails("1234567890123456", "John Doe", "12/25", "123")
            );
            
            PaymentResult result = processor.process(payment);
            
            assertThat(result.success()).isFalse();
            assertThat(result.getErrorCode()).contains(PaymentResult.ErrorCode.INVALID_CARD_NUMBER);
        }
    }
    
    @Nested
    @DisplayName("PayPalProcessor")
    class PayPalProcessorTests {
        
        private final PayPalProcessor processor = new PayPalProcessor();
        
        @Test
        @DisplayName("Поддерживает только PAYPAL")
        void shouldSupportOnlyPayPal() {
            assertThat(processor.supports(PaymentMethod.PAYPAL)).isTrue();
            assertThat(processor.supports(PaymentMethod.CREDIT_CARD)).isFalse();
        }
        
        @Test
        @DisplayName("Валидация email: корректные адреса")
        void shouldValidateCorrectEmails() {
            assertThat(processor.validateEmail("user@example.com")).isTrue();
            assertThat(processor.validateEmail("user.name@domain.org")).isTrue();
            assertThat(processor.validateEmail("user+tag@example.co.uk")).isTrue();
        }
        
        @Test
        @DisplayName("Валидация email: некорректные адреса")
        void shouldRejectIncorrectEmails() {
            assertThat(processor.validateEmail("user")).isFalse();
            assertThat(processor.validateEmail("user@")).isFalse();
            assertThat(processor.validateEmail("@domain.com")).isFalse();
            assertThat(processor.validateEmail("user@domain")).isFalse();
        }
        
        @Test
        @DisplayName("Успешная обработка валидного PayPal платежа")
        void shouldProcessValidPayPalPayment() {
            Payment payment = new Payment(
                "PAY-003",
                new BigDecimal("50.00"),
                "EUR",
                PaymentMethod.PAYPAL,
                new PayPalDetails("user@example.com")
            );
            
            PaymentResult result = processor.process(payment);
            
            assertThat(result.success()).isTrue();
        }
    }
    
    @Nested
    @DisplayName("CryptoProcessor")
    class CryptoProcessorTests {
        
        private final CryptoProcessor processor = new CryptoProcessor();
        
        @Test
        @DisplayName("Поддерживает только CRYPTO")
        void shouldSupportOnlyCrypto() {
            assertThat(processor.supports(PaymentMethod.CRYPTO)).isTrue();
            assertThat(processor.supports(PaymentMethod.CREDIT_CARD)).isFalse();
        }
        
        @Test
        @DisplayName("Валидация Bitcoin адресов")
        void shouldValidateBitcoinAddresses() {
            // Legacy адреса начинаются с 1
            assertThat(processor.validateWalletAddress("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC")).isTrue();
            // Segwit адреса начинаются с 3
            assertThat(processor.validateWalletAddress("3J98t1WpEZ73CNmQviecrnyiWrnqRhWNLy", "BTC")).isTrue();
            // Bech32 адреса начинаются с bc1
            assertThat(processor.validateWalletAddress("bc1qar0srrr7xfkvy5l643lydnw9re59gtzzwf5mdq", "BTC")).isTrue();
        }
        
        @Test
        @DisplayName("Валидация Ethereum адресов")
        void shouldValidateEthereumAddresses() {
            assertThat(processor.validateWalletAddress("0x742d35Cc6634C0532925a3b844Bc9e7595f5b7b8", "ETH")).isTrue();
            assertThat(processor.validateWalletAddress("0x0", "ETH")).isFalse();
        }
        
        @Test
        @DisplayName("Отклонение невалидных адресов")
        void shouldRejectInvalidAddresses() {
            assertThat(processor.validateWalletAddress("invalid", "BTC")).isFalse();
            assertThat(processor.validateWalletAddress("", "ETH")).isFalse();
        }
    }
    
    @Nested
    @DisplayName("Полиморфное использование")
    class PolymorphicUsageTests {
        
        @Test
        @DisplayName("Можно работать с разными процессорами через общий интерфейс")
        void shouldWorkThroughInterface() {
            PaymentProcessor[] processors = {
                new CreditCardProcessor(),
                new PayPalProcessor(),
                new CryptoProcessor()
            };
            
            for (PaymentProcessor processor : processors) {
                // Каждый процессор поддерживает ровно один метод
                long supportedCount = java.util.Arrays.stream(PaymentMethod.values())
                    .filter(processor::supports)
                    .count();
                    
                assertThat(supportedCount).isEqualTo(1);
            }
        }
    }
}

