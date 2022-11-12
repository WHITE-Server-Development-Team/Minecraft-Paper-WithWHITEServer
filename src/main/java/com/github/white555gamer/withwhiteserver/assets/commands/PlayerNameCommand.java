package com.github.white555gamer.withwhiteserver.assets.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.white555gamer.withwhiteserver.assets.constants.ConstantProperty.*;
import static org.bukkit.Bukkit.getServer;

public class PlayerNameCommand implements CommandExecutor, TabCompleter {

    /**プレイヤーネームを変更したときのメッセージ。(private static final)*/
    private static final String SET_PLAYER_NAME_MESSAGE = "名前を変更しました。";

    /**onCommandメソッド。コンソールまたは鯖主しか実行できないようにしてから、名前を設定する。
     * @param sender コマンド実行者
     * @param command コマンド
     * @param label コマンドのラベル
     * @param args コマンドの引数
     * @return 常にtrue。*/
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //コンソールか鯖主かを判定。そうでないならこのコマンドは実行できないと表示。
        if (sender.getName().equalsIgnoreCase("WHITE555Gamer") || sender instanceof ConsoleCommandSender) {

            //引数が2の時だけ実行。空白は考慮しないので名前には空白を使用しないこと。
            if (args.length == 2) {

                //プレイヤーを取得。いない場合はnull判定で見つからないことをsenderに伝える。
                Player player = Bukkit.getPlayer(args[0]);
                if (player == null) {

                    sender.sendMessage(PLAYER_NOT_FOUND_MESSAGE);
                    return true;

                }

                //いた場合は順々に設定する。deprecatedが使われているが、私の過去作成のSpigotプラグインからの移植部分のためそのまま使用。将来的には変更するかも。
                player.setCustomName(args[1]);
                player.setDisplayName(args[1]);
                player.setPlayerListName(args[1]);
                player.sendMessage(SET_PLAYER_NAME_MESSAGE);

            } else {

                sender.sendMessage(INVALID_COMMAND_MESSAGE);

            }

        } else {

            sender.sendMessage(NON_EXECUTABLE_MESSAGE);

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
                //オンラインプレイヤーをそのままStreamAPIで名前に変換して返却。
                return getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            } else {
                //オンラインプレイヤーをStreamAPIで名前に変換して適する人だけを返却。
                return getServer().getOnlinePlayers().stream().map(Player::getName).map(s -> s.toLowerCase()).filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
            }

        } else {
            //何もないを返却。
            return ImmutableList.of();
        }
    }
}
