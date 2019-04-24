package com.accumulate.business.utils;

/**
 * UUID类
 *
 * @author lilg
 */
public class UUID {

    public static String getId() {
        return java.util.UUID.randomUUID().toString();
    }

    public static String getId(String str) {
        return java.util.UUID.nameUUIDFromBytes(str.getBytes()).toString();
    }
}
