package com.io.udp;

import java.io.IOException;
import java.net.*;

/**
 * UDP 网络通信 -- 客户端
 * @author YUDI
 */
public class client {

    public static void main(String[] args) throws IOException {
        //1. 创建客户段发送的数据报
        InetAddress address = InetAddress.getByName("localhost");
        byte[] data = "我想学习UDP网络通信基础知识！".getBytes();
        DatagramPacket packet = new DatagramPacket(data,data.length,address,8800);
        //2. 客户端发送信息
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        //3. 接收服务端响应信息
        byte[] resData = new byte[20*1024];
        DatagramPacket resPacket = new DatagramPacket(resData,resData.length);
        socket.receive(resPacket);
        //4. 读取数据
        String info = new String(resData,0,resPacket.getLength());
        System.out.println("服务端响应信息："+info);
        socket.close();
    }
}
