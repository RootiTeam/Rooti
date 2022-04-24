package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

public class ClearCommand extends VanillaCommand {

    public GamemodeCommand(String name) {
        super(name, "Clear your inventory", "/clear",
                new String[]{"clear"});
        this.setPermission("nukkit.command.clear;");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new TranslationContainer("commands.generic.usage", this.usageMessage));
            return false;
        }
      
        CommandSender target = sender;
        if (args.length > 1) {
            if (sender.hasPermission("nukkit.command.clear")) {
                // CODE
            } else {
                sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.generic.permission"));
                return true;
            }
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(new TranslationContainer("commands.generic.usage", this.usageMessage));
            return true;
        }
}
