package com.example.examplemod.command;

import com.example.examplemod.ExampleMod;

public final class HelloWorldCommand implements ExampleCommandDefinition {
    @Override
    public String name() {
        return "hello-world";
    }

    @Override
    public String permissionNode() {
        return ExampleMod.PermissionNodes.HELLO_WORLD;
    }

    @Override
    public int execute(PlatformCommandContext context) {
        if (!context.hasPermission(permissionNode())) {
            context.sendSuccess(ExampleMod.NO_PERMISSION_MESSAGE);
            return 0;
        }

        context.sendSuccess(ExampleMod.helloWorldMessage());
        ExampleMod.logHelloWorld("command");
        return 1;
    }
}
