package org.example.client;

import org.example.Server.Receiver;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        String serverIP = "127.0.0.1";
        int port = 8080;
        String secretMessage = "Hidden Data";

        try (Socket socket = new Socket(serverIP, port)) {
            OutputStream out = socket.getOutputStream();
            //self
            InputStream in = socket.getInputStream();
            int initialSequence = 1000; // Example initial sequence
            byte[] encodedPacket = Sender.encodeData(secretMessage, initialSequence);

            // Send the encoded packet
            out.write(encodedPacket);
            System.out.println("Data sent successfully!");

            // Receive the packet back from the server
            byte[] receivedPacket = new byte[1024];
            int bytesRead = in.read(receivedPacket);
            if (bytesRead > 0) {
                System.out.println("Received packet back from server.");

                // Optionally, decode the received packet if needed
                int modifiedSequence = ByteBuffer.wrap(receivedPacket).getInt();
                String hiddenMessage = Receiver.decodeData(modifiedSequence, initialSequence);
                System.out.println("Hidden Message (from server's response): " + hiddenMessage);

            }
        }
    }
}