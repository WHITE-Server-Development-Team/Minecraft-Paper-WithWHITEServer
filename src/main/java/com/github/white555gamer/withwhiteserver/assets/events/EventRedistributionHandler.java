package com.github.white555gamer.withwhiteserver.assets.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class EventRedistributionHandler implements Listener {

    private JumpEvent jumpEvent;
    private ToggleSneakEvent toggleSneakEvent;

    public EventRedistributionHandler() {

        jumpEvent = new JumpEvent();
        toggleSneakEvent = new ToggleSneakEvent();

    }

    @EventHandler
    public void onJumpEvent(PlayerJumpEvent event) {
        jumpEvent.execute(event);
    }

    @EventHandler
    public void onShiftEvent(PlayerToggleSneakEvent event) {
        toggleSneakEvent.execute(event);
    }

}
