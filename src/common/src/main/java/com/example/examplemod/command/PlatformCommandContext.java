package com.example.examplemod.command;

public interface PlatformCommandContext {
    void sendSuccess(String message);

    boolean hasPermission(String permissionNode);
}
