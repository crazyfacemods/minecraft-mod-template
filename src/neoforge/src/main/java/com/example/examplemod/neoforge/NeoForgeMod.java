package com.example.examplemod.neoforge;

import com.mojang.brigadier.Command;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.command.BarCommand;
import com.example.examplemod.command.FooCommand;
import com.example.examplemod.command.HelloWorldCommand;
import com.example.examplemod.command.PlatformCommandContext;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(ExampleMod.MOD_ID)
public final class NeoForgeMod {
    private static final HelloWorldCommand HELLO_WORLD_COMMAND = new HelloWorldCommand();
    private static final FooCommand FOO_COMMAND = new FooCommand();
    private static final BarCommand BAR_COMMAND = new BarCommand();

    public NeoForgeMod(IEventBus eventBus) {
        ExampleMod.onModLoading("NeoForge");
        ExampleMod.init();
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
        ExampleMod.onModLoaded("NeoForge");
    }

    private void registerCommands(RegisterCommandsEvent event) {
        register(event, HELLO_WORLD_COMMAND);
        register(event, FOO_COMMAND);
        register(event, BAR_COMMAND);
    }

    private static void register(RegisterCommandsEvent event, com.example.examplemod.command.ExampleCommandDefinition command) {
        event.getDispatcher().register(
            Commands.literal(command.name())
                .executes(context -> command.execute(new PlatformCommandContext() {
                    @Override
                    public void sendSuccess(String message) {
                        context.getSource().sendSuccess(() -> Component.literal(message), false);
                    }

                    @Override
                    public boolean hasPermission(String permissionNode) {
                        return true;
                    }
                }))
        );
    }
}
