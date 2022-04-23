package cn.nukkit.network.protocol;

import lombok.ToString;

/**
 * Created by ddosnikgit on 23.04.2022.
 */
@ToString
public class ServerToClientHandshakePacket extends DataPacket {

	public static final byte NETWORK_ID = ProtocolInfo.SERVER_TO_CLIENT_HANDSHAKE_PACKET;

	public String serverToken;
	public String publicKey;

    @Override
	public byte pid() {
		return NETWORK_ID;
	}

    public boolean canBeSentBeforeLogin() {
    	return true;
    }

    public void decode() {
    	publicKey = this.getString();
    	serverToken = this.getString();
    }

    public void encode() {
    	this.reset();
    	this.putString(publicKey);
    	this.putString(serverToken);
    }
}
