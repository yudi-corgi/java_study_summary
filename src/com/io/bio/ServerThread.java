package com.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * BIO
 * 多线程网络通信处理类
 * @author YUDI
 */
public class ServerThread extends Thread {

    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = br.readLine()) != null){
                System.out.println("客户端传输信息：" + info);
            }
            socket.shutdownInput();

            // 获取输出流，响应客户端
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("观看代码，仔细体会和理解！");
            pw.flush();
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭资源
            try {
                if(pw != null){
                    pw.close();
                }
                if(os != null){
                    os.close();
                }
                if(br != null){
                    br.close();
                }
                if(is != null){
                    is.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
