package com.example.examplemod.command;

import com.example.examplemod.ExampleMod;

public final class FooCommand implements ExampleCommandDefinition {
    @Override
    public String name() {
        return "foo";
    }

    @Override
    public String permissionNode() {
        return ExampleMod.PermissionNodes.FOO;
    }

    @Override
    public int execute(PlatformCommandContext context) {
        if (!context.hasPermission(permissionNode())) {
            context.sendSuccess(ExampleMod.NO_PERMISSION_MESSAGE);
            return 0;
        }

        context.sendSuccess("Foo!");
        return 1;
    }
}
