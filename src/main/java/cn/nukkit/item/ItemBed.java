package cn.nukkit.item;

import cn.nukkit.block.BlockBed;
import cn.nukkit.utils.DyeColor;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class ItemBed extends Item {

    public ItemBed() {
        this(0, 1);
    }

    public ItemBed(Integer meta) {
        this(meta, 1);
    }

    public ItemBed(Integer meta, int count) {
        super(BED, meta, count, DyeColor.getByDyedData(meta).getName() + " Bed");
        this.block = new BlockBed();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
