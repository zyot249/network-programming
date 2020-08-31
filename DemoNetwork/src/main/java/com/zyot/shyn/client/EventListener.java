package com.zyot.shyn.client;

import com.zyot.shyn.packet.AddConnectionPacket;
import com.zyot.shyn.packet.AddConnectionResponsePacket;
import com.zyot.shyn.packet.RemoveConnectionPacket;

public class EventListener {
    public void received(Object p, Client client) {
        if (p instanceof AddConnectionPacket) {
            AddConnectionPacket packet = (AddConnectionPacket)p;
            ConnectionHandler.connections.put(packet.id, new Connection(packet.id));
            System.out.println(packet.id + " has connected");
        } else if (p instanceof RemoveConnectionPacket) {
            RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
            System.out.println("Connection: " + packet.id + " has disconnected");
            ConnectionHandler.connections.remove(packet.id);
        } else if (p instanceof AddConnectionResponsePacket) {
            AddConnectionResponsePacket packet = (AddConnectionResponsePacket) p;
            System.out.println(packet.message);
            client.setId(packet.id);
            if (!packet.isConnectSuccess) {
                client.close();
            }
        }
    }
}
