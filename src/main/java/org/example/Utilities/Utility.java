package org.example.Utilities;

public class Utility {

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b)); // Converts each byte to a 2-digit hexadecimal string
        }
        return hexString.toString();
    }
}

