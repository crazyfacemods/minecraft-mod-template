package com.example.examplemod.command;

import com.example.examplemod.ExampleMod;

public final class BarCommand implements ExampleCommandDefinition {
    @Override
    public String name() {
        return "bar";
    }

    @Override
    public String permissionNode() {
        return ExampleMod.PermissionNodes.BAR;
    }

    @Override
    public int execute(PlatformCommandContext context) {
        if (!context.hasPermission(permissionNode())) {
            context.sendSuccess(ExampleMod.NO_PERMISSION_MESSAGE);
            return 0;
        }

        context.sendSuccess("Bar!");
        return 1;
    }
}
