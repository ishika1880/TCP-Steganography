package org.example;
import org.example.Server.TCPServer;
import org.example.client.TCPClient;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Step 1: Start the TCP Client and Server for communication
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Start TCP Server to listen for incoming packets
                    TCPServer.main(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Start TCP Client to send data (including hidden data)
                    TCPClient.main(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        serverThread.start();
        clientThread.start();

        // Wait for threads to finish
        try {
            clientThread.join();
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
