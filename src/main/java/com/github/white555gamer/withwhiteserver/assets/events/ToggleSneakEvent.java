package com.github.white555gamer.withwhiteserver.assets.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.HUB_WORLD_NAME;

public class ToggleSneakEvent {

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
