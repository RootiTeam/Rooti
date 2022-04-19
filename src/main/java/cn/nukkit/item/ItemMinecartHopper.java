package cn.nukkit.item;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.item.EntityMinecartHopper;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

public class ItemMinecartHopper extends Item {

    public ItemMinecartHopper() {
        this(0, 1);
    }

    public ItemMinecartHopper(Integer meta) {
        this(meta, 1);
    }

    public ItemMinecartHopper(Integer meta, int count) {
        super(MINECART_WITH_HOPPER, meta, count, "Minecart With Hopper");
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        Block secret = level.getBlock(block.add(0, -1, 0));
        // TODO: 2016/1/30 check if blockId of secret is a rail
        EntityMinecartHopper minecart = new EntityMinecartHopper(
                level.getChunk(block.getFloorX() >> 4, block.getFloorZ() >> 4), new CompoundTag("")
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", block.getX() + 0.5))
                        .add(new DoubleTag("", block.getY()))
                        .add(new DoubleTag("", block.getZ() + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
        );
        minecart.spawnToAll();

        // TODO: 2016/1/30 if player is survival, item in hand count--

        return true;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
