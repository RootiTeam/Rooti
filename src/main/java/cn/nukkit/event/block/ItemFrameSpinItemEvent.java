package cn.nukkit.event.block;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntityItemFrame;
import cn.nukkit.event.Cancellable;
import cn.nukkit.item.Item;
import cn.nukkit.event.HandlerList;

/**
 * Created by ddosnikgit on 03.07.2022.
 */
public class ItemFrameSpinItemEvent extends BlockEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
	private final Item item;
	private final Player player;
	private final BlockEntityItemFrame itemFrame;

	public ItemFrameSpinItemEvent(Player player, Block block, Item item, BlockEntityItemFrame itemFrame) {
		super(block);
		this.player = player;
		this.itemFrame = itemFrame;
		this.item = item;
	}

	public Player getPlayer() {
		return player;
	}

	public BlockEntityItemFrame getItemFrame() {
		return itemFrame;
	}

    public static HandlerList getHandlers() {
        return handlers;
    }

	public Item getItem() {
		return item;
	}
}