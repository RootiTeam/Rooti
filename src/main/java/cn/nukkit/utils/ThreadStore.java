package cn.nukkit.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class ThreadStore {
    public static final Map<String, Object> store = new ConcurrentHashMap<>();
}
