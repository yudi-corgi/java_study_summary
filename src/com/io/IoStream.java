package com.io;

import java.io.*;

/**
 * 输入/输出字节流
 * @author YUDI
 */
public class IoStream {

    public void printHex(String fileName) throws IOException {

        FileInputStream fis = new FileInputStream(fileName);
        byte[] b = new byte[20 * 1024];
        int bytes ;
        while((bytes = fis.read(b,0,b.length)) != -1){
            System.out.println("当前指针位数:"+ bytes);
            System.out.println("字节数组长度："+ b.length);
            for (int i = 0; i < bytes; i++) {
                System.out.print(Integer.toHexString(b[i]) + " ");
            }
        }
        fis.close();
    }

    public void copyFile(String fileName1,String fileName2) throws IOException {
        FileInputStream fis = new FileInputStream(fileName1);
        //第二个参数：true 表示文件存在时追加内容，false 则为重写
        FileOutputStream fos = new FileOutputStream(fileName2,false);
        byte[] sourceByte = new byte[1024];
        int bytes ;
        while( (bytes = fis.read(sourceByte,0,sourceByte.length)) != -1){
            fos.write(sourceByte,0,bytes);
        }
        fis.close();
        fos.close();
    }

    public void bufferedCopyFile(String fileName1,String fileName2) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName1));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName2));
        int i;
        while((i=bis.read()) != -1){
            bos.write(i);
            bos.flush();
        }
        bis.close();
        bos.close();
    }

    public static void main(String[] args) throws IOException {
        IoStream ios = new IoStream();
        ios.printHex("D:/Config.ini");
        ios.copyFile("D:/Config.ini","D:/Config2.ini");
        ios.bufferedCopyFile("D:/Config.ini","D:/Config2.ini");

    }

}
