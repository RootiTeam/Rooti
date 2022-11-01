package cn.nukkit;

/**
 * Created by ddosnikgit on 01.11.2022.
 */

import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Binary;
import cn.nukkit.network.protocol.LoginPacket;

import java.util.UUID;

public final class PlayerInfo {

	public String username = "";
	public String iusername = "";
	public String displayName = "";
	public String languageCode = "";
	public int deviceOS = -1;
	public String deviceModel = "";
	public long randomClientId;
	public int protocol = 113;
	public int clientInput = -1;
	public UUID uuid;
	public byte[] rawUUID;

	public PlayerInfo(LoginPacket packet) {
		this.username = TextFormat.clean(packet.username);
		this.iusername = this.username.toLowerCase();
		this.displayName = this.username;
		this.languageCode = packet.languageCode;
		this.randomClientId = packet.clientId;
		this.protocol = packet.protocol;
		this.deviceOS = packet.deviceOS;
		this.deviceModel = packet.deviceModel;
		this.clientInput = packet.clientInput;
		this.uuid = packet.clientUUID;
		this.rawUUID = Binary.writeUUID(this.uuid);
	}

	public String getName() {
		return this.username;
	}

	public String getLowerCaseName() {
		return this.iusername;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public long getClientId() {
		return this.randomClientId;
	}

	public int getProtocol() {
		return this.protocol;
	}

	public int getDeviceOS() {
		return this.deviceOS;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public int getClientInput() {
		return this.clientInput;
	}

	public UUID getUUID() {
		return this.uuid;
	}
}