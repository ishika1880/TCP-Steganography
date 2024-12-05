package org.example.Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.example.Utilities.Utility.bytesToHex;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening...");

            // Accept client connections
            Socket clientSocket = serverSocket.accept();
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            // Read the received packet from the client
            byte[] receivedPacket = new byte[1024];
            int bytesRead = in.read(receivedPacket);

            if (bytesRead > 0) {
                // Decode the hidden message from the received packet
                String hiddenMessage = Receiver.decodeData(receivedPacket);
                System.out.println("Hidden Message: " + hiddenMessage);

                // Send the received packet back to the client (echo)
                out.write(receivedPacket);
                System.out.println("Packet sent back to the client.");
                System.out.println("Received packet (hex): " + bytesToHex(receivedPacket));

            }
        }
    }
}

