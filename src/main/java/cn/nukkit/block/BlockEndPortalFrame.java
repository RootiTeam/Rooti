package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.math.AxisAlignedBB;

/**
 * Created by Pub4Game on 26.12.2015.
 */
public class BlockEndPortalFrame extends BlockTransparent {

    public BlockEndPortalFrame() {
        this(0);
    }

    public BlockEndPortalFrame(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return END_PORTAL_FRAME;
    }

    @Override
    public double getResistance() {
        return 18000000;
    }

    @Override
    public double getHardness() {
        return -1;
    }

    @Override
    public int getLightLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return "End Portal Frame";
    }

    @Override
    public boolean isBreakable(Item item) {
        return false;
    }

    @Override
    protected AxisAlignedBB recalculateBoundingBox() {
        return new AxisAlignedBB(
                x,
                y,
                z,
                x + 1,
                y + ((this.getDamage() & 0x04) > 0 ? 1 : 0.8125),
                z + 1
        );
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride() {
        return (meta & 4) != 0 ? 15 : 0;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        //TODO: ender eye
        //TODO: redstone comparator update
        return false;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
