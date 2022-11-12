package com.github.white555gamer.withwhiteserver.assets.commands;

import com.github.white555gamer.withwhiteserver.WithWHITEServer;
import com.google.common.collect.ImmutableList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.white555gamer.withwhiteserver.assets.messages.ListofMessages.INVALID_COMMAND_MESSAGE;
import static com.github.white555gamer.withwhiteserver.assets.messages.ListofMessages.SERVER_NON_EXECUTABLE_MESSAGE;

public class SpawnCommand implements CommandExecutor, TabCompleter {

    private static final String teleportInitialSpawnMessage = "初期スポーンに戻りました。";
    private static final String teleportBedSpawnMessage = "ベッドスポーンに戻りました。";
    private static final String errorNonExistBedSpawnMessage = "ベッドが存在しないか、スポーン出来ない状態なため戻れません。";

    private static final List<String> commandSuggestions = ImmutableList.of("Initial", "Bed");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("Initial")) {

                player.teleport(player.getWorld().getSpawnLocation());
                player.sendMessage(teleportInitialSpawnMessage);

            } else if (args[0].equalsIgnoreCase("Bed")) {

                if (player.getBedSpawnLocation() == null) {

                    player.sendMessage(errorNonExistBedSpawnMessage);

                } else {

                    player.teleport(player.getBedSpawnLocation());
                    player.sendMessage(teleportBedSpawnMessage);

                }

            } else {

                player.sendMessage(INVALID_COMMAND_MESSAGE);

            }

        } else {

            sender.sendMessage(SERVER_NON_EXECUTABLE_MESSAGE);

        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {

            if (args[0].length() == 0) {
                return commandSuggestions;
            } else {
                return commandSuggestions.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
            }

        } else {
            return ImmutableList.of();
        }
    }
}
