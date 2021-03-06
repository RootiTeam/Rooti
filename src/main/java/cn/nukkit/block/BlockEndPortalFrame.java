package cn.nukkit.block;

import cn.nukkit.math.Vector3;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.math.AxisAlignedBB;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride() {
        return (meta & 4) != 0 ? 15 : 0;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        if ((this.getDamage() & 0x04) == 0 && player != null && item.getId() == Item.ENDER_EYE) {
            this.setDamage(this.getDamage() + 4);
            this.getLevel().setBlock(this, this, true, true);
            //this.createPortal();
            return true;
        }
        return false;
    }
    
     public void createPortal() {
        Vector3 centerSpot = this.searchCenter(new ArrayList<>());
        if(centerSpot != null) {
            for(int x = -2; x <= 2; x++) {
                for(int z = -2; z <= 2; z++) {
                    if((x == -2 || x == 2) && (z == -2 || z == 2))
                        continue;
                    if(x == -2 || x == 2 || z == -2 || z == 2) {
                        if(!this.checkFrame(this.getLevel().getBlock(centerSpot.add(x, 0, z)), x, z)) {
                            return;
                        }
                    }
                }
            }

            for(int x = -1; x <= 1; x++) {
                for(int z = -1; z <= 1; z++) {
                    Vector3 vector3 = centerSpot.add(x, 0, z);
                    if(this.getLevel().getBlock(vector3).getId() != Block.AIR) {
                        this.getLevel().useBreakOn(vector3);
                    }
                    this.getLevel().setBlock(vector3, Block.get(Block.END_PORTAL));
                }
            }
        }
    }

    private Vector3 searchCenter(List<Block> visited) {
        for(int x = -2; x <= 2; x++) {
            if(x == 0)
                continue;
            Block block = this.getLevel().getBlock(this.add(x, 0, 0));
            Block iBlock = this.getLevel().getBlock(this.add(x * 2, 0, 0));
            if(this.checkFrame(block) && !visited.contains(block)) {
                visited.add(block);
                if((x == -1 || x == 1) && this.checkFrame(iBlock))
                    return ((BlockEndPortalFrame) block).searchCenter(visited);
                for(int z = -4; z <= 4; z++) {
                    if(z == 0)
                        continue;
                    block = this.getLevel().getBlock(this.add(x, 0, z));
                    if(this.checkFrame(block)) {
                        return this.add(x / 2, 0, z / 2);
                    }
                }
            }
        }
        for(int z = -2; z <= 2; z++) {
            if(z == 0)
                continue;
            Block block = this.getLevel().getBlock(this.add(0, 0, z));
            Block iBlock = this.getLevel().getBlock(this.add(0, 0, z * 2));
            if(this.checkFrame(block) && !visited.contains(block)) {
                visited.add(block);
                if((z == -1 || z == 1) && this.checkFrame(iBlock))
                    return ((BlockEndPortalFrame) block).searchCenter(visited);
                for(int x = -4; x <= 4; x++) {
                    if(x == 0)
                        continue;
                    block = this.getLevel().getBlock(this.add(x, 0, z));
                    if(this.checkFrame(block)) {
                        return this.add(x / 2, 0, z / 2);
                    }
                }
            }
        }
        return null;
    }

    private boolean checkFrame(Block block) {
        return block.getId() == this.getId() && (block.getDamage() & 4) == 4;
    }

    private boolean checkFrame(Block block, int x, int z) {
        return block.getId() == this.getId() && (block.getDamage() - 4) == (x == -2 ? 3 : x == 2 ? 1 : z == -2 ? 0 : z == 2 ? 2 : -1);
    }
    
    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
