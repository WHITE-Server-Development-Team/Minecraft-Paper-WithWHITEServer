package com.github.white555gamer.withwhiteserver.assets.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**プレイヤーログインイベントが発生したときの実行内容が格納されている。*/
public class LoginEvent {

    /**プレイヤーログインイベントが発生したときに実行される。
     * @param event PlayerJumpEvent*/
    public void execute(@NotNull PlayerLoginEvent event) {

        //Playerをいちいちフルで呼び出すのが面倒・長いため、一時的に代入する。
        Player player = event.getPlayer();

        //FirstPlayedが0のとき初回のログインだから0かどうかをチェックする。
        if (player.getFirstPlayed() == 0) {

            //鉄のシャベル、鉄のピッケル、鉄の斧、松明x64、焼肉x64を配布。
            player.sendMessage(
                    "WHITE555Gamerのサーバーへようこそ！\n初回ログインのため、いくつかの特典を差し上げます！");
            player.getInventory().addItem(
                    new ItemStack(Material.IRON_SHOVEL, 1),
                    new ItemStack(Material.IRON_PICKAXE, 1),
                    new ItemStack(Material.IRON_AXE, 1),
                    new ItemStack(Material.TORCH, 64),
                    new ItemStack(Material.COOKED_BEEF, 64));
            player.sendMessage(
                    "それではお楽しみください！\nこのサーバーでは追加でいくつかのコマンドが使えます。詳しくはDiscordの #マインクラフト を参照してください。");
        }

    }
}
