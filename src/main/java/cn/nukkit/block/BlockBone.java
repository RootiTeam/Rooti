package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.Faceable;

/**
 * Created by ddosnikgit on 30.04.2022.
 */
public class BlockBone extends BlockSolid implements Faceable{

    private static final int[] FACES = {
            0,
            0,
            0b1000,
            0b1000,
            0b0100,
            0b0100
    };

	public BlockBone(int meta) {
		super(meta);
	}

	public BlockBone() {
		this(0);
	}

	@Override
	public String getName() {
		return "Bone Block";
	}

	@Override
	public int getId() {
		return BONE_BLOCK;
	}

	@Override
	public double getHardness() {
		return 2;
	}

	@Override
	public double getResistance() {
		return 10;
	}

	@Override
	public int getToolType() {
		return ItemTool.TYPE_PICKAXE;
	}

    @Override
    public BlockFace getBlockFace() {
        return BlockFace.fromHorizontalIndex(this.getDamage() & 0x7);
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        this.setDamage(((this.getDamage() & 0x3) | FACES[face.getIndex()]));
        this.getLevel().setBlock(block, this, true);
        return true;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= ItemTool.TIER_WOODEN) {
            return new Item[]{new ItemBlock(this)};
        }

        return new Item[0];
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}