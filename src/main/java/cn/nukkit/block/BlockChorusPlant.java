package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.utils.ItemID;
import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.block.Block;
import cn.nukkit.math.BlockFace;
import cn.nukkit.Player;

import java.util.concurrent.ThreadLocalRandom;

/**
  * Created by ddosnikgit on 24.04.2022.
  */    
public class BlockChorusPlant extends BlockTransparent {

	public BlockChorusPlant() {
		this(0);
	}

    public BlockChorusPlant(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
    	return CHORUS_PLANT;
    }

    @Override
    public String getName() {
    	return "Chorus Plant";
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
    	return ItemTool.TYPE_AXE;
    }

    @Override
    public Item[] getDrops(Item item) {
    	return new Item[]{Item.get(ItemID.CHORUS_FRUIT, 0, 1)};
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        Block down = this.down();
               if (down.getId() == Block.END_STONE || down.getId() == Block.CHORUS_PLANT || down.getId() == Block.CHORUS_FLOWER || down.getId() == Block.END_BRICKS || down.getId() == Block.AIR) {
            this.getLevel().setBlock(block, this, true);

            return true;
        }
        return false;
    }

    @Override
    public BlockColor getColor() {
    	return BlockColor.PURPLE_BLOCK_COLOR;
    }
}