package com.td.admin.util;

import org.web3j.crypto.LinuxSecureRandom;

import java.security.SecureRandom;

public class MySecureRandomUtils {
    private static final SecureRandom SECURE_RANDOM;
    private static int isAndroid;

    public static SecureRandom secureRandom() {
        return SECURE_RANDOM;
    }

    static boolean isAndroidRuntime() {
        if (isAndroid == -1) {
            String runtime = System.getProperty("java.runtime.name");
            isAndroid = runtime != null && runtime.equals("Android Runtime") ? 1 : 0;
        }

        return isAndroid == 1;
    }

    private MySecureRandomUtils() {
    }

    static {
        if (isAndroidRuntime()) {
            new LinuxSecureRandom();
        }

        SECURE_RANDOM = new SecureRandom();
        isAndroid = -1;
    }
}
