package com.github.white555gamer.withwhiteserver.assets.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**このクラスをイベント登録して、ここではイベントを適するイベントクラスに再分配して実行するようにしている。*/
public class EventRedistributionHandler implements Listener {

    /**ジャンプしたときのイベントの実行するクラス*/
    private JumpEvent jumpEvent;
    /**スニークしたときのイベントの実行するクラス*/
    private ToggleSneakEvent toggleSneakEvent;
    /**プレイヤーが入ってきたときのイベントの実行するクラス*/
    private LoginEvent loginEvent;

    /**コンストラクタ。引数なし。最初の呼び出しの時に代入しておく。*/
    public EventRedistributionHandler() {

        jumpEvent = new JumpEvent();
        toggleSneakEvent = new ToggleSneakEvent();
        loginEvent = new LoginEvent();

    }

    /**
     * ジャンプしたときに発生するイベント。そのままJumpEventクラスに丸投げしている。
     * @param event PlayerJumpEvent
     */
    @EventHandler
    public void onPlayerJumpEvent(PlayerJumpEvent event) {
        jumpEvent.execute(event);
    }

    /**
     * スニークしたときに発生するイベント。そのままtoggleSneakEventクラスに丸投げしている。
     * @param event PlayerJumpEvent
     */
    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        toggleSneakEvent.execute(event);
    }

    /**
     * プレイヤーが入ってきたときに発生するイベント。そのままloginEventクラスに丸投げしている。
     * @param event PlayerLoginEvent
     */
    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        loginEvent.execute(event);
    }

}
