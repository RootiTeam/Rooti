package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemCoal;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.enchantment.Enchantment;

import java.util.concurrent.ThreadLocalRandom;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class BlockOreCoal extends BlockSolid {

    public BlockOreCoal() {
        this(0);
    }

    public BlockOreCoal(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return COAL_ORE;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 15;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public String getName() {
        return "Coal Ore";
    }

    @Override
    public Item[] getDrops(Item item) {
       if (item.isPickaxe() && item.getTier() >= ItemTool.TIER_WOODEN) {
            int count = 1;
            Enchantment fortune = item.getEnchantment(Enchantment.ID_FORTUNE_DIGGING);
            if (fortune != null && fortune.getLevel() >= 1) {
                int i = ThreadLocalRandom.current().nextInt(fortune.getLevel() + 2) - 1;

                if (i < 0) {
                    i = 0;
                }

                count = i + 1;
            }
            return new Item[]{
                    new ItemCoal(0, count)
            };
        } else {
            return new Item[0];
        }
}

    @Override
    public int getDropExp() {
        return ThreadLocalRandom.current().nextInt(2);
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
