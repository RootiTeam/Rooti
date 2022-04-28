package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

public class ClearCommand extends VanillaCommand {

    public ClearCommand(String name) {
        super(name, "%nukkit.command.clear.description", "/clear",
                new String[]{"clear"});
        this.setPermission("nukkit.command.clear;");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.generic.ingame"));
            return true;
        } else {
             if (sender.hasPermission("nukkit.command.clear")) {
                CommandSender target = sender;
                ((Player) target).getInventory().clearAll();
                sender.sendMessage(new TranslationContainer(TextFormat.GREEN + "%nukkit.command.clear.success");
            } else {
                sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.generic.permission"));
                return true;
                }
            }
        return true;
        }
}
