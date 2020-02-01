package com.learn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端线程类，专门接收服务端响应信息
 */
public class NioClientHandler implements Runnable {

    private Selector selector;
    public NioClientHandler(Selector selector){
        this.selector =  selector;
    }

    @Override
    public void run(){
        try {
            while (true){
                int readyChannel = selector.select();
                if(readyChannel == 0){
                    continue;
                }
                //获取就绪的通道集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    //获取 channel 实例
                    SelectionKey selectionKey = (SelectionKey) iterator.next();
                    iterator.remove();
                    //读事件
                    if(selectionKey.isReadable()){
                        readHandler(selectionKey,selector);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //读事件处理
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        //从selectionKey中获取就绪的通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //创建 buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //循环读取服务端响应信息
        String response = "";
        //将数据读到Buffer中，大于1说明有数据
        while(socketChannel.read(byteBuffer) > 0){
            //切换buffer 为读模式
            byteBuffer.flip();
            //读取数据
            response += Charset.forName("UTF-8").decode(byteBuffer);
            //将channel再次注册到selector上，并监听事件（非必须操作，当监听事件没有改变时不需要写）
            socketChannel.register(selector,SelectionKey.OP_READ);
        }
        //将服务端信息打印到本地
        if(response.length() > 0){
            System.out.println(response);
        }
    }
}
