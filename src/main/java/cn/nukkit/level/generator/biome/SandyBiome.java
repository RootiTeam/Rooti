package cn.nukkit.level.generator.biome;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockSand;
import cn.nukkit.block.BlockSandstone;
import cn.nukkit.level.generator.populator.PopulatorCactus;
import cn.nukkit.level.generator.populator.PopulatorDeadBush;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public abstract class SandyBiome extends NormalBiome implements CaveBiome {
    public SandyBiome() {

        PopulatorCactus cactus = new PopulatorCactus();
        cactus.setBaseAmount(2);

        PopulatorDeadBush deadbush = new PopulatorDeadBush();
        deadbush.setBaseAmount(2);

        this.addPopulator(cactus);
        this.addPopulator(deadbush);

        this.setGroundCover(new Block[]{
                new BlockSand(),
                new BlockSand(),
                new BlockSandstone(),
                new BlockSandstone(),
                new BlockSandstone()
        });
    }

    @Override
    public int getSurfaceBlock() {
        return Block.SAND;
    }

    @Override
    public int getGroundBlock() {
        return Block.SAND;
    }

    @Override
    public int getStoneBlock() {
        return Block.SANDSTONE;
    }
}
