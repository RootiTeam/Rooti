package cn.nukkit.network.protocol;

import lombok.ToString;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
@ToString
public class EntityEventPacket extends DataPacket {
    public static final int NETWORK_ID = ProtocolInfo.ENTITY_EVENT_PACKET;


    public static final int HURT_ANIMATION = 2;
    public static final int DEATH_ANIMATION = 3;

    public static final int TAME_FAIL = 6;
    public static final int TAME_SUCCESS = 7;
    public static final int SHAKE_WET = 8;
    public static final int USE_ITEM = 9;
    public static final int EAT_GRASS_ANIMATION = 10;
    public static final int FISH_HOOK_BUBBLE = 11;
    public static final int FISH_HOOK_POSITION = 12;
    public static final int FISH_HOOK_HOOK = 13;
    public static final int FISH_HOOK_TEASE = 14;
    public static final int SQUID_INK_CLOUD = 15;
    public static final int AMBIENT_SOUND = 16;
    public static final int RESPAWN = 17;
    
    public static final int EATING_ITEM = 57;
    
    public static final int BABY_ANIMAL_FEED = 60;
    public static final int DEATH_SMOKE_CLOUD = 61;
    public static final int COMPLETE_TRADE = 62;
    public static final int REMOVE_LEASH = 63;
    
    public static final int CONSUME_TOTEM = 65;
    public static final int PLAYER_CHECK_TREASURE_HUNTER_ACHIEVEMENT = 66;
    public static final int ENTITY_SPAWN = 67;
    public static final int DRAGON_PUKE = 68;
    public static final int MERGE_ITEMS = 69;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long eid;
    public int event;
    public int data = 0;

    @Override
    public void decode() {
        this.eid = this.getVarLong();
        this.event = this.getByte();
        this.data = this.getVarInt();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.eid);
        this.putVarInt(this.event);
        this.putVarInt(this.data);
    }
}
