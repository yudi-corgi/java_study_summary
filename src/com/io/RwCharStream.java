package com.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 输入/输出字符流
 * @author CDY
 */
public class RwCharStream {

    public static void rwTest(String fileName1,String fileName2) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName1), StandardCharsets.UTF_8);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName2),StandardCharsets.UTF_8);
        char[] charByte = new char[1024];
        int c ;
        while((c = isr.read(charByte,0,charByte.length)) != -1){
            System.out.println(c);
            System.out.println(new String(charByte,0,c));
            for (int i = 0; i < c; i++) {
                osw.write(charByte[i]);
                osw.flush();
            }
        }
        isr.close();
        osw.close();
    }

    public static void fileRw(String fileName1,String fileName2) throws IOException {
        FileReader reader = new FileReader(fileName1);
        FileWriter writer = new FileWriter(fileName2);

        BufferedReader br = new BufferedReader(reader);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter pw = new PrintWriter(writer);
    }


    public static void main(String[] args) throws IOException {

        RwCharStream.rwTest("D:/Config.ini","D:/Config2.ini");
    }
}
