package org.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

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

            // Validate input
            if (!jsonRequest.has("username") || !jsonRequest.has("password")) {
                sendResponse(exchange, 400, "{\"error\": \"Missing required fields\"}");
                return;
            }

            //TODO: HERE STUFFS

            sendResponse(exchange, 200, "{\"token\": \"your-jwt-token-here\"}");

        } catch (Exception e) {
            sendResponse(exchange, 500, "{\"error\": \"Internal server error\"}");
        }
    }
}