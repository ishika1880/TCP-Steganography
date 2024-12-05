package org.example.Server;

import java.nio.ByteBuffer;

public class Receiver {
    public static String decodeData(byte[] receivedPacket) {
        // Read the sequence and hidden message from the received packet
        ByteBuffer byteBuffer = ByteBuffer.wrap(receivedPacket);

        // Extract the initial sequence
        int initialSequence = byteBuffer.getInt();

        // The remaining bytes contain the hidden message
        byte[] messageBytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(messageBytes);

        // Convert byte array back to a string
        return new String(messageBytes);
    }
}

