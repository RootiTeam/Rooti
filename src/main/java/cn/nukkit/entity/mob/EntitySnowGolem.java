package cn.nukkit.entity.mob;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.AddEntityPacket;

/**
 * Created by ddosnikgit on 24.04.2022.
 */ 
public class EntitySnowGolem extends EntityMob {
	public static final int NETWORK_ID = 21;

	public EntitySnowGolem(FullChunk chunk, CompoundTag nbt) {
		super(chunk, nbt);
	}

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	@Override
	public void initEntity() {
		super.initEntity();
		this.setMaxHealth(4);
	}

    @Override
	public float getWidth() {
		return 0.7f;
	}

    @Override
	public float getHeight() {
		return 1.9f;
	}

	@Override
	public String getName() {
		return "Snow Golem";
	}

    @Override
    public void spawnTo(Player player) {
    	AddEntityPacket pk = new AddEntityPacket();
    	pk.type = this.getNetworkId();
    	pk.entityUniqueId = this.getId();
    	pk.entityRuntimeId = this.getId();
    	pk.x = (float) this.x;
    	pk.y = (float) this.y;
    	pk.z = (float) this.z;
    	pk.speedX = (float) this.motionX;
    	pk.speedY = (float) this.motionY;
    	pk.speedZ = (float) this.motionZ;
    	pk.metadata = this.dataProperties;
    	player.dataPacket(pk);

    	super.spawnTo(player);
    }

    @Override
    public Item[] getDrops() {
    	return new Item[]{Item.get(Item.SNOWBALL, this.level.rand.nextInt(2) + 1)};
    }
}