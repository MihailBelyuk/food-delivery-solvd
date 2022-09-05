package com.solvd.fooddelivery.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    public void create() {
        LOGGER.info("Object created " + Thread.currentThread().getName());
    }

    public String read() {
        LOGGER.info("Object info extracted from DB. " + Thread.currentThread().getName());
        return "some object";
    }

    public void update() {
        LOGGER.info("Object info updated in the DB. " + Thread.currentThread().getName());
    }

    public void delete() {
        LOGGER.info("Object deleted from the DB. " + Thread.currentThread().getName());
    }

    public String replace(String string) {
        String modified = string.replace(" ", "-");
        LOGGER.info("Object1 replaced in DB with Object2. " + Thread.currentThread().getName());
        return modified;
    }

    @Override
    public void run() {
        delete();read();create();update();
    }
}
