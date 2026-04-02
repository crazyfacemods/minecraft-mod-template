package com.example.examplemod.command;

public interface ExampleCommandDefinition {
    String name();

    String permissionNode();

    int execute(PlatformCommandContext context);
}
