package cn.nukkit.network.protocol;

import cn.nukkit.entity.Attribute;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.entity.item.EntityFishingHook;
import cn.nukkit.entity.mob.EntitySkeleton;
import cn.nukkit.utils.Binary;
import com.google.common.collect.ImmutableMap;
import lombok.ToString;
/**
 * author: MagicDroidX
 * Nukkit Project
 */
@ToString
public class AddEntityPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.ADD_ENTITY_PACKET;

    public static ImmutableMap<Integer, String> LEGACY_IDS = ImmutableMap.<Integer, String>builder()
            .put(51, "minecraft:npc")
            .put(63, "minecraft:player")
            .put(EntityFishingHook.NETWORK_ID, "minecraft:fishing_hook")
            .put(EntitySkeleton.NETWORK_ID, "minecraft:skeleton")
            .build();

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityUniqueId;
    public long entityRuntimeId;
    public int type;
    public float x;
    public float y;
    public float z;
    public float speedX = 0f;
    public float speedY = 0f;
    public float speedZ = 0f;
    public float yaw;
    public float pitch;
    public EntityMetadata metadata = new EntityMetadata();
    public Attribute[] attributes = new Attribute[0];
    public final Object[][] links = new Object[0][3];

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.entityUniqueId);
        this.putVarLong(this.entityRuntimeId);
        this.putUnsignedVarInt(this.type);
        this.putVector3f(this.x, this.y, this.z);
        this.putVector3f(this.speedX, this.speedY, this.speedZ);
        this.putLFloat(this.pitch);
        this.putLFloat(this.yaw);
        this.putAttributeList(this.attributes);
        this.put(Binary.writeMetadata(this.metadata));
        this.putUnsignedVarInt(this.links.length);
        for (Object[] link : this.links) {
            this.putVarLong((long) link[0]);
            this.putVarLong((long) link[1]);
            this.putByte((byte) link[2]);
        }
    }
}
