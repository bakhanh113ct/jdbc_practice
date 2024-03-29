package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler implements HttpHandler {

    @Override

    public void handle(HttpExchange he) throws IOException {
        String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + 8001 + "</h1>";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        System.out.println("The Thread name is " + Thread.currentThread().getName());
        os.write(response.getBytes());
        os.close();
    }
}