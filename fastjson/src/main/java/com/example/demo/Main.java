package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Main{

    public static void main(String[] args) throws Exception {
        InputStream in = Runtime.getRuntime().exec("dir").getInputStream();
        byte[] bcache = new byte[1024];
        int readSize = 0;   //每次读取的字节长度
        ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
        while ((readSize = in.read(bcache)) > 0) {
            infoStream.write(bcache, 0, readSize);
        }
        System.out.println(infoStream.toString());
    }
}