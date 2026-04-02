package com.example.examplemod.fabric;

import com.mojang.brigadier.Command;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.command.BarCommand;
import com.example.examplemod.command.FooCommand;
import com.example.examplemod.command.HelloWorldCommand;
import com.example.examplemod.command.PlatformCommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public final class FabricBootstrap implements ModInitializer {
    private static final HelloWorldCommand HELLO_WORLD_COMMAND = new HelloWorldCommand();
    private static final FooCommand FOO_COMMAND = new FooCommand();
    private static final BarCommand BAR_COMMAND = new BarCommand();

    @Override
    public void onInitialize() {
        ExampleMod.onModLoading("Fabric");
        ExampleMod.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            register(dispatcher, HELLO_WORLD_COMMAND);
            register(dispatcher, FOO_COMMAND);
            register(dispatcher, BAR_COMMAND);
        });
        ExampleMod.onModLoaded("Fabric");
    }

    private static void register(com.mojang.brigadier.CommandDispatcher<net.minecraft.commands.CommandSourceStack> dispatcher, com.example.examplemod.command.ExampleCommandDefinition command) {
        dispatcher.register(
            Commands.literal(command.name())
                .requires(source -> source.hasPermission(2))
                .executes(context -> command.execute(new PlatformCommandContext() {
                    @Override
                    public void sendSuccess(String message) {
                        context.getSource().sendSuccess(() -> Component.literal(message), false);
                    }

                    @Override
                    public boolean hasPermission(String permissionNode) {
                        return context.getSource().hasPermission(2);
                    }
                }))
        );
    }
}
