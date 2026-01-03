package com.java.learning.equalshash;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("equals/hashCode Contract")
class EqualsHashCodeTest {
    
    @Nested
    @DisplayName("Money")
    class MoneyTests {
        
        @Test
        @DisplayName("Рефлексивность: x.equals(x)")
        void shouldBeReflexive() {
            Money money = new Money(new BigDecimal("100.00"), "USD");
            assertThat(money.equals(money)).isTrue();
        }
        
        @Test
        @DisplayName("Симметричность: x.equals(y) == y.equals(x)")
        void shouldBeSymmetric() {
            Money x = new Money(new BigDecimal("100.00"), "USD");
            Money y = new Money(new BigDecimal("100.00"), "USD");
            
            assertThat(x.equals(y)).isTrue();
            assertThat(y.equals(x)).isTrue();
        }
        
        @Test
        @DisplayName("Транзитивность")
        void shouldBeTransitive() {
            Money x = new Money(new BigDecimal("100.00"), "USD");
            Money y = new Money(new BigDecimal("100.00"), "USD");
            Money z = new Money(new BigDecimal("100.00"), "USD");
            
            assertThat(x.equals(y)).isTrue();
            assertThat(y.equals(z)).isTrue();
            assertThat(x.equals(z)).isTrue();
        }
        
        @Test
        @DisplayName("null-safety: x.equals(null) == false")
        void shouldBeNullSafe() {
            Money money = new Money(new BigDecimal("100.00"), "USD");
            assertThat(money.equals(null)).isFalse();
        }
        
        @Test
        @DisplayName("hashCode согласован с equals")
        void hashCodeShouldBeConsistentWithEquals() {
            Money x = new Money(new BigDecimal("100.00"), "USD");
            Money y = new Money(new BigDecimal("100.00"), "USD");
            
            assertThat(x.equals(y)).isTrue();
            assertThat(x.hashCode()).isEqualTo(y.hashCode());
        }
        
        @Test
        @DisplayName("Разные суммы не равны")
        void differentAmountsShouldNotBeEqual() {
            Money m1 = new Money(new BigDecimal("100.00"), "USD");
            Money m2 = new Money(new BigDecimal("200.00"), "USD");
            
            assertThat(m1).isNotEqualTo(m2);
        }
        
        @Test
        @DisplayName("Разные валюты не равны")
        void differentCurrenciesShouldNotBeEqual() {
            Money m1 = new Money(new BigDecimal("100.00"), "USD");
            Money m2 = new Money(new BigDecimal("100.00"), "EUR");
            
            assertThat(m1).isNotEqualTo(m2);
        }
        
        @Test
        @DisplayName("BigDecimal: 100.00 equals 100.0")
        void bigDecimalScaleShouldNotMatter() {
            Money m1 = new Money(new BigDecimal("100.00"), "USD");
            Money m2 = new Money(new BigDecimal("100.0"), "USD");
            
            // Важно! BigDecimal.equals() учитывает scale,
            // но для денег 100.00 == 100.0
            assertThat(m1).isEqualTo(m2);
        }
        
        @Test
        @DisplayName("Money работает как ключ HashMap")
        void shouldWorkAsHashMapKey() {
            Map<Money, String> map = new HashMap<>();
            Money key = new Money(new BigDecimal("100.00"), "USD");
            
            map.put(key, "value");
            
            Money sameKey = new Money(new BigDecimal("100.00"), "USD");
            assertThat(map.get(sameKey)).isEqualTo("value");
        }
        
        @Test
        @DisplayName("Money работает в HashSet")
        void shouldWorkInHashSet() {
            Set<Money> set = new HashSet<>();
            set.add(new Money(new BigDecimal("100.00"), "USD"));
            set.add(new Money(new BigDecimal("100.00"), "USD")); // дубликат
            set.add(new Money(new BigDecimal("200.00"), "USD"));
            
            assertThat(set).hasSize(2);
        }
    }
    
    @Nested
    @DisplayName("CacheKey")
    class CacheKeyTests {
        
        @Test
        @DisplayName("Одинаковые ключи равны")
        void shouldBeEqualForSameData() {
            CacheKey k1 = new CacheKey("ns", "key", "tag1", "tag2");
            CacheKey k2 = new CacheKey("ns", "key", "tag1", "tag2");
            
            assertThat(k1).isEqualTo(k2);
            assertThat(k1.hashCode()).isEqualTo(k2.hashCode());
        }
        
        @Test
        @DisplayName("Разный порядок тегов — разные ключи")
        void differentTagOrderShouldNotBeEqual() {
            CacheKey k1 = new CacheKey("ns", "key", "tag1", "tag2");
            CacheKey k2 = new CacheKey("ns", "key", "tag2", "tag1");
            
            assertThat(k1).isNotEqualTo(k2);
        }
        
        @Test
        @DisplayName("getTags возвращает копию")
        void getTagsShouldReturnDefensiveCopy() {
            CacheKey key = new CacheKey("ns", "key", "tag1");
            String[] tags = key.getTags();
            tags[0] = "hacked";
            
            assertThat(key.getTags()[0]).isEqualTo("tag1");
        }
    }
}

