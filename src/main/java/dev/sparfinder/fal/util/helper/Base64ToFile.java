package dev.sparfinder.fal.util.helper;

import java.util.Base64;

public class Base64ToFile {

    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
