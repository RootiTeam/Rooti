package cn.nukkit.network.protocol;

import lombok.ToString;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
@ToString
public class EntityEventPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.ENTITY_EVENT_PACKET;


    public static final byte HURT_ANIMATION = 2;
    public static final byte DEATH_ANIMATION = 3;

    public static final byte TAME_FAIL = 6;
    public static final byte TAME_SUCCESS = 7;
    public static final byte SHAKE_WET = 8;
    public static final byte USE_ITEM = 9;
    public static final byte EAT_GRASS_ANIMATION = 10;
    public static final byte FISH_HOOK_BUBBLE = 11;
    public static final byte FISH_HOOK_POSITION = 12;
    public static final byte FISH_HOOK_HOOK = 13;
    public static final byte FISH_HOOK_TEASE = 14;
    public static final byte SQUID_INK_CLOUD = 15;
    public static final byte AMBIENT_SOUND = 16;
    public static final byte RESPAWN = 17;
    
    public static final byte EATING_ITEM = 57;
    
    public static final byte BABY_ANIMAL_FEED = 60;
    public static final byte DEATH_SMOKE_CLOUD = 61;
    public static final byte COMPLETE_TRADE = 62;
    public static final byte REMOVE_LEASH = 63;
    
    public static final byte CONSUME_TOTEM = 65;
    public static final byte PLAYER_CHECK_TREASURE_HUNTER_ACHIEVEMENT = 66;
    public static final byte ENTITY_SPAWN = 67;
    public static final byte DRAGON_PUKE = 68;
    public static final byte MERGE_ITEMS = 69;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long eid;
    public byte event;
    public int data = 0;

    @Override
    public void decode() {
        this.eid = this.getVarLong();
        this.event = (byte) this.getByte();
        this.data = this.getVarInt();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.eid);
        this.putByte(this.event);
        this.putVarInt(this.data);
    }
}
