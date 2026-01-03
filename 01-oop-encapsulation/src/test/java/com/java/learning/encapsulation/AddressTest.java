package com.java.learning.encapsulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Address: immutable value object")
class AddressTest {
    
    @Nested
    @DisplayName("Создание Address")
    class CreationTests {
        
        @Test
        @DisplayName("Address создаётся с корректными данными")
        void shouldCreateAddressWithValidData() {
            Address address = new Address("123 Main St", "New York", "10001", "USA");
            
            assertThat(address.getStreet()).isEqualTo("123 Main St");
            assertThat(address.getCity()).isEqualTo("New York");
            assertThat(address.getZipCode()).isEqualTo("10001");
            assertThat(address.getCountry()).isEqualTo("USA");
        }
        
        @Test
        @DisplayName("Нельзя создать Address с null street")
        void shouldRejectNullStreet() {
            assertThatThrownBy(() -> new Address(null, "City", "12345", "Country"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать Address с пустым street")
        void shouldRejectEmptyStreet() {
            assertThatThrownBy(() -> new Address("  ", "City", "12345", "Country"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать Address с null city")
        void shouldRejectNullCity() {
            assertThatThrownBy(() -> new Address("Street", null, "12345", "Country"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать Address с null zipCode")
        void shouldRejectNullZipCode() {
            assertThatThrownBy(() -> new Address("Street", "City", null, "Country"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать Address с null country")
        void shouldRejectNullCountry() {
            assertThatThrownBy(() -> new Address("Street", "City", "12345", null))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    @Nested
    @DisplayName("Wither-методы")
    class WitherTests {
        
        @Test
        @DisplayName("withCity создаёт новый Address с изменённым городом")
        void shouldCreateNewAddressWithDifferentCity() {
            Address original = new Address("123 Main St", "New York", "10001", "USA");
            
            Address updated = original.withCity("Boston");
            
            // Оригинал не изменился
            assertThat(original.getCity()).isEqualTo("New York");
            
            // Новый адрес имеет другой город
            assertThat(updated.getCity()).isEqualTo("Boston");
            
            // Остальные поля не изменились
            assertThat(updated.getStreet()).isEqualTo("123 Main St");
            assertThat(updated.getZipCode()).isEqualTo("10001");
            assertThat(updated.getCountry()).isEqualTo("USA");
        }
        
        @Test
        @DisplayName("withStreet создаёт новый Address с изменённой улицей")
        void shouldCreateNewAddressWithDifferentStreet() {
            Address original = new Address("123 Main St", "New York", "10001", "USA");
            
            Address updated = original.withStreet("456 Oak Ave");
            
            assertThat(original.getStreet()).isEqualTo("123 Main St");
            assertThat(updated.getStreet()).isEqualTo("456 Oak Ave");
        }
    }
    
    @Nested
    @DisplayName("equals и hashCode")
    class EqualsHashCodeTests {
        
        @Test
        @DisplayName("Два Address с одинаковыми данными равны")
        void shouldBeEqualForSameData() {
            Address address1 = new Address("123 Main St", "New York", "10001", "USA");
            Address address2 = new Address("123 Main St", "New York", "10001", "USA");
            
            assertThat(address1).isEqualTo(address2);
            assertThat(address1.hashCode()).isEqualTo(address2.hashCode());
        }
        
        @Test
        @DisplayName("Два Address с разными данными не равны")
        void shouldNotBeEqualForDifferentData() {
            Address address1 = new Address("123 Main St", "New York", "10001", "USA");
            Address address2 = new Address("456 Oak Ave", "New York", "10001", "USA");
            
            assertThat(address1).isNotEqualTo(address2);
        }
        
        @Test
        @DisplayName("Address не равен null")
        void shouldNotBeEqualToNull() {
            Address address = new Address("123 Main St", "New York", "10001", "USA");
            
            assertThat(address).isNotEqualTo(null);
        }
        
        @Test
        @DisplayName("Address не равен объекту другого типа")
        void shouldNotBeEqualToDifferentType() {
            Address address = new Address("123 Main St", "New York", "10001", "USA");
            
            assertThat(address).isNotEqualTo("some string");
        }
    }
    
    @Nested
    @DisplayName("toString")
    class ToStringTests {
        
        @Test
        @DisplayName("toString содержит все поля")
        void shouldContainAllFields() {
            Address address = new Address("123 Main St", "New York", "10001", "USA");
            
            String str = address.toString();
            
            assertThat(str).contains("123 Main St");
            assertThat(str).contains("New York");
            assertThat(str).contains("10001");
            assertThat(str).contains("USA");
        }
    }
    
    @Nested
    @DisplayName("Класс объявлен как final")
    class FinalClassTests {
        
        @Test
        @DisplayName("Address является final классом")
        void shouldBeFinalClass() {
            assertThat(java.lang.reflect.Modifier.isFinal(Address.class.getModifiers()))
                .as("Address должен быть final классом для гарантии иммутабельности")
                .isTrue();
        }
    }
}

