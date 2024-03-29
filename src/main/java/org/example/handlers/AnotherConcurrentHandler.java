package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class AnotherConcurrentHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>Hello another</h1>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        Future<String> future = executorService.submit(() -> "<h1>Hello another</h1>");
//        try {
//            Thread.sleep(2000);
//            System.out.println("The Thread name is " + Thread.currentThread().getName());
////            System.out.println(result);
//        } catch (InterruptedException e) {
//            System.out.println(e);
//            throw new RuntimeException(e);
//        }
//// some operations
//        try {
//            String result = future.get();
//            System.out.println(result);
//            os.write(result.getBytes());
//            os.close();
//        } catch (InterruptedException e) {
//            System.out.println(e);
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            System.out.println(e);
//            throw new RuntimeException(e);
//        }

        os.write(response.getBytes());
        os.close();
    }
}
