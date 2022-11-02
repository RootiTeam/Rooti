package cn.nukkit.raknet.server;

import cn.nukkit.Server;
import cn.nukkit.utils.MainLogger;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
@Log4j2
public class RakNetServer extends Thread {
    protected final int port;
    protected String interfaz;

    protected ConcurrentLinkedQueue<byte[]> externalQueue;
    protected ConcurrentLinkedQueue<byte[]> internalQueue;

    protected boolean shutdown;


    public RakNetServer(int port) {
        this(port, "0.0.0.0");
    }

    public RakNetServer(int port, String interfaz) {
        this.port = port;
        if (port < 1 || port > 65536) {
            throw new IllegalArgumentException("Invalid port range");
        }

        this.interfaz = interfaz;

        this.externalQueue = new ConcurrentLinkedQueue<>();
        this.internalQueue = new ConcurrentLinkedQueue<>();

        this.start();
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void shutdown() {
        this.shutdown = true;
    }

    public int getPort() {
        return port;
    }

    public String getInterface() {
        return interfaz;
    }

    public MainLogger getLogger() {
        return MainLogger.getLogger();
    }

    public ConcurrentLinkedQueue<byte[]> getExternalQueue() {
        return externalQueue;
    }

    public ConcurrentLinkedQueue<byte[]> getInternalQueue() {
        return internalQueue;
    }

    public void pushMainToThreadPacket(byte[] data) {
        this.internalQueue.add(data);
    }

    public byte[] readMainToThreadPacket() {
        return this.internalQueue.poll();
    }

    public void pushThreadToMainPacket(byte[] data) {
        this.externalQueue.add(data);
    }

    public byte[] readThreadToMainPacket() {
        return this.externalQueue.poll();
    }

    private class ShutdownHandler extends Thread {
        public void run() {
            if (!shutdown) {
                log.fatal("RakNet crashed!");
            }
        }
    }

    @Override
    public void run() {
        this.setName("RakNet Thread #" + Thread.currentThread().getId());
        Runtime.getRuntime().addShutdownHook(new ShutdownHandler());
        UDPServerSocket socket = new UDPServerSocket(this.getLogger(), port, this.interfaz);
        try {
            new SessionManager(this, socket);
        } catch (Exception e) {
            Server.getInstance().getLogger().logException(e);
        }
    }
}
