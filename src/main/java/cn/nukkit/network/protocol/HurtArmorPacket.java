package cn.nukkit.network.protocol;

import lombok.ToString;

/**
 * @author Nukkit Project Team
 */
@ToString
public class HurtArmorPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.HURT_ARMOR_PACKET;

    public int cause;
    public int health;
    public long armorSlots;

    @Override
    public void decode() {
        this.cause = getVarInt();
        this.health = getVarInt();
        this.armorSlots = getUnsignedVarLong();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarInt(this.cause);
        this.putVarInt(this.health);
        this.putUnsignedVarLong(this.armorSlots);
    }

    @Override
    public byte pid() {
        return NETWORK_ID;
        
    }

}
