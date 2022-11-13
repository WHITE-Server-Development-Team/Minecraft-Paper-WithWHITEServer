package com.github.white555gamer.withwhiteserver.assets.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.HUB_WORLD_NAME;

/**ジャンプイベントが発生したときの実行内容が格納されている。*/
public class JumpEvent {

    /**
     *プレイヤーが有効範囲内にいるかどうかを確認するisPlayerInHubElevatorメソッド。
     * ハブにはエレベーターは一つしか設置しないのと、今後ハブにはエレベーターを増やす予定がないため、Hubかどうかの確認と有効範囲内かどうかの確認をまとめている。
     * （参考：見たら分かりますがハブの広さは50*50で上方向に今後拡張するタワー型の仕組みになっている。）
     * @param location プレイヤーの座標。
     * @return 有効範囲内にいればtrue、いなければfalse。
     */
    private boolean isPlayerInHubElevator(Location location) {
        return location.getWorld().getName().equalsIgnoreCase(HUB_WORLD_NAME) &&
                (-1 < location.getX() && location.getX() < 1) &&
                (-1 < location.getZ() && location.getZ() < 1);
    }

    /**ジャンプイベントが発生したときに実行される。
     * @param event PlayerJumpEvent
     */
    public void execute(@NotNull PlayerJumpEvent event) {
        //PlayerとLocationをいちいちフルで呼び出すのが面倒・長いため、一時的に代入する。
        Player player = event.getPlayer();
        Location location = player.getLocation();
        //ハブワールドでのみ有効。エレベーターの範囲でしか動作しないようにする。
        if (isPlayerInHubElevator(location)) {
            if (location.getY() <= 50) {
                //実質的にはteleportでエレベーターが動作する。ちなみに階層は6ブロックごとに区切られているからy座標に+6
                player.teleport(location.add(0, 6, 0));
            }
        }
    }
}
