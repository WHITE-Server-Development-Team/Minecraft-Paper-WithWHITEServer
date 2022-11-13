package com.github.white555gamer.withwhiteserver.assets.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.INVALID_COMMAND_MESSAGE;
import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.SERVER_NON_EXECUTABLE_MESSAGE;

public class ShareCommand implements CommandExecutor, TabExecutor {

    /**
     * shareLocationMessageメソッド。内部で使用。座標は（使いやすさ・見やすさ的に）小数点第一位まで表示される。
     * @param loc プレイヤーの座標。
     * @return メッセージ形式でそのままplayer.chatが出来るように変換・返却。
     */
    private static String shareLocationMessage(@NotNull Location loc) {
        return "World: " + loc.getWorld().getName() + "\n" +
                "X(Block X): " + Math.floor(loc.getX() * 10) / 10 + "(" + loc.getBlockX() + ")\n" +
                "Y(Block Y): " + Math.floor(loc.getY() * 10) / 10 + "(" + loc.getBlockY() + ")\n" +
                "Z(Block Z): " + Math.floor(loc.getZ() * 10) / 10 + "(" + loc.getBlockZ() + ")";
    }

    /**コマンドの候補。*/
    private static final List<String> COMMAND_SUGGESTIONS = ImmutableList.of("nowlocation", "mainhanditem", "offhanditem");

    /**
     * onCommandメソッド。プレイヤーしか実行できない。プレイヤーかどうかを取得して、引数に応じたものを返す。
     * @param sender コマンド実行者
     * @param command コマンド
     * @param label コマンドのラベル
     * @param args コマンドの引数
     * @return 常にtrue。
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            //senderをそのままplayerに変換。instanceofで比較していて、senderなためnullチェックはしない。
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("NowLocation")) {
                //shareLocationMessageメソッドにプレイヤー座標を渡して返り値をそのままチャットするようにする。
                player.chat(shareLocationMessage(player.getLocation()));
            } else if (args[0].equalsIgnoreCase("MainHandItem")) {
                //そのままチャット。ItemStackの情報を見るためでもある。
                player.chat(player.getInventory().getItemInMainHand().toString());
            } else if (args[0].equalsIgnoreCase("OffHandItem")) {
                //そのままチャット。ItemStackの情報を見るためでもある。
                player.chat(player.getInventory().getItemInOffHand().toString());
            } else {
                player.sendMessage(INVALID_COMMAND_MESSAGE);
            }
        } else {
            sender.sendMessage(SERVER_NON_EXECUTABLE_MESSAGE);
        }
        return true;
    }

    /**
     * onTabCompleteメソッド。
     * @param sender コマンド実行者
     * @param command コマンド
     * @param alias コマンドのラベル
     * @param args コマンドの引数
     * @return 最初にすべての要素、何か入力されたら対応するものをStreamAPIを用いて返却する。何もなければ何も要素のないListを返す。
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].length() == 0) {
                //すべて表示する。
                return COMMAND_SUGGESTIONS;
            } else {
                //StreamAPIで適するものだけを返却。
                return COMMAND_SUGGESTIONS.stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
            }
        } else {
            //何も要素のないListを返却。
            return ImmutableList.of();
        }
    }
}
