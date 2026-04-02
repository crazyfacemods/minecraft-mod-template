package com.example.examplemod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static final String HELLO_WORLD_MESSAGE = "Hello World!";
    public static final String NO_PERMISSION_MESSAGE = "You do not have permission to use this command.";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static boolean initialized;

    private ExampleMod() {
    }

    public static void onModLoading(String platformName) {
        LOGGER.info("Loading {} on {}", MOD_ID, platformName);
    }

    public static boolean init() {
        boolean firstInitialization = markInitialized();
        if (firstInitialization) {
            LOGGER.info("Loaded {} common systems", MOD_ID);
        }

        return firstInitialization;
    }

    public static void onModLoaded(String platformName) {
        LOGGER.info("{} loaded on {}", MOD_ID, platformName);
    }

    public static String helloWorldMessage() {
        return HELLO_WORLD_MESSAGE;
    }

    public static void logHelloWorld(String platformName) {
        LOGGER.info("{} command on {} responded with: {}", MOD_ID, platformName, HELLO_WORLD_MESSAGE);
    }

    public static final class PermissionNodes {
        public static final String HELLO_WORLD = MOD_ID + ".command.hello_world";
        public static final String FOO = MOD_ID + ".command.foo";
        public static final String BAR = MOD_ID + ".command.bar";

        private PermissionNodes() {
        }
    }

    static boolean isInitialized() {
        return initialized;
    }

    static void resetInitializationForTests() {
        initialized = false;
    }

    private static boolean markInitialized() {
        if (initialized) {
            return false;
        }

        initialized = true;
        return true;
    }
}
