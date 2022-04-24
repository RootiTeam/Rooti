package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.ItemID;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

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
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        Block down = this.down();
if (down.getId() == Block.END_STONE || down.getId() == Block.CHORUS_PLANT || down.getId() == Block.CHORUS_FLOWER || down.getId() == Block.END_BRICKS || down.getId() == Block.AIR) {
            this.getLevel().setBlock(block, this, true);

            return true;
        }
        return false;
    }

	@Override
    public Item[] getDrops(Item item) {
        return new Item[]{this.toItem()};
    }
}