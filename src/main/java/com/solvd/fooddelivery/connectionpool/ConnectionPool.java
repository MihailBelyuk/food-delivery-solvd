package com.solvd.fooddelivery.connectionpool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final Integer POOL_SIZE = 5;
    private static ConnectionPool instance;
    private final List<Connection> connections;

    private ConnectionPool() {
        connections = new ArrayList<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = new Connection();
            connections.add(connection);
        }
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (connections.size() == 1) {
            pause();
            System.out.println("Releasing connection");
            releaseConnection(connections.get(0));
        }
        return connections.remove(connections.size() - 1);
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    private static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

