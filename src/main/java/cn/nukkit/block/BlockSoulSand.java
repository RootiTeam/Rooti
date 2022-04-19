package cn.nukkit.block;

import cn.nukkit.entity.Entity;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.utils.BlockColor;

/**
 * Created by Pub4Game on 27.12.2015.
 */
public class BlockSoulSand extends BlockSolid {

    public BlockSoulSand() {
        this(0);
    }

    public BlockSoulSand(int meta) {
        super(0);
    }

    @Override
    public String getName() {
        return "Soul Sand";
    }

    @Override
    public int getId() {
        return SOUL_SAND;
    }

    @Override
    public double getHardness() {
        return 0.5;
    }

    @Override
    public double getResistance() {
        return 2.5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_SHOVEL;
    }

    @Override
    protected AxisAlignedBB recalculateBoundingBox() {
        return new AxisAlignedBB(
                this.x,
                this.y,
                this.z,
                this.x + 1,
                this.y + 1 - 0.125,
                this.z + 1
        );
    }

    @Override
    public boolean hasEntityCollision() {
        return true;
    }

    @Override
    public void onEntityCollide(Entity entity) {
        entity.motionX *= 0.4d;
        entity.motionZ *= 0.4d;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.SAND_BLOCK_COLOR;
    }

}
