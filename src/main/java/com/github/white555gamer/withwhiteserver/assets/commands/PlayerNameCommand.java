package com.github.white555gamer.withwhiteserver.assets.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.white555gamer.withwhiteserver.assets.messages.ListofMessages.*;
import static org.bukkit.Bukkit.getServer;

public class PlayerNameCommand implements CommandExecutor, TabCompleter {

    private static final String setPlayerNameMessage = "名前を変更しました。";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.getName().equalsIgnoreCase("WHITE555Gamer") || sender instanceof ConsoleCommandSender) {

            if (args.length == 2) {

                Player player = Bukkit.getPlayer(args[0]);
                if (player == null) {

                    sender.sendMessage(PLAYER_NOT_FOUND_MESSAGE);
                    return true;

                }
                player.setCustomName(args[1]);
                player.setDisplayName(args[1]);
                player.setPlayerListName(args[1]);
                player.sendMessage(setPlayerNameMessage);

            } else {

                sender.sendMessage(INVALID_COMMAND_MESSAGE);

            }

        } else {

            sender.sendMessage(NON_EXECUTABLE_MESSAGE);

        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {

            if (args[0].length() == 0) {
                return getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            } else {
                return getServer().getOnlinePlayers().stream().map(Player::getName).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
            }

        } else {
            return ImmutableList.of();
        }
    }
}
