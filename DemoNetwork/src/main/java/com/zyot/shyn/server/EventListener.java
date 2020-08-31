package com.zyot.shyn.server;

import com.zyot.shyn.packet.AddConnectionPacket;
import com.zyot.shyn.packet.AddConnectionResponsePacket;
import com.zyot.shyn.packet.RemoveConnectionPacket;

import java.util.Map;

public class EventListener {
    public void received(Object p, Connection connection) {
        if (p instanceof AddConnectionPacket) {
            if (ConnectionHandler.connections.size() <= ConnectionHandler.MAX_SIZE) {
                AddConnectionPacket addConnectionPacket = (AddConnectionPacket) p;
                addConnectionPacket.id = connection.id;
                for(Map.Entry<Integer, Connection> entry : ConnectionHandler.connections.entrySet()) {
                    Connection c = entry.getValue();
                    if (c != connection) {
                        c.sendObject(addConnectionPacket);
                    } else {
                        AddConnectionResponsePacket addConnectionResponsePacket = new AddConnectionResponsePacket(
                                connection.id,
                                true,
                                "Connect successfully to server!");

                        c.sendObject(addConnectionResponsePacket);
                    }
                }
                System.out.println("Client " + connection.id + " is connected!");
            } else {
                AddConnectionResponsePacket addConnectionResponsePacket = new AddConnectionResponsePacket(
                        -1,
                        false,
                        "Room is full!");

                connection.sendObject(addConnectionResponsePacket);
                connection.close();
            }

        } else if (p instanceof RemoveConnectionPacket) {
            RemoveConnectionPacket packet = (RemoveConnectionPacket) p;
            System.out.println("Connection: " + packet.id + " has disconnected");
            ConnectionHandler.connections.get(packet.id).close();
            for(Map.Entry<Integer, Connection> entry : ConnectionHandler.connections.entrySet()) {
                Connection c = entry.getValue();
                RemoveConnectionPacket removeConnectionAtClientPacket = new RemoveConnectionPacket();
                removeConnectionAtClientPacket.id = packet.id;
                c.sendObject(removeConnectionAtClientPacket);
            }
        }
    }
}
