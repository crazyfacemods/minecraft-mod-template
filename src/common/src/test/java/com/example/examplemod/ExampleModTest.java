package com.example.examplemod;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ExampleModTest {
    @AfterEach
    void tearDown() {
        ExampleMod.resetInitializationForTests();
    }

    @Test
    void exposesExpectedModId() {
        assertEquals("examplemod", ExampleMod.MOD_ID);
    }

    @Test
    void exposesLogger() {
        assertNotNull(ExampleMod.LOGGER);
    }

    @Test
    void startsUninitialized() {
        assertFalse(ExampleMod.isInitialized());
    }

    @Test
    void initMarksCommonSystemsAsLoadedOnce() {
        assertTrue(ExampleMod.init());
        assertTrue(ExampleMod.isInitialized());
        assertFalse(ExampleMod.init());
    }
}
