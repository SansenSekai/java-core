package com.java.learning.inheritance.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Notification System: интерфейсы и default-методы")
class NotifierTest {
    
    @Nested
    @DisplayName("EmailNotifier")
    class EmailNotifierTests {
        
        @Test
        @DisplayName("Отправка на валидный email")
        void shouldSendToValidEmail() {
            EmailNotifier notifier = new EmailNotifier();
            
            boolean result = notifier.send("Hello", "user@example.com");
            
            assertThat(result).isTrue();
        }
        
        @Test
        @DisplayName("Отклонение невалидного email")
        void shouldRejectInvalidEmail() {
            EmailNotifier notifier = new EmailNotifier();
            
            boolean result = notifier.send("Hello", "invalid-email");
            
            assertThat(result).isFalse();
        }
        
        @Test
        @DisplayName("Форматирование HTML сообщения")
        void shouldFormatHtmlMessage() {
            EmailNotifier notifier = new EmailNotifier(EmailNotifier.Format.HTML);
            
            String formatted = notifier.formatMessage("Hello");
            
            assertThat(formatted).contains("<html>");
            assertThat(formatted).contains("</html>");
            assertThat(formatted).contains("Hello");
        }
        
        @Test
        @DisplayName("PLAIN_TEXT не добавляет HTML теги")
        void shouldNotAddHtmlTagsForPlainText() {
            EmailNotifier notifier = new EmailNotifier(EmailNotifier.Format.PLAIN_TEXT);
            
            String formatted = notifier.formatMessage("Hello");
            
            assertThat(formatted).doesNotContain("<html>");
            assertThat(formatted).isEqualTo("Hello");
        }
        
        @Test
        @DisplayName("Тип уведомителя — EMAIL")
        void shouldHaveCorrectType() {
            EmailNotifier notifier = new EmailNotifier();
            
            assertThat(notifier.getType()).isEqualTo("EMAIL");
        }
    }
    
    @Nested
    @DisplayName("SmsNotifier")
    class SmsNotifierTests {
        
        @Test
        @DisplayName("Отправка на валидный номер")
        void shouldSendToValidPhoneNumber() {
            SmsNotifier notifier = new SmsNotifier();
            
            boolean result = notifier.send("Hello", "+1234567890");
            
            assertThat(result).isTrue();
        }
        
        @Test
        @DisplayName("Обрезка длинного сообщения")
        void shouldTruncateLongMessage() {
            SmsNotifier notifier = new SmsNotifier();
            String longMessage = "A".repeat(200);
            
            String truncated = notifier.truncateMessage(longMessage);
            
            assertThat(truncated.length()).isLessThanOrEqualTo(SmsNotifier.MAX_SMS_LENGTH);
            assertThat(truncated).endsWith("...");
        }
        
        @Test
        @DisplayName("Короткое сообщение не обрезается")
        void shouldNotTruncateShortMessage() {
            SmsNotifier notifier = new SmsNotifier();
            String shortMessage = "Hello";
            
            String result = notifier.truncateMessage(shortMessage);
            
            assertThat(result).isEqualTo("Hello");
        }
        
        @Test
        @DisplayName("Валидация номера телефона")
        void shouldValidatePhoneNumber() {
            SmsNotifier notifier = new SmsNotifier();
            
            assertThat(notifier.isValidPhoneNumber("+1234567890")).isTrue();
            assertThat(notifier.isValidPhoneNumber("1234567890")).isTrue();
            assertThat(notifier.isValidPhoneNumber("+1-234-567-8901")).isTrue();
            assertThat(notifier.isValidPhoneNumber("123")).isFalse();  // too short
            assertThat(notifier.isValidPhoneNumber("abc")).isFalse();  // not digits
        }
    }
    
    @Nested
    @DisplayName("PushNotifier")
    class PushNotifierTests {
        
        @Test
        @DisplayName("Отправка на валидный device token")
        void shouldSendToValidDeviceToken() {
            PushNotifier notifier = new PushNotifier();
            
            boolean result = notifier.send("Hello", "device-token-12345");
            
            assertThat(result).isTrue();
        }
        
        @Test
        @DisplayName("Отклонение невалидного device token")
        void shouldRejectInvalidDeviceToken() {
            PushNotifier notifier = new PushNotifier();
            
            boolean result = notifier.send("Hello", "short");
            
            assertThat(result).isFalse();
        }
        
        @Test
        @DisplayName("Валидация device token")
        void shouldValidateDeviceToken() {
            PushNotifier notifier = new PushNotifier();
            
            assertThat(notifier.isValidDeviceToken("device-token-12345")).isTrue();
            assertThat(notifier.isValidDeviceToken("")).isFalse();
            assertThat(notifier.isValidDeviceToken("short")).isFalse();
        }
    }
    
    @Nested
    @DisplayName("CompositeNotifier")
    class CompositeNotifierTests {
        
        @Test
        @DisplayName("Отправка через несколько каналов")
        void shouldSendThroughMultipleChannels() {
            CompositeNotifier notifier = new CompositeNotifier(
                new EmailNotifier(),
                new SmsNotifier(),
                new PushNotifier()
            );
            
            // Все каналы получат сообщение
            // Email валиден, SMS валиден, Push токен валиден
            int successCount = notifier.sendToAll("Hello", "user@example.com");
            
            // Email должен быть успешным, SMS и Push зависят от формата получателя
            assertThat(successCount).isGreaterThanOrEqualTo(1);
        }
        
        @Test
        @DisplayName("send возвращает true, если хотя бы один канал успешен")
        void shouldReturnTrueIfAtLeastOneSucceeds() {
            CompositeNotifier notifier = new CompositeNotifier(
                new EmailNotifier(),
                new SmsNotifier()
            );
            
            // Email валиден, номер невалиден
            boolean result = notifier.send("Hello", "user@example.com");
            
            assertThat(result).isTrue();
        }
        
        @Test
        @DisplayName("Нельзя создать с пустым списком")
        void shouldRejectEmptyList() {
            assertThatThrownBy(() -> new CompositeNotifier(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("getNotifiers возвращает неизменяемый список")
        void shouldReturnUnmodifiableList() {
            CompositeNotifier notifier = new CompositeNotifier(new EmailNotifier());
            
            List<Notifier> notifiers = notifier.getNotifiers();
            
            assertThatThrownBy(() -> notifiers.add(new SmsNotifier()))
                .isInstanceOf(UnsupportedOperationException.class);
        }
    }
    
    @Nested
    @DisplayName("Default-методы интерфейса")
    class DefaultMethodTests {
        
        @Test
        @DisplayName("sendToMultiple отправляет всем получателям")
        void shouldSendToMultipleRecipients() {
            EmailNotifier notifier = new EmailNotifier();
            
            int successCount = notifier.sendToMultiple(
                "Hello",
                "user1@example.com",
                "user2@example.com",
                "invalid-email"
            );
            
            assertThat(successCount).isEqualTo(2);
        }
        
        @Test
        @DisplayName("isAvailable по умолчанию возвращает true")
        void shouldBeAvailableByDefault() {
            Notifier notifier = new EmailNotifier();
            
            assertThat(notifier.isAvailable()).isTrue();
        }
    }
}

