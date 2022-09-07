package com.solvd.fooddelivery.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private final ArrayBlockingQueue<Connection> connections;

    private ConnectionPool(int poolSize) {
        connections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = new Connection();
            connections.add(connection);
        }
    }

    public static ConnectionPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(poolSize);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.error("Failed to extract connection.");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Failed to put back connection.");
        }
    }

    public ArrayBlockingQueue<Connection> getConnections() {
        return connections;
    }
}

