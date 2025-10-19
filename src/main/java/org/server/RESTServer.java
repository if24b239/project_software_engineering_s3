package org.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.server.handlers.LoginHandler;
import org.server.handlers.RegistrationHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class RESTServer {
    private final HttpServer server;
    private static final int PORT = 8080;

    public RESTServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.setExecutor(Executors.newCachedThreadPool());
        setupRoutes();
    }

    private void setupRoutes() {
        server.createContext("/api/register", new RegistrationHandler());
        server.createContext("/api/login", new LoginHandler());
        // Add more routes as needed
    }

    public void start() {
        server.start();
        System.out.println("Server started on port " + PORT);
    }

    public void stop() {
        server.stop(0);
    }
}
