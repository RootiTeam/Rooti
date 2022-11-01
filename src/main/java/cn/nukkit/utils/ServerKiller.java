package cn.nukkit.utils;

import lombok.extern.log4j.Log4j2;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
@Log4j2
public class ServerKiller extends Thread {

    public int time;

    public ServerKiller(int time) {
        this.time = time;
        this.setName("Server Killer");
    }

    @Override
    public void run() {
        try {
            sleep(this.time * 1000L);
        } catch (InterruptedException e) {
            log.throwing(e);
        }
        System.out.println("\nTook too long to stop, server was killed forcefully!\n");
        System.exit(1);
    }

}
