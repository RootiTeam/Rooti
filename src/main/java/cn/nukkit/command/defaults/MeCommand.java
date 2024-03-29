package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

/**
 * Created on 2015/11/12 by xtypr.
 * Package cn.nukkit.command.defaults in project Nukkit .
 */
public class MeCommand extends VanillaCommand {

    public MeCommand(String name) {
        super(name, "%nukkit.command.me.description", "%commands.me.usage");
        this.setPermission("nukkit.command.me");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("action ...", CommandParameter.ARG_TYPE_RAW_TEXT, false)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        String name;

        if (!this.testPermission(sender)) {
            return true;
        } else if (args.length == 0) {
            sender.sendMessage(new TranslationContainer("commands.generic.usage", this.usageMessage));

            return false;
        } else if (sender instanceof Player) {
            name = ((Player) sender).getPlayerInfo().getDisplayName();
        } else {
            name = sender.getName();
        }

        StringBuilder msg = new StringBuilder();
        for (String arg : args) {
            msg.append(arg).append(" ");
        }

        if (msg.length() > 0) {
            msg = new StringBuilder(msg.substring(0, msg.length() - 1));
        }

        sender.getServer().broadcastMessage(new TranslationContainer("chat.type.emote", new String[]{name, TextFormat.WHITE + msg.toString()}));

        return true;
    }
}
