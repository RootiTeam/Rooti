package cn.nukkit.network.protocol;

import lombok.ToString;

/**
 * Created by ddosnikgit on 23.04.2022.
 */
@ToString
public class CameraPacket extends DataPacket {

	public static final byte NETWORK_ID = ProtocolInfo.CAMERA_PACKET;

	public long eid;

	@Override
	public void decode() {

	}

	@Override
	public byte pid() {
		return NETWORK_ID;
	}

	@Override
	public void encode() {
		this.reset();
		this.putVarLong(eid);
	}

	public String getName() {
		return "CameraPacket";
	}
}