package org.example.Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening...");

            Socket clientSocket = serverSocket.accept();
            InputStream in = clientSocket.getInputStream();

            //sending to myself
            OutputStream out = clientSocket.getOutputStream();

            // Read the sequence number (simulate packet)
            byte[] receivedPacket = new byte[1024];
            int bytesRead = in.read(receivedPacket);

            if (bytesRead > 0) {
                int originalSequence = 1000; // Example initial sequence
                String hiddenMessage = Receiver.decodeData(
                        ByteBuffer.wrap(receivedPacket).getInt(), originalSequence
                );
                System.out.println("Hidden Message: " + hiddenMessage);
                // Send the received packet back (echo the data)
                out.write(receivedPacket);
                System.out.println("Packet sent back to the client.");
            }
        }
    }
}
