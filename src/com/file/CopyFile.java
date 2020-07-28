package com.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 文件复制并改写内容
 * @author YUDI
 * @date 2020/7/20 22:46
 */
public class CopyFile {

    public static void main(String[] args) throws IOException {

        /**
         * 综合来看，数据很小时 FileChannel 较快，
         * 数据越大则是 BufferReader/BufferWriter 快
         */
        String source = "D:\\HELLO.txt";
        String dest = "D:\\WORLD.txt";
        // FileChannel
        long start = System.currentTimeMillis();
        long end;
        CopyFile.copyAndModify(source,dest);
        end = System.currentTimeMillis();
        System.out.println("FileChannel时间长度："+(end-start));
        // BufferReader/BufferWriter
        /*start = System.currentTimeMillis();
        CopyFile.copy(source,dest);
        end = System.currentTimeMillis();
        System.out.println("时间长度："+(end-start));*/
    }

    /**
     * 使用 FileChannel 读写文件，并将 HELLO 改为 WORLD
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copy(String source,String dest) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        FileInputStream fis = new FileInputStream(new File(source));
        FileOutputStream fos = new FileOutputStream(new File(dest));
        FileChannel input = fis.getChannel();
        FileChannel output = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        String str = null;
        while(input.read(byteBuffer) != -1){
            byteBuffer.flip();
            str = charset.decode(byteBuffer).toString();
            if(str.contains("HELLO")){
                str = str.replace("HELLO","WORLD");
            }
            byteBuffer = charset.encode(str);
            output.write(byteBuffer);
            byteBuffer.clear();
        }
        // output.transferFrom(input,0,input.size());
    }

    /**
     * 使用 BufferedReader/BufferedWriter 读写文件，并将 HELLO 改为 WORLD
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyAndModify(String source,String dest) throws IOException {

        File sFile = new File(source);
        File dFile = new File(dest);
        BufferedReader reader = new BufferedReader(new FileReader(sFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(dFile));
        String str = null;
        while((str = reader.readLine()) != null){
            if(str.contains("HELLO")){
                str = str.replace("HELLO","WORLD");
            }
            writer.write(str);
        }
        writer.flush();
    }

}
