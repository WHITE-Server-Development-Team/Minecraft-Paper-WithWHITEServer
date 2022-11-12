package com.github.white555gamer.withwhiteserver.assets.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.HUB_WORLD_NAME;

public class JumpEvent {

    public void execute(PlayerJumpEvent event) {

        Player player = event.getPlayer();
        Location location = event.getPlayer().getLocation();

        if (player.getWorld().getName().equalsIgnoreCase(HUB_WORLD_NAME)) {
            if ((-1 < location.getX() && location.getX() < 1) && (-1 < location.getZ() && location.getZ() < 1)) {

                if (location.getY() <= 50) {

                    player.teleport(location.add(0, 6, 0));

                }

            }
        }

    }

}
