package cn.nukkit.network.protocol;

import lombok.ToString;
import cn.nukkit.item.Item;

@ToString
public class AddItemPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.ADD_ITEM_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public Item item;

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        this.putSlot(item);
    }
}
