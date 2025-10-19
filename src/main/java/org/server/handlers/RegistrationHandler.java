package org.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import org.data.Database;
import org.data.DatabaseHandler;
import org.data.User;
import org.json.JSONObject;
import java.io.IOException;

public class RegistrationHandler extends BaseHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        handleCORS(exchange);

        if (exchange.getRequestMethod().equals("OPTIONS")) {
            sendResponse(exchange, 200, "");
            return;
        }

        if (!exchange.getRequestMethod().equals("POST")) {
            sendResponse(exchange, 405, "{\"error\": \"Method not allowed\"}");
            return;
        }

        try {
            String requestBody = readRequestBody(exchange);
            JSONObject jsonRequest = new JSONObject(requestBody);

            // Validate input
            if (!jsonRequest.has("username") || !jsonRequest.has("password")) {
                sendResponse(exchange, 400, "{\"error\": \"Missing required fields\"}");
                return;
            }

            String username = jsonRequest.getString("username");
            String password = jsonRequest.getString("password");

            // Validate username and password
            if (username.length() < 3 || password.length() < 6) {
                sendResponse(exchange, 400, "{\"error\": \"Invalid username or password\"}");
                return;
            }

            Database db = DatabaseHandler.getDatabase();
            boolean success = db.createUser(new User(username, password));

            if (!success) {
                sendResponse(exchange, 409, "{\"error\": \"Username already exists\"}");
                return;
            }

            sendResponse(exchange, 201, "{\"message\": \"Registration successful\"}");

        } catch (Exception e) {
            sendResponse(exchange, 500, "{\"error\": \"Internal server error\"}");
        }
    }
}
