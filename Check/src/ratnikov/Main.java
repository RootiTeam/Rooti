package ratnikov;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.Listener;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.Command;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.mob.EntitySkeleton;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;

public class Main extends PluginBase implements Listener {

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch(command.getName()) {
		    case "skeleton":
              sender.sendMessage("qq");
              break;
          }
          return true;
      }
  }