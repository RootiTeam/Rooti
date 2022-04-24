package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;

/**
  * Created by ddosnikgit on 24.04.2022.
  */
public class BlockChorusFlower extends BlockTransparent {

	public BlockChorusFlower() {
		this(0);
	}

    public BlockChorusFlower(int meta) {
        super(meta);
    }

	@Override
	public int getId() {
		return CHORUS_FLOWER;
	}

	@Override
	public String getName() {
		return "Chorus Flower";
	}

	@Override
	public double getHardness() {
		return 0.4;
	}

	@Override
	public double getResistance() {
		return 2;
	}

	@Override
	public int getToolType() {
		return ItemTool.TYPE_NONE;
	}

	@Override
    public Item[] getDrops(Item item) {
        return new Item[]{this.toItem()};
    }
}