package com.yb.demo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yb
 * @description channel当作之前的stream来用。与buffer进行交互，buffer读入，channel.read;buffer写入，channel.write
 * @data 2020/5/20
 */
public class ChannelDemo {

    public static void main(String[] args) throws IOException {
//        write();
//        read();
//        copy();
//        copy2();
          copy3();
    }

    /**
     * 将字符串写入到本地文件中
     */
    public static void write() throws IOException {
        //定义一个需要写入的字符串
        String str = "hello world";
        //创建一个文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("hello.txt");
        //将字符串写入到文件中，不使用channel
        //fileOutputStream.write(str.getBytes());

        //使用channel：创建一个FileChannel()
        //OutputStream中内置了一个Channel
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将字符串先写入到缓冲区中（buffer）
        byteBuffer.put(str.getBytes());
        //把Buffer中的数据写入到Channel中，首先要对buffer进行反转（写->读）
        //比如：一开始写入数据后，position变成了100（即写入了100个字符），limit是1024(即限定的长度)
        //反转后，开始读数据，就将position变为0（即从第一个字符开始读），limit变为原先的position(即100)
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        //关闭流
        fileOutputStream.close();
    }

    /**
     * 将本地文件读入到内存中
     */
    public static void read() throws IOException {
        //获取本地文件
        File file = new File("hello.txt");
        //创建一个输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        //获取一个channel
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建一个buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //通过fileChannel将数据读入到buffer中
        fileChannel.read(byteBuffer);
        //从buffer读取数据
        byte[] array = byteBuffer.array();
        System.out.println(new String(array));
        fileInputStream.close();
    }

    /**
     * 将本地文件复制一份
     */
    public static void copy() throws IOException {
        //先将本地文件读取到内存中
        File file = new File("hello.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //todo:循环读取，每次需要使用buffer.clear属性复位
        fileChannel.read(byteBuffer);
        //将buffer中的数据写入到内存中
        FileOutputStream fileOutputStream = new FileOutputStream("hello_copy.txt");
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();
        byteBuffer.flip();
        fileOutputStreamChannel.write(byteBuffer);
        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 使用transferFrom
     */
    public static void copy2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("hello.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream("hello_copy2.txt");

        //源通道和目标通道
        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destChannel = fileOutputStream.getChannel();

        destChannel.transferFrom(sourceChannel,0,sourceChannel.size());

        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void copy3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("hello.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream("hello_copy3.txt");

        //源通道和目标通道
        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destChannel = fileOutputStream.getChannel();

        sourceChannel.transferTo(0,sourceChannel.size(),destChannel);

        fileInputStream.close();
        fileOutputStream.close();
    }
}
