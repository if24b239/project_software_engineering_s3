package org.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            RESTServer server = new RESTServer();
            server.start();
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}
