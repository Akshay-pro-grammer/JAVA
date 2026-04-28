package com.peerlink;

import com.peerlink.networking.Client;
import com.peerlink.networking.Server;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String myId = UUID.randomUUID().toString();

        System.out.println("1. Start Server");
        System.out.println("2. Connect to Server");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        if (choice == 1) {
            // SERVER
            Server server = new Server(9000);
            server.acceptConnection();

            // HELLO exchange
            server.sendMessage("HELLO " + myId);
            String msg = server.readMessage();

            System.out.println("Received: " + msg);

            server.close();

        } else if (choice == 2) {
            // CLIENT
            System.out.print("Enter server IP: ");
            String ip = sc.nextLine();

            Client client = new Client(ip, 9000);

            // HELLO exchange
            String msg = client.readMessage();
            System.out.println("Received: " + msg);

            client.sendMessage("HELLO " + myId);

            client.close();
        }

        sc.close();
    }
}
