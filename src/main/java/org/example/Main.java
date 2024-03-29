package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.handlers.ClientGetUserHandler;
import org.example.handlers.ClientSetUserHandler;
import org.example.utils.DatabaseCon;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

public class Main {
    private static final int PORT_GET = 8080;
    private static final int PORT_SET = 8081;
    private static final int THREAD_POOL_SIZE = 2;
    public static DatabaseCon databaseCon = new DatabaseCon();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        System.out.println("Hello world!");
//        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
//        server.createContext("/", new RootHandler());
//
//        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 4000,
//                TimeUnit.MILLISECONDS, workQueue, handler);
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        server.createContext("/concurrentHandler", new ConcurrentHandler());
//        server.setExecutor(executor);
//        server.start();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        ServerSocketChannel serverSocketChannelGet = ServerSocketChannel.open();
        serverSocketChannelGet.socket().bind(new InetSocketAddress(PORT_GET));
        serverSocketChannelGet.configureBlocking(false);

        ServerSocketChannel serverSocketChannelSet = ServerSocketChannel.open();
        serverSocketChannelSet.socket().bind(new InetSocketAddress(PORT_SET));
        serverSocketChannelSet.configureBlocking(false);

        System.out.println("Server is running on PORT: " + PORT_GET + " and PORT: " + PORT_SET);

        while (true) {
            SocketChannel clientSocketChannelGet = serverSocketChannelGet.accept();
            SocketChannel clientSocketChannelSet = serverSocketChannelSet.accept();
//            System.out.println("jhi");

            if (clientSocketChannelGet != null) {
                executorService.execute(new ClientGetUserHandler(clientSocketChannelGet));
            }
            if (clientSocketChannelSet != null) {
                executorService.execute(new ClientSetUserHandler(clientSocketChannelSet));
            }
        }
    }

//    private static class ClientGetUserHandler implements Runnable {
//        private final SocketChannel clientSocketChannel;
//
//        public ClientGetUserHandler(SocketChannel clientSocketChannel) {
//            this.clientSocketChannel = clientSocketChannel;
//        }
//
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(4000);
//                handleClientRequest(clientSocketChannel);
//                System.out.println("The Thread name is " + Thread.currentThread().getName());
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        private void handleClientRequest(SocketChannel clientSocketChannel) throws IOException {
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//            clientSocketChannel.read(buffer);
//            buffer.flip();
//            byte[] bytes = new byte[buffer.remaining()];
//            buffer.get(bytes);
//            String request = new String(bytes);
//
////            System.out.println("Client request: " + request);
//
//            String response = "HTTP/1.1 200 OK\r\nContent-Length: 12\r\n\r\nHello World!";
//            clientSocketChannel.write(ByteBuffer.wrap(response.getBytes()));
//
//            clientSocketChannel.close();
//        }
//    }

}