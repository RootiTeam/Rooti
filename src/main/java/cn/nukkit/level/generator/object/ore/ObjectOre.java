package cn.nukkit.level.generator.object.ore;

import cn.nukkit.block.Block;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector2;
import cn.nukkit.math.VectorMath;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class ObjectOre {

    private final NukkitRandom random;
    public final OreType type;
    private int replaceId;

    public ObjectOre(NukkitRandom random, OreType type) {
        this(random, type, Block.STONE);
    }

    public ObjectOre(NukkitRandom random, OreType type, int replaceId) {
        this.type = type;
        this.random = random;
        this.replaceId = replaceId;
    }

    public OreType getType() {
        return type;
    }

    public boolean canPlaceObject(ChunkManager level, int x, int y, int z) {
        return (level.getBlockIdAt(x, y, z) == replaceId);
    }

    public void placeObject(ChunkManager level, int x, int y, int z) {
        int clusterSize = this.type.clusterSize;
        double angle = this.random.nextFloat() * Math.PI;
        Vector2 offset = VectorMath.getDirection2D(angle).multiply(clusterSize).divide(8);
        double x1 = x + 8 + offset.x;
        double x2 = x + 8 - offset.x;
        double z1 = z + 8 + offset.y;
        double z2 = z + 8 - offset.y;
        double y1 = y + this.random.nextBoundedInt(3) + 2;
        double y2 = y + this.random.nextBoundedInt(3) + 2;
        for (int count = 0; count <= clusterSize; ++count) {
            double seedX = x1 + (x2 - x1) * count / clusterSize;
            double seedY = y1 + (y2 - y1) * count / clusterSize;
            double seedZ = z1 + (z2 - z1) * count / clusterSize;
            double size = ((Math.sin(count * (Math.PI / clusterSize)) + 1) * this.random.nextFloat() * clusterSize / 16 + 1) / 2;

            int startX = (int) (seedX - size);
            int startY = (int) (seedY - size);
            int startZ = (int) (seedZ - size);
            int endX = (int) (seedX + size);
            int endY = (int) (seedY + size);
            int endZ = (int) (seedZ + size);

            for (x = startX; x <= endX; ++x) {
                double sizeX = (x + 0.5 - seedX) / size;
                sizeX *= sizeX;

                if (sizeX < 1) {
                    for (y = startY; y <= endY; ++y) {
                        double sizeY = (y + 0.5 - seedY) / size;
                        sizeY *= sizeY;

                        if (y > 0 && (sizeX + sizeY) < 1) {
                            for (z = startZ; z <= endZ; ++z) {
                                double sizeZ = (z + 0.5 - seedZ) / size;
                                sizeZ *= sizeZ;

                                if ((sizeX + sizeY + sizeZ) < 1 && level.getBlockIdAt(x, y, z) == replaceId) {
                                    level.setBlockIdAt(x, y, z, this.type.material.getId());
                                    if (this.type.material.getDamage() != 0) {
                                        level.setBlockDataAt(x, y, z, this.type.material.getDamage());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
