package com.io.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * UDP 网络通信 -- 服务端
 * @author YUDI
 */
public class Server {

    public static void main(String[] args) throws IOException {
        //1. 创建服务端，指定端口
        DatagramSocket server = new DatagramSocket(8800);
        //2. 创建数据报
        byte[] data = new byte[20*1024];
        DatagramPacket packet = new DatagramPacket(data,data.length);
        //3. 接收客户端数据（该方法在接收到数据报前会一直阻塞）
        server.receive(packet);
        //4. 读取数据
        String info = new String(data,0,packet.getLength());
        System.out.println("客户端传递信息："+info);

        //5. 响应客户端
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] resData = "请观看代码，仔细体会和理解！".getBytes();
        DatagramPacket resPacket = new DatagramPacket(resData,0,address,port);
        server.send(resPacket);
        server.close();

    }

}
