package com.zyot.shyn.client;

import com.zyot.shyn.packet.AddConnectionPacket;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Client client = new Client("localhost", 6666);
        client.connect();

        AddConnectionPacket addConnectionPacket = new AddConnectionPacket();
        client.sendObject(addConnectionPacket);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                client.close();
                break;
            }
        }
    }
}
