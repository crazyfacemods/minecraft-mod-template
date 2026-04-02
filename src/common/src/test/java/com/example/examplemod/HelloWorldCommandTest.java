package com.example.examplemod;

import com.example.examplemod.command.HelloWorldCommand;
import com.example.examplemod.command.PlatformCommandContext;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class HelloWorldCommandTest {
    private final HelloWorldCommand command = new HelloWorldCommand();

    @Test
    void returnsExpectedHelloWorldMessage() {
        assertEquals(ExampleMod.HELLO_WORLD_MESSAGE, ExampleMod.helloWorldMessage());
    }

    @Test
    void logsHelloWorldWithoutThrowing() {
        assertDoesNotThrow(() -> ExampleMod.logHelloWorld("TestPlatform"));
    }

    @Test
    void executesHelloWorldCommandWhenPermissionIsGranted() {
        AtomicReference<String> sentMessage = new AtomicReference<>();

        int result = command.execute(new PlatformCommandContext() {
            @Override
            public void sendSuccess(String message) {
                sentMessage.set(message);
            }

            @Override
            public boolean hasPermission(String permissionNode) {
                return true;
            }
        });

        assertEquals(1, result);
        assertEquals(ExampleMod.HELLO_WORLD_MESSAGE, sentMessage.get());
    }

    @Test
    void deniesHelloWorldCommandWhenPermissionIsMissing() {
        AtomicReference<String> sentMessage = new AtomicReference<>();

        int result = command.execute(new PlatformCommandContext() {
            @Override
            public void sendSuccess(String message) {
                sentMessage.set(message);
            }

            @Override
            public boolean hasPermission(String permissionNode) {
                return false;
            }
        });

        assertEquals(0, result);
        assertEquals(ExampleMod.NO_PERMISSION_MESSAGE, sentMessage.get());
    }
}
