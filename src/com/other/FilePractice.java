package com.other;

import java.io.File;

/**
 * @version 2019/9/11
 **/
public class FilePractice {


    public static void main(String[] args){
        String fileName = "F://profile/upload/2019/09/11/457b8e0ff47fc6008ff279b7d130b204.jpg";
        System.out.println(File.separator + fileName);
        FilePractice.getFileName(fileName);

        System.out.println(fileName);
    }


    public static File getFileName(String fileName){

        return new File(File.separator + fileName);
    }

}
