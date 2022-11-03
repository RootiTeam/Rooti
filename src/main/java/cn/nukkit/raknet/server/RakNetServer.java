package cn.nukkit.raknet.server;

import cn.nukkit.Server;
import cn.nukkit.utils.MainLogger;
import cn.nukkit.raknet.generic.Socket;
import cn.nukkit.raknet.utils.InternetAddress;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
@Log4j2
public class RakNetServer extends Thread {
    protected InternetAddress bindAddress;

    protected ConcurrentLinkedQueue<byte[]> externalQueue;
    protected ConcurrentLinkedQueue<byte[]> internalQueue;

    protected boolean shutdown;

    public RakNetServer(InternetAddress bindAddress) {
        this.bindAddress = bindAddress;

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

    public InternetAddress getAddress() {
        return bindAddress;
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
        Socket socket = new Socket(this.getLogger(), this.bindAddress);
        try {
            new SessionManager(this, socket);
        } catch (Exception e) {
            Server.getInstance().getLogger().logException(e);
        }
    }
}
