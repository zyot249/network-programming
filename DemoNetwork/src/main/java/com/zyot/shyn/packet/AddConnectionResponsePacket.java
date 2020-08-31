package com.zyot.shyn.packet;

import java.io.Serializable;

public class AddConnectionResponsePacket implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public boolean isConnectSuccess;
    public String message;

    public AddConnectionResponsePacket() {

    }

    public AddConnectionResponsePacket(int id, boolean isConnectSuccess, String message) {
        this.id = id;
        this.isConnectSuccess = isConnectSuccess;
        this.message = message;
    }
}
