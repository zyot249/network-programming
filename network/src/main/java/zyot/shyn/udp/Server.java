package zyot.shyn.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws Exception {
        int udp_port = 0;

        if (args.length != 1) {
            System.out.println("Usage: udpserver [udp port]");
            System.exit(0);
        }
        try {
            udp_port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Usage: udpserver [udp port]");
            System.exit(0);
        }

        DatagramSocket serverSocket = new DatagramSocket(udp_port);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            System.out.println("UDP Server is waiting for client data at port: " + udp_port);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            System.out.println("  - Received data from client: " +
                    receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort());
            String sentence = new String(receivePacket.getData());
            System.out.println("  - Data: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            System.out.println("  - Send data to client: " + capitalizedSentence);
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            System.out.println("  - Finish working with client.\n\n");
        }
    }
}
