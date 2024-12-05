package org.example.client;

import org.example.Server.Receiver;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import static org.example.Utilities.Utility.bytesToHex;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        String serverIP = "127.0.0.1";
        int port = 8080;
        String secretMessage = "it's here";

        try (Socket socket = new Socket(serverIP, port)) {
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            // Example initial sequence
            int initialSequence = 1000;

            // Encode the data
            byte[] encodedPacket = Sender.encodeData(secretMessage, initialSequence);

            // Send the encoded packet to the server
            out.write(encodedPacket);
            System.out.println("Data sent successfully!");
            System.out.println("Encoded packet (hex): " + bytesToHex(encodedPacket));


            // Receive the packet back from the server
            byte[] receivedPacket = new byte[1024];
            int bytesRead = in.read(receivedPacket);
            if (bytesRead > 0) {
                System.out.println("Received packet back from server.");

                // Now use the new method to decode the packet
                String hiddenMessage = Receiver.decodeData(receivedPacket);
                System.out.println("Hidden Message (from server's response): " + hiddenMessage);

            }
        }
    }
}
