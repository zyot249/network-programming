package com.zyot.shyn.server;

import java.util.HashMap;

public class ConnectionHandler {
    public static final int MAX_SIZE = 2;

    public static HashMap<Integer, Connection> connections = new HashMap<>();
}
