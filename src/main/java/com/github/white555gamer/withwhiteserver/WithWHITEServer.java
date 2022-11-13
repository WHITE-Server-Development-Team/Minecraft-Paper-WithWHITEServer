package com.github.white555gamer.withwhiteserver;

import com.github.white555gamer.withwhiteserver.assets.commands.PlayerNameCommand;
import com.github.white555gamer.withwhiteserver.assets.commands.ShareCommand;
import com.github.white555gamer.withwhiteserver.assets.commands.SpawnCommand;
import com.github.white555gamer.withwhiteserver.assets.events.EventRedistributionHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class WithWHITEServer extends JavaPlugin {

    /**インスタンスの格納所。setter/getterを通して操作できる。*/
    private static WithWHITEServer instance = new WithWHITEServer();

    /**SHAREコマンドのラベル。*/
    private static final String SHARE_COMMAND_LABEL = "share";
    /**SPAWNコマンドのラベル。*/
    private static final String SPAWN_COMMAND_LABEL = "spawn";
    /**PLAYERNAMEコマンドのラベル。*/
    private static final String PLAYERNAME_COMMAND_LABEL = "playername";

    /**コンストラクタ(private)。これによって外部からnew出来なくし、Singletonパターンにする。*/
    private WithWHITEServer(){}

    /**onEnableメソッド。イベントの登録、コマンドの設定を順に行う。*/
    @Override
    public void onEnable() {
        getLogger().info("有効化開始...");

        //イベントを登録
        getServer().getPluginManager().registerEvents(new EventRedistributionHandler(), this);

        //コマンドを設定
        getCommand(SHARE_COMMAND_LABEL).setExecutor(new ShareCommand());
        getCommand(SPAWN_COMMAND_LABEL).setExecutor(new SpawnCommand());
        getCommand(PLAYERNAME_COMMAND_LABEL).setExecutor(new PlayerNameCommand());

        getLogger().info("有効化完了...");
    }

    /**onLoadメソッド。*/
    @Override
    public void onLoad() {
        getLogger().info("ロード開始...");
        getLogger().info("ロード完了...");
    }

    /**onDisableメソッド。*/
    @Override
    public void onDisable() {
        getLogger().info("無効化開始...");
        getLogger().info("無効化完了...");
    }

    /**
     * getInstanceメソッド。onEnableで代入したInstanceを外部から取得出来るようにする。
     * config.yml操作などで用いる予定だが、/configurationに取り回しをよくするための専用クラスを配置し、そこにconfig.ymlの大体の操作を置くので主な使用用途はconfigurationを想定。
     * 場合によってはそれ以外も考える。
     */
    public static WithWHITEServer getInstance() {
        return instance;
    }

}
