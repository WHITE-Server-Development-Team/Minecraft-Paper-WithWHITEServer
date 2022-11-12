package com.github.white555gamer.withwhiteserver.assets.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ToggleSneakEvent {

    private static final String HUB_WORLD_NAME = "hub_world";

    public void execute(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        Location location = event.getPlayer().getLocation();

        if (player.isSneaking()) {
            if (player.getWorld().getName().equalsIgnoreCase(HUB_WORLD_NAME)) {
                if ((-1 < location.getX() && location.getX() < 1) && (-1 < location.getZ() && location.getZ() < 1)) {

                    if (location.getY() >= 7) {

                        player.teleport(location.add(0, -6, 0));

                    }

                }
            }
        }

    }

}
