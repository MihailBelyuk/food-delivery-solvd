package com.solvd.fooddelivery.connectionpool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static int maxConnections;
    private static ConnectionPool instance;
    private final List<Connection> connections;

    private ConnectionPool() {
        connections = new ArrayList<>(maxConnections);
        for (int i = 0; i < maxConnections; i++) {
            Connection connection = new Connection();
            connection.start();
            connections.add(connection);
        }
    }

    public static ConnectionPool getInstance(int poolSize) {
        maxConnections = poolSize;
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = connections.remove(connections.size() - 1);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    public List<Connection> getConnections() {
        return connections;
    }
}

