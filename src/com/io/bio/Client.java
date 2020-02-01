package com.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * BIO
 * TCP 网络通信 -- 客户端
 * @author YUDI
 */
public class Client {

    public static void main(String[] args) throws IOException {
        //1. 创建客户端Socket，并指定服务器地址和端口
        Socket socket = new Socket("localhost",8888);
        //2. 获取输出流，向服务端传输信息
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("我想学习网络通信基础知识！");
        pw.flush();
        socket.shutdownOutput();

        //3. 获取输入流，获取服务端响应信息
        InputStream is = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String info = null;
        while((info = reader.readLine()) != null){
            System.out.println("服务端响应信息："+info);
        }
        socket.shutdownInput();

        //4. 关闭资源
        reader.close();
        is.close();
        pw.close();
        os.close();
        socket.close();

    }

}
