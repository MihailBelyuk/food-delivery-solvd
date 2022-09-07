package com.solvd.fooddelivery.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(ThreadMain.class);

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance(5);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                Connection connection = CONNECTION_POOL.getConnection();
                connection.create();
                CONNECTION_POOL.releaseConnection(connection);
                LOGGER.info("Released connection");
            }).start();
        }

//        CompletableFuture<String> firstCf = CompletableFuture.supplyAsync(() -> new Connection().read(), EXECUTOR)
//                .thenApply(str -> str + " vasia");
//        CompletableFuture<String> secondCf = CompletableFuture.supplyAsync(() -> new Connection().read(), EXECUTOR)
//                .thenApplyAsync(str -> new Connection().replace(str))
//                .thenApplyAsync(str -> str + " back to vasia", EXECUTOR).thenApplyAsync(str -> str + " - the coolest guy");
//        CompletableFuture<Integer> thirdCf = CompletableFuture.supplyAsync(() -> 6, EXECUTOR)
//                .thenApplyAsync(num -> num + 1, EXECUTOR);
//        CompletableFuture<Void> futures = CompletableFuture.allOf(thirdCf, firstCf);
//        futures.join();
//        String first = firstCf.join();
//        String second = secondCf.join();
//        Integer third = thirdCf.join();
//        LOGGER.info(first);
//        LOGGER.info(second);
//        LOGGER.info(third);
//
//        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(Connection::new, EXECUTOR);
//        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(Connection::new, EXECUTOR);
//        CompletableFuture<Void> cf3 = CompletableFuture.runAsync(Connection::new, EXECUTOR);
//        CompletableFuture<Void> cf4 = CompletableFuture.runAsync(Connection::new, EXECUTOR);
//        CompletableFuture<Void> cf5 = CompletableFuture.runAsync(Connection::new, EXECUTOR);
//        CompletableFuture<Void> cf6 = CompletableFuture.runAsync(Connection::new, EXECUTOR);
//        CompletableFuture<Void> cf7 = CompletableFuture.runAsync(Connection::new, EXECUTOR);
    }
}


