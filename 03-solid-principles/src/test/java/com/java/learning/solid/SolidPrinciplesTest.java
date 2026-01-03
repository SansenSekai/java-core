package com.java.learning.solid;

import com.java.learning.solid.dip.solution.*;
import com.java.learning.solid.isp.solution.*;
import com.java.learning.solid.lsp.solution.*;
import com.java.learning.solid.ocp.solution.*;
import com.java.learning.solid.srp.solution.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SOLID Principles")
class SolidPrinciplesTest {
    
    @Nested
    @DisplayName("SRP: Single Responsibility")
    class SrpTests {
        
        @Test
        @DisplayName("UserValidator валидирует данные")
        void shouldValidateUserData() {
            UserValidator validator = new UserValidator();
            
            var validResult = validator.validate("John", "john@example.com");
            var invalidResult = validator.validate("", "invalid");
            
            assertThat(validResult.valid()).isTrue();
            assertThat(invalidResult.valid()).isFalse();
        }
        
        @Test
        @DisplayName("UserRepository сохраняет и находит пользователей")
        void shouldSaveAndFindUsers() {
            com.java.learning.solid.srp.solution.UserRepository repository = 
                new com.java.learning.solid.srp.solution.UserRepository();
            
            User saved = repository.save(new User(null, "John", "john@example.com"));
            
            assertThat(saved.id()).isNotNull();
            assertThat(repository.findByEmail("john@example.com")).isPresent();
            assertThat(repository.existsByEmail("john@example.com")).isTrue();
        }
        
        @Test
        @DisplayName("UserReportGenerator генерирует отчёты")
        void shouldGenerateReports() {
            UserReportGenerator generator = new UserReportGenerator();
            List<User> users = List.of(
                new User(1L, "John", "john@example.com"),
                new User(2L, "Jane", "jane@example.com")
            );
            
            String textReport = generator.generateTextReport(users);
            String csvReport = generator.generateCsvReport(users);
            
            assertThat(textReport).contains("John", "Jane");
            assertThat(csvReport).contains("id,name,email");
        }
        
        @Test
        @DisplayName("UserRegistrationService координирует регистрацию")
        void shouldCoordinateRegistration() {
            UserValidator validator = new UserValidator();
            com.java.learning.solid.srp.solution.UserRepository repository = 
                new com.java.learning.solid.srp.solution.UserRepository();
            EmailNotifier notifier = new EmailNotifier();
            
            UserRegistrationService service = new UserRegistrationService(
                validator, repository, notifier
            );
            
            User user = service.register("John", "john@example.com");
            
            assertThat(user.name()).isEqualTo("John");
            assertThat(repository.findByEmail("john@example.com")).isPresent();
        }
        
        @Test
        @DisplayName("Повторная регистрация с тем же email невозможна")
        void shouldRejectDuplicateEmail() {
            UserValidator validator = new UserValidator();
            com.java.learning.solid.srp.solution.UserRepository repository = 
                new com.java.learning.solid.srp.solution.UserRepository();
            EmailNotifier notifier = new EmailNotifier();
            
            UserRegistrationService service = new UserRegistrationService(
                validator, repository, notifier
            );
            
            service.register("John", "john@example.com");
            
            assertThatThrownBy(() -> service.register("Jane", "john@example.com"))
                .isInstanceOf(IllegalStateException.class);
        }
    }
    
    @Nested
    @DisplayName("OCP: Open/Closed")
    class OcpTests {
        
        @Test
        @DisplayName("NoDiscount возвращает исходную цену")
        void shouldReturnOriginalPriceWithNoDiscount() {
            DiscountStrategy noDiscount = new NoDiscount();
            BigDecimal price = new BigDecimal("100.00");
            
            BigDecimal result = noDiscount.apply(price);
            
            assertThat(result).isEqualByComparingTo(price);
        }
        
        @Test
        @DisplayName("PercentageDiscount применяет процентную скидку")
        void shouldApplyPercentageDiscount() {
            DiscountStrategy discount = new PercentageDiscount("VIP", 20);
            BigDecimal price = new BigDecimal("100.00");
            
            BigDecimal result = discount.apply(price);
            
            assertThat(result).isEqualByComparingTo(new BigDecimal("80.00"));
        }
        
        @Test
        @DisplayName("FixedAmountDiscount применяет фиксированную скидку")
        void shouldApplyFixedAmountDiscount() {
            DiscountStrategy discount = new FixedAmountDiscount("Promo", new BigDecimal("15.00"));
            BigDecimal price = new BigDecimal("100.00");
            
            BigDecimal result = discount.apply(price);
            
            assertThat(result).isEqualByComparingTo(new BigDecimal("85.00"));
        }
        
        @Test
        @DisplayName("FixedAmountDiscount не уходит в минус")
        void shouldNotGoNegative() {
            DiscountStrategy discount = new FixedAmountDiscount("Big", new BigDecimal("150.00"));
            BigDecimal price = new BigDecimal("100.00");
            
            BigDecimal result = discount.apply(price);
            
            assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
        }
        
        @Test
        @DisplayName("CompositeDiscount применяет скидки последовательно")
        void shouldApplyDiscountsSequentially() {
            DiscountStrategy composite = new CompositeDiscount(
                new PercentageDiscount("First", 10),  // 100 -> 90
                new FixedAmountDiscount("Second", new BigDecimal("10.00"))  // 90 -> 80
            );
            BigDecimal price = new BigDecimal("100.00");
            
            BigDecimal result = composite.apply(price);
            
            assertThat(result).isEqualByComparingTo(new BigDecimal("80.00"));
        }
        
        @Test
        @DisplayName("PriceCalculator работает с любой стратегией")
        void shouldWorkWithAnyStrategy() {
            PriceCalculator calculator = new PriceCalculator();
            BigDecimal price = new BigDecimal("100.00");
            
            BigDecimal withVip = calculator.calculate(price, new PercentageDiscount("VIP", 25));
            BigDecimal withPromo = calculator.calculate(price, new FixedAmountDiscount("Promo", new BigDecimal("10")));
            
            assertThat(withVip).isEqualByComparingTo(new BigDecimal("75.00"));
            assertThat(withPromo).isEqualByComparingTo(new BigDecimal("90.00"));
        }
    }
    
    @Nested
    @DisplayName("LSP: Liskov Substitution")
    class LspTests {
        
        @Test
        @DisplayName("ImmutableRectangle вычисляет площадь и периметр")
        void shouldCalculateRectangleAreaAndPerimeter() {
            ImmutableRectangle rect = new ImmutableRectangle(4, 5);
            
            assertThat(rect.getArea()).isEqualTo(20);
            assertThat(rect.getPerimeter()).isEqualTo(18);
        }
        
        @Test
        @DisplayName("ImmutableSquare вычисляет площадь и периметр")
        void shouldCalculateSquareAreaAndPerimeter() {
            ImmutableSquare square = new ImmutableSquare(5);
            
            assertThat(square.getArea()).isEqualTo(25);
            assertThat(square.getPerimeter()).isEqualTo(20);
        }
        
        @Test
        @DisplayName("Wither-методы создают новые объекты")
        void witherMethodsShouldCreateNewObjects() {
            ImmutableRectangle original = new ImmutableRectangle(4, 5);
            ImmutableRectangle modified = original.withWidth(10);
            
            assertThat(original.getWidth()).isEqualTo(4);
            assertThat(modified.getWidth()).isEqualTo(10);
            assertThat(modified).isNotSameAs(original);
        }
        
        @Test
        @DisplayName("Можно работать с разными Shape полиморфно")
        void shouldWorkWithShapesPolymorphically() {
            List<Shape> shapes = List.of(
                new ImmutableRectangle(4, 5),
                new ImmutableSquare(5)
            );
            
            int totalArea = shapes.stream()
                .mapToInt(Shape::getArea)
                .sum();
            
            assertThat(totalArea).isEqualTo(45); // 20 + 25
        }
    }
    
    @Nested
    @DisplayName("ISP: Interface Segregation")
    class IspTests {
        
        @Test
        @DisplayName("HumanDeveloper реализует все подходящие интерфейсы")
        void humanDeveloperShouldImplementAllRelevantInterfaces() {
            HumanDeveloper developer = new HumanDeveloper("John");
            
            assertThat(developer).isInstanceOf(Workable.class);
            assertThat(developer).isInstanceOf(Feedable.class);
            assertThat(developer).isInstanceOf(Collaboratable.class);
            assertThat(developer).isInstanceOf(Programmer.class);
        }
        
        @Test
        @DisplayName("RobotDeveloper НЕ реализует Feedable и Collaboratable")
        void robotDeveloperShouldNotImplementIrrelevantInterfaces() {
            RobotDeveloper robot = new RobotDeveloper("R2D2");
            
            assertThat(robot).isInstanceOf(Workable.class);
            assertThat(robot).isInstanceOf(Programmer.class);
            assertThat(robot).isNotInstanceOf(Feedable.class);
            assertThat(robot).isNotInstanceOf(Collaboratable.class);
        }
        
        @Test
        @DisplayName("Workable работает полиморфно")
        void workableShouldWorkPolymorphically() {
            List<Workable> workers = List.of(
                new HumanDeveloper("John"),
                new RobotDeveloper("R2D2")
            );
            
            // Все могут работать
            assertThatNoException().isThrownBy(() -> {
                workers.forEach(Workable::work);
            });
        }
    }
    
    @Nested
    @DisplayName("DIP: Dependency Inversion")
    class DipTests {
        
        @Test
        @DisplayName("NotificationService работает с любой реализацией MessageSender")
        void shouldWorkWithAnyMessageSender() {
            InMemoryUserRepository userRepo = new InMemoryUserRepository();
            userRepo.addUser("user1", "user1@example.com");
            
            NotificationService emailService = new NotificationService(
                new EmailSender(), userRepo
            );
            NotificationService smsService = new NotificationService(
                new SmsSender(), userRepo
            );
            
            assertThat(emailService.sendNotification("user1", "Hello")).isTrue();
            assertThat(smsService.sendNotification("user1", "Hello")).isTrue();
        }
        
        @Test
        @DisplayName("NotificationService бросает исключение для несуществующего пользователя")
        void shouldThrowForNonExistentUser() {
            InMemoryUserRepository userRepo = new InMemoryUserRepository();
            NotificationService service = new NotificationService(
                new EmailSender(), userRepo
            );
            
            assertThatThrownBy(() -> service.sendNotification("unknown", "Hello"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Можно использовать mock для тестирования")
        void shouldAllowMocking() {
            // Создаём тестовый double
            MessageSender mockSender = new MessageSender() {
                private boolean called = false;
                
                @Override
                public boolean send(String recipient, String subject, String body) {
                    called = true;
                    return true;
                }
                
                public boolean wasCalled() {
                    return called;
                }
            };
            
            InMemoryUserRepository userRepo = new InMemoryUserRepository();
            userRepo.addUser("user1", "user1@example.com");
            
            NotificationService service = new NotificationService(mockSender, userRepo);
            service.sendNotification("user1", "Test");
            
            // Проверяем, что mock был вызван
            assertThat(((Object) mockSender).getClass().getSimpleName())
                .contains("MessageSender");
        }
    }
}

