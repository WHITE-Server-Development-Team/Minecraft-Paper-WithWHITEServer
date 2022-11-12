package com.github.white555gamer.withwhiteserver.assets.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.HUB_WORLD_NAME;

/**ジャンプイベントが発生したときの実行内容が格納されている。*/
public class JumpEvent {

    /**ジャンプイベントが発生したときに実行される。
     * @param event PlayerJumpEvent*/
    public void execute(@NotNull PlayerJumpEvent event) {

        //PlayerとLocationをいちいちフルで呼び出すのが面倒・長いため、一時的に代入する。
        Player player = event.getPlayer();
        Location location = event.getPlayer().getLocation();

        //ハブワールドでのみ有効・エレベーターの範囲でしか動作しないようにする。
        if (player.getWorld().getName().equalsIgnoreCase(HUB_WORLD_NAME)) {
            if ((-1 < location.getX() && location.getX() < 1) && (-1 < location.getZ() && location.getZ() < 1)) {

                if (location.getY() <= 50) {

                    //実質的にはteleportでエレベーターが動作する。ちなみに階層は6ブロックごとに区切られているからy座標に+6
                    player.teleport(location.add(0, 6, 0));

                }

            }
        }

    }

}
