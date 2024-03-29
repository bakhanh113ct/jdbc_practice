package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ConcurrentHandler implements HttpHandler{

//    private final SocketChannel clientSocketChannel;


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Xử lý yêu cầu HTTP ở đây
        String response = "<h1>Hello</h1>";

        try {
            exchange.sendResponseHeaders(200, response.length());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream os = exchange.getResponseBody();

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        Future<String> future = executorService.submit(() -> "<h1>Hello</h1>");
        try {
            Thread.sleep(4000);
            System.out.println("The Thread name is " + Thread.currentThread().getName());
//            System.out.println(result);
        } catch (InterruptedException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
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

        try {
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    @Override
//    public void run() {
//
//        try {
//            handleClientRequest(clientSocketChannel);
//            Thread.sleep(4000);
//            System.out.println("The Thread name is " + Thread.currentThread().getName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void handleClientRequest(SocketChannel clientSocketChannel) throws IOException {
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//        clientSocketChannel.read(buffer);
//        buffer.flip();
//        byte[] bytes = new byte[buffer.remaining()];
//        buffer.get(bytes);
//        String request = new String(bytes);
//
////            System.out.println("Client request: " + request);
//
//        String response = "HTTP/1.1 200 OK\r\nContent-Length: 12\r\n\r\nHello World!";
//        clientSocketChannel.write(ByteBuffer.wrap(response.getBytes()));
//
//        clientSocketChannel.close();
//    }
}
