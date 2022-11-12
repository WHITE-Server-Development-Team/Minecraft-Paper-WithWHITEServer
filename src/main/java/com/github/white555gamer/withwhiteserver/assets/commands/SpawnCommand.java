package com.github.white555gamer.withwhiteserver.assets.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.INVALID_COMMAND_MESSAGE;
import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.SERVER_NON_EXECUTABLE_MESSAGE;

public class SpawnCommand implements CommandExecutor, TabCompleter {

    /**引数でInitial指定の初期スポーンに帰還したときのメッセージ。(private static final)*/
    private static final String TELEPORT_INITIAL_SPAWN_MESSAGE = "初期スポーンに戻りました。";
    /**引数でBed指定のベッドスポーンに帰還したときのメッセージ。(private static final)*/
    private static final String TELEPORT_BED_SPAWN_MESSAGE = "ベッドスポーンに戻りました。";
    /**引数でBed指定のとき、ベッドスポーンに戻れないときのエラーのメッセージ。(private static final)*/
    private static final String ERROR_NON_EXIST_BED_SPAWN_MESSAGE = "ベッドが存在しないか、スポーン出来ない状態なため戻れません。";

    /**コマンドの候補。*/
    private static final List<String> COMMAND_SUGGESTIONS = ImmutableList.of("Initial", "Bed");

    /**onCommandメソッド。プレイヤーしか実行できない。プレイヤーかどうかを取得して、引数に応じた場所にteleportする。
     * @param sender コマンド実行者
     * @param command コマンド
     * @param label コマンドのラベル
     * @param args コマンドの引数
     * @return 常にtrue。*/
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

            //senderをそのままplayerに変換。instanceofで比較していて、senderなためチェックはしない。
            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("Initial")) {

                //スポーンロケーションを取得してそこにテレポートする。
                player.teleport(player.getWorld().getSpawnLocation());
                player.sendMessage(TELEPORT_INITIAL_SPAWN_MESSAGE);

            } else if (args[0].equalsIgnoreCase("Bed")) {

                //ベッドに触ってない場合はnullが帰ってくるので、その時はベッドがないことを伝える。
                if (player.getBedSpawnLocation() == null) {

                    player.sendMessage(ERROR_NON_EXIST_BED_SPAWN_MESSAGE);

                } else {
                    //ベッドスポーンロケーションを取得してそこにテレポートする。
                    player.teleport(player.getBedSpawnLocation());
                    player.sendMessage(TELEPORT_BED_SPAWN_MESSAGE);

                }

            } else {

                player.sendMessage(INVALID_COMMAND_MESSAGE);

            }

        } else {

            sender.sendMessage(SERVER_NON_EXECUTABLE_MESSAGE);

        }
        return true;
    }

    /**onTabCompleteメソッド。
     * @param sender コマンド実行者
     * @param command コマンド
     * @param alias コマンドのラベル
     * @param args コマンドの引数
     * @return 最初にすべての要素、何か入力されたら対応するものをStreamAPIを用いて返却する。何もなければ何もないものを返す。*/
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {

            if (args[0].length() == 0) {
                //すべて表示する。
                return COMMAND_SUGGESTIONS;
            } else {
                //StreamAPIで適するものだけを返却。
                return COMMAND_SUGGESTIONS.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
            }

        } else {
            //何もないを返却。
            return ImmutableList.of();
        }
    }
}
