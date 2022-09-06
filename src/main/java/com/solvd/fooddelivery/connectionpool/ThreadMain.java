package com.solvd.fooddelivery.connectionpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Thread thread = new Thread(connectionPool.getConnection());
        thread.start();
        Thread thread1 = new Thread(connectionPool.getConnection());
        thread1.start();
        Thread thread2 = new Thread(connectionPool.getConnection());
        thread2.start();
        Thread thread3 = new Thread(connectionPool.getConnection());
        thread3.start();
        Thread thread4 = new Thread(connectionPool.getConnection());
        thread4.start();
        Thread thread5 = new Thread(connectionPool.getConnection());
        thread5.start();
        Thread thread6 = new Thread(connectionPool.getConnection());
        thread6.start();
        Thread thread7 = new Thread(connectionPool.getConnection());
        thread7.start();

        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());
        EXECUTOR.execute(new Connection());

        CompletableFuture<String> firstCf = CompletableFuture.supplyAsync(() -> new Connection().read(), EXECUTOR)
                .thenApply(str -> str + " vasia");
        System.out.println(firstCf.join());

        CompletableFuture<String> replaceCf = CompletableFuture.supplyAsync(() -> new Connection().read(), EXECUTOR)
                .thenApplyAsync(str -> new Connection().replace(str), EXECUTOR);
        System.out.println(replaceCf.join());

        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> new Connection().read(), EXECUTOR);
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> new Connection().read(), EXECUTOR);
        CompletableFuture.runAsync(() -> new Connection().delete(), EXECUTOR);
        CompletableFuture.runAsync(() -> new Connection().update(), EXECUTOR);
        CompletableFuture.runAsync(() -> new Connection().create(), EXECUTOR);
        CompletableFuture.runAsync(() -> new Connection().delete(), EXECUTOR);

        CompletableFuture<Void> cfs = CompletableFuture.allOf(cf1, cf2).thenRunAsync(() -> System.out.println("info of two objects read"));
        cfs.join();
    }
}
