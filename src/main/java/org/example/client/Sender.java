package org.example.client;

import java.nio.ByteBuffer;

public class Sender {
    public static byte[] encodeData(String secretMessage, int initialSequence) {
        byte[] messageBytes = secretMessage.getBytes(); // Convert message to byte array
        int messageLength = messageBytes.length;

        // Combine message length and the message itself into the sequence
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES + messageLength);
        buffer.putInt(initialSequence); // Put initial sequence
        buffer.put(messageBytes); // Put the message bytes

        return buffer.array(); // Return as byte array
    }
}

