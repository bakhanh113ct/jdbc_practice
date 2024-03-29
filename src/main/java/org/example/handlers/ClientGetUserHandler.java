package org.example.handlers;

import org.example.Main;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientGetUserHandler implements Runnable {
    private final SocketChannel clientSocketChannel;

    public ClientGetUserHandler(SocketChannel clientSocketChannel) {
        this.clientSocketChannel = clientSocketChannel;
    }

    @Override
    public void run() {
        try {
//            Thread.sleep(4000);
            handleClientRequest(clientSocketChannel);
//            System.out.println("The Thread name is " + Thread.currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void handleClientRequest(SocketChannel clientSocketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        clientSocketChannel.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        //
        String username = "john";
        String password = "secret";
        String query = "select * from tbluser where username=? and password=?";
        String rs = Main.databaseCon.getUser(query, username, password);
        System.out.println(rs);


        String response = "HTTP/1.1 200 OK\r\nContent-Length: 12\r\n\r\nHello world!" ;
        clientSocketChannel.write(ByteBuffer.wrap(response.getBytes()));

        clientSocketChannel.close();
    }
}
