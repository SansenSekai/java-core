package com.java.learning.encapsulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Person: полная иммутабельность")
class PersonTest {
    
    private static final Address DEFAULT_ADDRESS = 
        new Address("123 Main St", "New York", "10001", "USA");
    
    @Nested
    @DisplayName("Создание Person")
    class CreationTests {
        
        @Test
        @DisplayName("Person создаётся с корректными данными")
        void shouldCreatePersonWithValidData() {
            List<String> hobbies = List.of("reading", "coding");
            
            Person person = new Person(
                "John Doe",
                LocalDate.of(1990, 5, 15),
                hobbies,
                DEFAULT_ADDRESS
            );
            
            assertThat(person.getName()).isEqualTo("John Doe");
            assertThat(person.getBirthDate()).isEqualTo(LocalDate.of(1990, 5, 15));
            assertThat(person.getHobbies()).containsExactly("reading", "coding");
            assertThat(person.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        }
        
        @Test
        @DisplayName("Нельзя создать Person с null именем")
        void shouldRejectNullName() {
            assertThatThrownBy(() -> new Person(
                null,
                LocalDate.of(1990, 1, 1),
                List.of(),
                DEFAULT_ADDRESS
            )).isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать Person с пустым именем")
        void shouldRejectEmptyName() {
            assertThatThrownBy(() -> new Person(
                "   ",
                LocalDate.of(1990, 1, 1),
                List.of(),
                DEFAULT_ADDRESS
            )).isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать Person с датой рождения в будущем")
        void shouldRejectFutureBirthDate() {
            LocalDate futureDate = LocalDate.now().plusYears(1);
            
            assertThatThrownBy(() -> new Person(
                "John",
                futureDate,
                List.of(),
                DEFAULT_ADDRESS
            )).isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать Person с null адресом")
        void shouldRejectNullAddress() {
            assertThatThrownBy(() -> new Person(
                "John",
                LocalDate.of(1990, 1, 1),
                List.of(),
                null
            )).isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    @Nested
    @DisplayName("Защита от модификации списка хобби")
    class HobbiesImmutabilityTests {
        
        @Test
        @DisplayName("Изменение исходного списка после создания Person не влияет на Person")
        void shouldNotBeAffectedByModifyingOriginalList() {
            List<String> hobbies = new ArrayList<>();
            hobbies.add("reading");
            
            Person person = new Person(
                "John",
                LocalDate.of(1990, 1, 1),
                hobbies,
                DEFAULT_ADDRESS
            );
            
            // Изменяем исходный список
            hobbies.add("hacking");
            hobbies.clear();
            
            // Person не должен измениться
            assertThat(person.getHobbies()).containsExactly("reading");
        }
        
        @Test
        @DisplayName("Изменение возвращённого списка не влияет на Person")
        void shouldNotBeAffectedByModifyingReturnedList() {
            Person person = new Person(
                "John",
                LocalDate.of(1990, 1, 1),
                List.of("reading"),
                DEFAULT_ADDRESS
            );
            
            List<String> hobbies = person.getHobbies();
            
            // Попытка модифицировать возвращённый список
            assertThatThrownBy(() -> hobbies.add("hacking"))
                .isInstanceOf(UnsupportedOperationException.class);
            
            // Убеждаемся, что Person не изменился
            assertThat(person.getHobbies()).containsExactly("reading");
        }
    }
    
    @Nested
    @DisplayName("Wither-методы")
    class WitherMethodTests {
        
        @Test
        @DisplayName("withHobby создаёт новый Person с добавленным хобби")
        void shouldCreateNewPersonWithAddedHobby() {
            Person original = new Person(
                "John",
                LocalDate.of(1990, 1, 1),
                List.of("reading"),
                DEFAULT_ADDRESS
            );
            
            Person updated = original.withHobby("coding");
            
            // Оригинал не изменился
            assertThat(original.getHobbies()).containsExactly("reading");
            
            // Новый Person содержит оба хобби
            assertThat(updated.getHobbies()).containsExactly("reading", "coding");
            
            // Это разные объекты
            assertThat(updated).isNotSameAs(original);
        }
        
        @Test
        @DisplayName("withAddress создаёт новый Person с новым адресом")
        void shouldCreateNewPersonWithNewAddress() {
            Person original = new Person(
                "John",
                LocalDate.of(1990, 1, 1),
                List.of("reading"),
                DEFAULT_ADDRESS
            );
            
            Address newAddress = new Address("456 Oak Ave", "Boston", "02101", "USA");
            Person updated = original.withAddress(newAddress);
            
            // Оригинал не изменился
            assertThat(original.getAddress()).isEqualTo(DEFAULT_ADDRESS);
            
            // Новый Person имеет новый адрес
            assertThat(updated.getAddress()).isEqualTo(newAddress);
        }
    }
    
    @Nested
    @DisplayName("Вычисляемые свойства")
    class ComputedPropertiesTests {
        
        @Test
        @DisplayName("getAge возвращает корректный возраст")
        void shouldCalculateCorrectAge() {
            LocalDate birthDate = LocalDate.now().minusYears(30).minusDays(1);
            
            Person person = new Person(
                "John",
                birthDate,
                List.of(),
                DEFAULT_ADDRESS
            );
            
            assertThat(person.getAge()).isEqualTo(30);
        }
        
        @Test
        @DisplayName("getAge для человека, у которого день рождения сегодня")
        void shouldCalculateAgeOnBirthday() {
            LocalDate birthDate = LocalDate.now().minusYears(25);
            
            Person person = new Person(
                "John",
                birthDate,
                List.of(),
                DEFAULT_ADDRESS
            );
            
            assertThat(person.getAge()).isEqualTo(25);
        }
    }
    
    @Nested
    @DisplayName("equals и hashCode")
    class EqualsHashCodeTests {
        
        @Test
        @DisplayName("Два Person с одинаковыми данными равны")
        void shouldBeEqualForSameData() {
            Person person1 = new Person(
                "John",
                LocalDate.of(1990, 1, 1),
                List.of("reading"),
                DEFAULT_ADDRESS
            );
            
            Person person2 = new Person(
                "John",
                LocalDate.of(1990, 1, 1),
                List.of("reading"),
                DEFAULT_ADDRESS
            );
            
            assertThat(person1).isEqualTo(person2);
            assertThat(person1.hashCode()).isEqualTo(person2.hashCode());
        }
        
        @Test
        @DisplayName("Два Person с разными именами не равны")
        void shouldNotBeEqualForDifferentNames() {
            Person person1 = new Person("John", LocalDate.of(1990, 1, 1), List.of(), DEFAULT_ADDRESS);
            Person person2 = new Person("Jane", LocalDate.of(1990, 1, 1), List.of(), DEFAULT_ADDRESS);
            
            assertThat(person1).isNotEqualTo(person2);
        }
    }
    
    @Nested
    @DisplayName("Класс объявлен как final")
    class FinalClassTests {
        
        @Test
        @DisplayName("Person является final классом")
        void shouldBeFinalClass() {
            assertThat(java.lang.reflect.Modifier.isFinal(Person.class.getModifiers()))
                .as("Person должен быть final классом для гарантии иммутабельности")
                .isTrue();
        }
    }
}

