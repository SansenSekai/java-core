package com.java.learning.modules;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Java Modules (JPMS)")
class ModuleExampleTest {

    @Test
    @DisplayName("Можно получить Module текущего класса")
    void shouldGetCurrentModule() {
        Module module = ModuleExampleTest.class.getModule();
        
        // В безмодульном проекте (classpath) модуль будет unnamed
        assertThat(module).isNotNull();
    }
    
    @Test
    @DisplayName("Unnamed module для classpath проекта")
    void shouldBeUnnamedForClasspathProject() {
        Module module = ModuleExampleTest.class.getModule();
        
        // Этот проект не использует module-info.java,
        // поэтому все классы в "unnamed module"
        assertThat(module.isNamed()).isFalse();
    }
    
    @Test
    @DisplayName("java.base — именованный модуль")
    void javaBaseShouldBeNamedModule() {
        Module javaBase = String.class.getModule();
        
        assertThat(javaBase.isNamed()).isTrue();
        assertThat(javaBase.getName()).isEqualTo("java.base");
    }
    
    @Test
    @DisplayName("demonstrateModules выполняется без ошибок")
    void demonstrateModulesShouldRun() {
        assertThatNoException().isThrownBy(ModuleExample::demonstrateModules);
    }
}

