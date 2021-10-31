package com.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO
 * TCP 网络通信 -- 服务端
 * @author YUDI
 */
public class Server {

    public static void main(String[] args) throws IOException {
        //1. 创建一个服务端，指定绑定的端口
        ServerSocket ss = new ServerSocket(8888);
        Socket socket = null;
        //记录客户端连接数量
        int count = 0;
        while(true){
            //2. 循环监听客户端连接
            socket = ss.accept();
            //3. 创建线程处理客户请求
            ServerThread st = new ServerThread(socket);
            st.start();

            count++;
            System.out.println("客户端连接数量："+count);
        }
    }

}
