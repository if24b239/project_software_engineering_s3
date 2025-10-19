package org.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import org.data.Database;
import org.data.DatabaseHandler;
import org.data.User;
import org.json.JSONObject;
import org.server.auth.TokenService;
import java.io.IOException;

public class LoginHandler extends BaseHandler {

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

            if (!jsonRequest.has("username") || !jsonRequest.has("password")) {
                sendResponse(exchange, 400, "{\"error\": \"Missing required fields\"}");
                return;
            }

            String username = jsonRequest.getString("username");
            String password = jsonRequest.getString("password");

            Database db = DatabaseHandler.getDatabase();
            User user = db.getUserByUsername(username);

            if (user == null || !user.getPassword().equals(password)) {
                sendResponse(exchange, 401, "{\"error\": \"Invalid credentials\"}");
                return;
            }

            String token = TokenService.generateToken(username);
            db.storeToken(username, token);

            JSONObject response = new JSONObject();
            response.put("token", token);
            response.put("username", username);

            sendResponse(exchange, 200, response.toString());

        } catch (Exception e) {
            sendResponse(exchange, 500, "{\"error\": \"Internal server error\"}");
        }
    }
}