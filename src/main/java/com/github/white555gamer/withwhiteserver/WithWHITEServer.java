package com.github.white555gamer.withwhiteserver;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.github.white555gamer.withwhiteserver.assets.commands.PlayerNameCommand;
import com.github.white555gamer.withwhiteserver.assets.commands.ShareCommand;
import com.github.white555gamer.withwhiteserver.assets.commands.SpawnCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class WithWHITEServer extends JavaPlugin implements Listener {

    public Plugin getThisPlugin() {
        return this;
    }

    @Override
    public void onEnable() {
        getLogger().info("有効化開始...");

        getServer().getPluginManager().registerEvents(this,this);

        getCommand("share").setExecutor(new ShareCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("playername").setExecutor(new PlayerNameCommand());



        getLogger().info("有効化完了...");
    }

    @Override
    public void onLoad() {
        getLogger().info("ロード開始...");
        getLogger().info("ロード完了...");
    }

    @Override
    public void onDisable() {
        getLogger().info("無効化開始...");
        getLogger().info("無効化完了...");
    }

    @EventHandler
    public void onJumpEvent(PlayerJumpEvent event) {

        Player player = event.getPlayer();
        Location location = event.getPlayer().getLocation();

        if (player.getWorld().getName().equalsIgnoreCase("hub_world")) {
            if ((-1 < location.getX() && location.getX() < 1) && (-1 < location.getZ() && location.getZ() < 1)) {

                if (location.getY() <= 50) {

                    player.teleport(location.add(0, 6, 0));

                }

            }
        }

    }

    @EventHandler
    public void onShiftEvent(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        Location location = event.getPlayer().getLocation();

        if (player.isSneaking()) {
            if (player.getWorld().getName().equalsIgnoreCase("hub_world")) {
                if ((-1 < location.getX() && location.getX() < 1) && (-1 < location.getZ() && location.getZ() < 1)) {

                    if (location.getY() >= 7) {

                        player.teleport(location.add(0, -6, 0));

                    }

                }
            }
        }

    }

}
