package org.data;

public class DatabaseHandler {

    private static Database instance;

    private DatabaseHandler() {}

    public static Database getDatabase() {
        synchronized (DatabaseHandler.class) {
            if (instance != null)
                instance = new Database();
        }

        return instance;
    }
}
