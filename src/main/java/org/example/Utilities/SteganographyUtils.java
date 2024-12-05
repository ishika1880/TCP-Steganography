package org.example.Utilities;
import java.nio.ByteBuffer;

public class SteganographyUtils {
    public static byte[] hideDataInSequence(int originalSequence, String secret) {
        String binarySecret = Integer.toBinaryString(secret.hashCode());
        int secretBits = Integer.parseInt(binarySecret.substring(0, Math.min(16, binarySecret.length())), 2);
        int modifiedSequence = originalSequence ^ secretBits;

        return ByteBuffer.allocate(4).putInt(modifiedSequence).array();
    }

    public static String extractDataFromSequence(int modifiedSequence, int originalSequence) {
        int embeddedBits = modifiedSequence ^ originalSequence;
        return new String(ByteBuffer.allocate(4).putInt(embeddedBits).array());
    }
}
