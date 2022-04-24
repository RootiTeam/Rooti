package cn.nukkit.level.generator.biome;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockDirt;
import cn.nukkit.block.BlockClay;
import cn.nukkit.block.BlockSand;
import cn.nukkit.block.BlockStone;

/**
 * author: Angelic47
 * Nukkit Project
 */
public abstract class WateryBiome extends NormalBiome implements CaveBiome {
    public WateryBiome() {
        this.setGroundCover(new Block[]{
                new BlockSand(),
                new BlockSand(),
                new BlockSand(),
                new BlockStone()
        });
    }

    @Override
    public int getSurfaceBlock() {
        return Block.DIRT;
    }

    @Override
    public int getGroundBlock() {
        return Block.DIRT;
    }

    @Override
    public int getStoneBlock() {
        return Block.STONE;
    }
}
