package com.learn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NioClient {

    public void start() throws IOException {

        //连接服务端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8880));

        //接收服务端响应数据
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        //向服务端发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String request = scanner.next();
            if(request != null && request.length() >0){
                socketChannel.write(StandardCharsets.UTF_8.encode(request));
            }
            System.out.println("客户端输入内容:"+scanner.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        NioClient c = new NioClient();
        c.start();
    }

}
