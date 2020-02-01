package com.learn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 服务端
 */
public class NioServer {

    public void start() throws IOException {

        //1. 创建 Selector
        Selector selector = Selector.open();
        //2. 创建 channel 通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3. 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
        //4. 设置 channel 为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //5. 将通道注册到 selector 上，并监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动成功");
        //6. 循环等待新接入的连接
        while (true){
            //该方法会阻塞，直到监听的通道有就绪事件才会返回，返回的是就绪的Channel数量
            int readyChannel = selector.select();
            //此处是为了防止空轮询，但效果不友好
            if(readyChannel == 0){
                continue;
            }

            //获取已连接并且就绪的通道集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator iterator = selectionKeys.iterator();

            while(iterator.hasNext()){
                //获取 channel 实例
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                //移除Set中的当前SelectionKey，是因为每检测到事件就把selectionkey放到集合，所以取出来后就将其从set中删除
                iterator.remove();

                //7. 根据就绪状态，调用方法处理业务逻辑
                /**
                 * 如果是接入事件
                 */
                if(selectionKey.isAcceptable()){
                    acceptHandler(serverSocketChannel,selector);
                }
                /**
                 * 如果是读事件
                 */
                if(selectionKey.isReadable()){
                    readHandler(selectionKey,selector);
                }
            }
        }

    }

    //接入事件处理
    private void acceptHandler(ServerSocketChannel serverSocketChannel,Selector selector) throws IOException {
        //通过服务端通道创建通道与客户端进行通信
        SocketChannel socketChannel = serverSocketChannel.accept();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //注册到selector上，并监听可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);
        //服务端回复客户端消息
        socketChannel.write(Charset.forName("UTF-8").encode("安静潜水，不要冒泡"));

    }

    //读事件处理
    private void readHandler(SelectionKey selectionKey,Selector selector) throws IOException {
        //从selectionKey中获取就绪的通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //创建 buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //循环读取客户端请求信息
        String request = "";
        //将数据读到Buffer中，大于1说明有数据
        while(socketChannel.read(byteBuffer) > 0){
            //切换buffer 为读模式
            byteBuffer.flip();
            //读取数据
            request += Charset.forName("UTF-8").decode(byteBuffer);
        }
        //将channel再次注册到selector上，并监听事件（非必须操作，当监听事件没有改变时不需要写）
        socketChannel.register(selector,SelectionKey.OP_READ);
        //将客户端发送的请求信息，广播给其他客户端
        if(request.length() > 0){
            broadCast(selector,socketChannel,request);
        }
    }

    //将客户端A的消息广播给其他客户端
    private void broadCast(Selector selector,SocketChannel sourceChannel,String request){
        //获取所有已接入的通道，kes 是获取所有通道（包括已连接但没有就绪事件的通道）
        Set<SelectionKey> selectionKeys = selector.keys();
        //循环向其他通道广播消息
        selectionKeys.forEach(selectionKey -> {
            //获取目标通道
            Channel targetChannel = selectionKey.channel();
            //判断其是否为SocketChannel，是否为发送消息的SocketChannel，即剔除发消息的客户端
            if(targetChannel instanceof SocketChannel && targetChannel != sourceChannel){
                try {
                    //发送消息
                    ((SocketChannel) targetChannel).write(Charset.forName("UTF-8").encode(request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        NioServer n = new NioServer();
        n.start();
    }

}
