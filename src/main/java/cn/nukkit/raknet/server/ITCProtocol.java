package cn.nukkit.raknet.server;

/**
 * This interface contains descriptions of ITC packets used to transmit data into RakNet from the main thread.
 */
public interface ITCProtocol{

	/*
	 * These internal "packets" DO NOT exist in the RakNet protocol. They are used by the RakNet API to communicate
	 * messages between the RakLib thread and the implementation's thread.
	 *
	 * Internal Packet:
	 * byte (packet ID)
	 * byte[] (payload)
	 */

	/*
     * ENCAPSULATED payload:
     * byte (identifier length)
     * byte[] (identifier)
     * byte (flags, last 3 bits, priority)
     * payload (binary internal EncapsulatedPacket)
     */
    public static final byte PACKET_ENCAPSULATED = 0x01;

    /*
     * OPEN_SESSION payload:
     * byte (identifier length)
     * byte[] (identifier)
     * byte (address length)
     * byte[] (address)
     * short (port)
     * long (clientID)
     */
    public static final byte PACKET_OPEN_SESSION = 0x02;

    /*
     * CLOSE_SESSION payload:
     * byte (identifier length)
     * byte[] (identifier)
     * string (reason)
     */
    public static final byte PACKET_CLOSE_SESSION = 0x03;

    /*
     * INVALID_SESSION payload:
     * byte (identifier length)
     * byte[] (identifier)
     */
    public static final byte PACKET_INVALID_SESSION = 0x04;

    /* SEND_QUEUE payload:
     * byte (identifier length)
     * byte[] (identifier)
     */
    public static final byte PACKET_SEND_QUEUE = 0x05;

    /*
     * ACK_NOTIFICATION payload:
     * byte (identifier length)
     * byte[] (identifier)
     * int (identifierACK)
     */
    public static final byte PACKET_ACK_NOTIFICATION = 0x06;

    /*
     * SET_OPTION payload:
     * byte (option name length)
     * byte[] (option name)
     * byte[] (option value)
     */
    public static final byte PACKET_SET_OPTION = 0x07;

    /*
     * RAW payload:
     * byte (address length)
     * byte[] (address from/to)
     * short (port)
     * byte[] (payload)
     */
    public static final byte PACKET_RAW = 0x08;

    /*
     * BLOCK_ADDRESS payload:
     * byte (address length)
     * byte[] (address)
     * int (timeout)
     */
    public static final byte PACKET_BLOCK_ADDRESS = 0x09;

    /*
     * UNBLOCK_ADDRESS payload:
     * byte (adress length)
     * byte[] (address)
     */
    public static final byte PACKET_UNBLOCK_ADDRESS = 0x10;

    /*
     * REPORT_PING payload:
	 * int32 (internal session ID)
	 * int32 (measured latency in MS)
	 */
    public static final byte PACKET_REPORT_PING = 0x11;

   	/*
	 * RAW_FILTER payload:
	 * byte[] (pattern)
	 */
	public static final byte PACKET_RAW_FILTER = 0x12;

	/*
	 * UNLIMIT_ADDRESS payload:
	 * byte (address length)
	 * byte[] (address)
	 */
	public static final byte PACKET_UNLIMIT_ADDRESS = 0x13;

    /*
     * No payload
     *
     * Sends the disconnect message, removes sessions correctly, closes sockets.
     */
    public static final byte PACKET_SHUTDOWN = 0x7e;

    /*
     * No payload
     *
     * Leaves everything as-is and halts, other Threads can be in a post-crash condition.
     */
    public static final byte PACKET_EMERGENCY_SHUTDOWN = 0x7f;

}