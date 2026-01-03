package com.java.learning.encapsulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("AuditLog: защита от модификации")
class AuditLogTest {
    
    @Nested
    @DisplayName("Добавление записей")
    class AddingEntriesTests {
        
        @Test
        @DisplayName("Можно добавить запись с корректными данными")
        void shouldAddValidEntry() {
            AuditLog log = new AuditLog();
            
            log.addEntry("User logged in", "john.doe");
            
            assertThat(log.size()).isEqualTo(1);
        }
        
        @Test
        @DisplayName("Нельзя добавить запись с null action")
        void shouldRejectNullAction() {
            AuditLog log = new AuditLog();
            
            assertThatThrownBy(() -> log.addEntry(null, "john.doe"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя добавить запись с пустым action")
        void shouldRejectEmptyAction() {
            AuditLog log = new AuditLog();
            
            assertThatThrownBy(() -> log.addEntry("   ", "john.doe"))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя добавить запись с null user")
        void shouldRejectNullUser() {
            AuditLog log = new AuditLog();
            
            assertThatThrownBy(() -> log.addEntry("Some action", null))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Записи имеют корректный timestamp")
        void shouldHaveCorrectTimestamp() {
            AuditLog log = new AuditLog();
            LocalDateTime before = LocalDateTime.now();
            
            log.addEntry("Test action", "user");
            
            LocalDateTime after = LocalDateTime.now();
            AuditLog.AuditEntry entry = log.getEntries().get(0);
            
            assertThat(entry.timestamp())
                .isAfterOrEqualTo(before)
                .isBeforeOrEqualTo(after);
        }
    }
    
    @Nested
    @DisplayName("Защита списка записей")
    class ListProtectionTests {
        
        @Test
        @DisplayName("Возвращённый список нельзя модифицировать через add")
        void shouldNotAllowAddingToReturnedList() {
            AuditLog log = new AuditLog();
            log.addEntry("First", "user");
            
            List<AuditLog.AuditEntry> entries = log.getEntries();
            
            assertThatThrownBy(() -> 
                entries.add(new AuditLog.AuditEntry(LocalDateTime.now(), "Hacked", "hacker"))
            ).isInstanceOf(UnsupportedOperationException.class);
        }
        
        @Test
        @DisplayName("Возвращённый список нельзя модифицировать через clear")
        void shouldNotAllowClearingReturnedList() {
            AuditLog log = new AuditLog();
            log.addEntry("First", "user");
            
            List<AuditLog.AuditEntry> entries = log.getEntries();
            
            assertThatThrownBy(entries::clear)
                .isInstanceOf(UnsupportedOperationException.class);
        }
        
        @Test
        @DisplayName("Возвращённый список нельзя модифицировать через remove")
        void shouldNotAllowRemovingFromReturnedList() {
            AuditLog log = new AuditLog();
            log.addEntry("First", "user");
            
            List<AuditLog.AuditEntry> entries = log.getEntries();
            
            assertThatThrownBy(() -> entries.remove(0))
                .isInstanceOf(UnsupportedOperationException.class);
        }
        
        @Test
        @DisplayName("После попытки модификации оригинальный лог не изменяется")
        void shouldNotAffectOriginalLogAfterModificationAttempt() {
            AuditLog log = new AuditLog();
            log.addEntry("First", "user");
            log.addEntry("Second", "user");
            
            List<AuditLog.AuditEntry> entries = log.getEntries();
            try {
                entries.clear();
            } catch (UnsupportedOperationException ignored) {
            }
            
            assertThat(log.size()).isEqualTo(2);
            assertThat(log.getEntries()).hasSize(2);
        }
    }
    
    @Nested
    @DisplayName("Фильтрация записей")
    class FilteringTests {
        
        @Test
        @DisplayName("getEntriesByUser возвращает только записи указанного пользователя")
        void shouldFilterByUser() {
            AuditLog log = new AuditLog();
            log.addEntry("Action 1", "alice");
            log.addEntry("Action 2", "bob");
            log.addEntry("Action 3", "alice");
            log.addEntry("Action 4", "charlie");
            
            List<AuditLog.AuditEntry> aliceEntries = log.getEntriesByUser("alice");
            
            assertThat(aliceEntries).hasSize(2);
            assertThat(aliceEntries).allMatch(e -> e.user().equals("alice"));
        }
        
        @Test
        @DisplayName("getEntriesByUser возвращает пустой список для несуществующего пользователя")
        void shouldReturnEmptyListForNonExistentUser() {
            AuditLog log = new AuditLog();
            log.addEntry("Action", "alice");
            
            List<AuditLog.AuditEntry> entries = log.getEntriesByUser("nobody");
            
            assertThat(entries).isEmpty();
        }
        
        @Test
        @DisplayName("getEntriesBetween возвращает записи в указанном диапазоне")
        void shouldFilterByDateRange() throws InterruptedException {
            AuditLog log = new AuditLog();
            
            LocalDateTime before = LocalDateTime.now().minusSeconds(1);
            log.addEntry("Action 1", "user");
            Thread.sleep(10);
            LocalDateTime middle = LocalDateTime.now();
            Thread.sleep(10);
            log.addEntry("Action 2", "user");
            LocalDateTime after = LocalDateTime.now().plusSeconds(1);
            
            List<AuditLog.AuditEntry> entriesInRange = log.getEntriesBetween(middle, after);
            
            assertThat(entriesInRange).hasSize(1);
            assertThat(entriesInRange.get(0).action()).isEqualTo("Action 2");
        }
        
        @Test
        @DisplayName("Отфильтрованный список также нельзя модифицировать")
        void shouldReturnUnmodifiableFilteredList() {
            AuditLog log = new AuditLog();
            log.addEntry("Action", "alice");
            
            List<AuditLog.AuditEntry> filtered = log.getEntriesByUser("alice");
            
            assertThatThrownBy(() -> 
                filtered.add(new AuditLog.AuditEntry(LocalDateTime.now(), "Hack", "hacker"))
            ).isInstanceOf(UnsupportedOperationException.class);
        }
    }
}

