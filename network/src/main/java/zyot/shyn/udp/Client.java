package zyot.shyn.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws Exception {
        int server_port = 0;
        String server_ip = "";

        if (args.length != 2) {
            System.out.println("Usage: udpclient [server address] [server port]");
            System.exit(0);
        }
        try {
            server_port = Integer.parseInt(args[1]);
            server_ip = args[0];
        } catch (Exception e) {
            System.out.println("Usage: udpclient [server address] [server port]");
            System.exit(0);
        }

        System.out.print("Enter a sentence to send to server: ");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(server_ip);
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, server_port);
        System.out.println("Sending data to server...");
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("Receiving data from server...");
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("Data received from server:" + modifiedSentence);
        clientSocket.close();
    }
}
