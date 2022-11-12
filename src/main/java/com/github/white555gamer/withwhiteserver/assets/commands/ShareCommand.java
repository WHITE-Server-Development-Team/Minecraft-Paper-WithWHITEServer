package com.github.white555gamer.withwhiteserver.assets.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.white555gamer.withwhiteserver.assets.messages.ListofMessages.INVALID_COMMAND_MESSAGE;
import static com.github.white555gamer.withwhiteserver.assets.messages.ListofMessages.SERVER_NON_EXECUTABLE_MESSAGE;

public class ShareCommand implements CommandExecutor, TabExecutor {

    private static String shareLocationMessage(@NotNull Location loc) {
        return "World: " + loc.getWorld().getName() + "\n" +
                "X(Block X): " + Math.floor(loc.getX() * 10) / 10 + "(" + loc.getBlockX() + ")\n" +
                "Y(Block Y): " + Math.floor(loc.getY() * 10) / 10 + "(" + loc.getBlockY() + ")\n" +
                "Z(Block Z): " + Math.floor(loc.getZ() * 10) / 10 + "(" + loc.getBlockZ() + ")";
    }

    private static final List<String> COMMAND_SUGGESTIONS = ImmutableList.of("NowLocation", "MainHandItem", "OffHandItem");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("NowLocation")) {

                player.chat(shareLocationMessage(player.getLocation()));

            } else if (args[0].equalsIgnoreCase("MainHandItem")) {

                player.chat(player.getInventory().getItemInMainHand().toString());

            } else if (args[0].equalsIgnoreCase("OffHandItem")) {

                player.chat(player.getInventory().getItemInOffHand().toString());

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
                return COMMAND_SUGGESTIONS;
            } else {
                return COMMAND_SUGGESTIONS.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
            }

        } else {
            return ImmutableList.of();
        }
    }
}
