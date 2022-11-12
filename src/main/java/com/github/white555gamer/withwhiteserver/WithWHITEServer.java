package com.github.white555gamer.withwhiteserver;

import com.github.white555gamer.withwhiteserver.assets.commands.PlayerNameCommand;
import com.github.white555gamer.withwhiteserver.assets.commands.ShareCommand;
import com.github.white555gamer.withwhiteserver.assets.commands.SpawnCommand;
import com.github.white555gamer.withwhiteserver.assets.events.EventRedistributionHandlers;
import org.bukkit.plugin.java.JavaPlugin;

public final class WithWHITEServer extends JavaPlugin {

    private static WithWHITEServer instance;

    private static final String SHARE_COMMAND_LABEL = "share";
    private static final String SPAWN_COMMAND_LABEL = "spawn";
    private static final String PLAYERNAME_COMMAND_LABEL = "playername";

    @Override
    public void onEnable() {
        getLogger().info("有効化開始...");

        instance = this;

        getServer().getPluginManager().registerEvents(new EventRedistributionHandlers(),this);

        getCommand(SHARE_COMMAND_LABEL).setExecutor(new ShareCommand());
        getCommand(SPAWN_COMMAND_LABEL).setExecutor(new SpawnCommand());
        getCommand(PLAYERNAME_COMMAND_LABEL).setExecutor(new PlayerNameCommand());

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

    public static WithWHITEServer getInstance() {
        return instance;
    }

}
