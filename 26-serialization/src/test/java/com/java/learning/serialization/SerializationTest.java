package com.java.learning.serialization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Serialization")
class SerializationTest {

    @Nested
    @DisplayName("SerializationUtils")
    class UtilsTest {
        
        @Test
        @DisplayName("serialize/deserialize сохраняют данные")
        void shouldSerializeAndDeserialize() throws Exception {
            SerializableEntity original = new SerializableEntity(
                "test", "secret", List.of("a", "b")
            );
            
            byte[] bytes = SerializationUtils.serialize(original);
            SerializableEntity restored = SerializationUtils.deserialize(bytes);
            
            assertThat(restored.getName()).isEqualTo("test");
            assertThat(restored.getTags()).containsExactly("a", "b");
        }
        
        @Test
        @DisplayName("transient поля не сериализуются")
        void transientFieldsShouldNotBeSerialized() throws Exception {
            SerializableEntity original = new SerializableEntity(
                "test", "secret123", List.of()
            );
            
            byte[] bytes = SerializationUtils.serialize(original);
            SerializableEntity restored = SerializationUtils.deserialize(bytes);
            
            assertThat(restored.getPassword()).isNull();
        }
        
        @Test
        @DisplayName("deepCopy создаёт независимую копию")
        void shouldCreateDeepCopy() throws Exception {
            SerializableEntity original = new SerializableEntity(
                "test", null, List.of("x")
            );
            
            SerializableEntity copy = SerializationUtils.deepCopy(original);
            
            assertThat(copy).isNotSameAs(original);
            assertThat(copy.getName()).isEqualTo(original.getName());
        }
        
        @Test
        @DisplayName("Сохранение и загрузка из файла")
        void shouldSaveAndLoadFromFile(@TempDir Path tempDir) throws Exception {
            String filename = tempDir.resolve("test.ser").toString();
            SerializableEntity original = new SerializableEntity(
                "file-test", null, List.of("tag")
            );
            
            SerializationUtils.saveToFile(original, filename);
            SerializableEntity loaded = SerializationUtils.loadFromFile(filename);
            
            assertThat(loaded.getName()).isEqualTo("file-test");
        }
    }
    
    @Nested
    @DisplayName("SerializableSingleton")
    class SingletonTest {
        
        @Test
        @DisplayName("Singleton сохраняет идентичность после десериализации")
        void shouldPreserveIdentity() throws Exception {
            SerializableSingleton original = SerializableSingleton.getInstance();
            
            byte[] bytes = SerializationUtils.serialize(original);
            SerializableSingleton restored = SerializationUtils.deserialize(bytes);
            
            assertThat(restored).isSameAs(original);
        }
    }
}

