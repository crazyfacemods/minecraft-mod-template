package com.example.examplemod;

import com.example.examplemod.command.BarCommand;
import com.example.examplemod.command.FooCommand;
import com.example.examplemod.command.PlatformCommandContext;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class FooBarCommandTest {
    @Test
    void fooCommandSendsFoo() {
        AtomicReference<String> sentMessage = new AtomicReference<>();
        int result = new FooCommand().execute(allowAll(sentMessage));
        assertEquals(1, result);
        assertEquals("Foo!", sentMessage.get());
    }

    @Test
    void barCommandSendsBar() {
        AtomicReference<String> sentMessage = new AtomicReference<>();
        int result = new BarCommand().execute(allowAll(sentMessage));
        assertEquals(1, result);
        assertEquals("Bar!", sentMessage.get());
    }

    private static PlatformCommandContext allowAll(AtomicReference<String> sentMessage) {
        return new PlatformCommandContext() {
            @Override
            public void sendSuccess(String message) {
                sentMessage.set(message);
            }

            @Override
            public boolean hasPermission(String permissionNode) {
                return true;
            }
        };
    }
}
