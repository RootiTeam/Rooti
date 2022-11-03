package cn.nukkit.raknet.utils;

/**
 * Created by @ddosnikgit on 3-11-2022.
 * @link https://github.com/RootiTeam/Rooti
 */
public class InternetAddress{

	public String interfaz;
	public int port;
	public int version;

	public InternetAddress(String interfaz, int port, int version) {
		this.interfaz = interfaz;

		if (port < 1 || port > 65536) {
			throw new IllegalArgumentException("Invalid port range, you must used port much 1 and less 65536");
		}
		this.port = port;
		this.version = version;
	}

	public String getIp() {
		return this.interfaz;
	}

	public int getPort() {
		return this.port;
	}

	public int getVersion() {
		return this.version;
	}

	public String toString() {
		return this.interfaz + ":" + this.port;
	}
}