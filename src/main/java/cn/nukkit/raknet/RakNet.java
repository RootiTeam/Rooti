package cn.nukkit.raknet;

/**
 * author: MagicDroidX
 * Nukkit Project
 * UDP network library that follows the RakNet protocol for Nukkit Project
 * This is not affiliated with Jenkins Software LLC nor RakNet.
 */
public abstract class RakNet {

    public static final String VERSION = "1.1.7";
    public static final byte PROTOCOL = 6;
    public static final byte[] MAGIC = new byte[]{
            (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0x00,
            (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe,
            (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd,
            (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78
    };

    public static final byte PRIORITY_NORMAL = 0;
    public static final byte PRIORITY_IMMEDIATE = 1;

    public static final byte FLAG_NEED_ACK = 0b00001000;
}