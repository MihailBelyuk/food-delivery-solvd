package com.solvd.fooddelivery.connectionpool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final Integer POOL_SIZE = 5;
    private static ConnectionPool instance;
    private final List<Connection> pool;
    private final List<Connection> usedConnections;

    private ConnectionPool() {
        pool = new ArrayList<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = new Connection();
            pool.add(connection);
        }
        usedConnections = new ArrayList<>(POOL_SIZE);
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = pool.remove(pool.size() - 1);
        usedConnections.add(connection);
        if (pool.size() == 0) {
            while (pool.size() != POOL_SIZE-1) {
                releaseConnection(usedConnections.get(0));
            }
            pause();
            System.out.println("sleeping");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        pool.add(connection);
    }

    private static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

