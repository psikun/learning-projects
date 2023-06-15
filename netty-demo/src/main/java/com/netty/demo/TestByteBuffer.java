package com.netty.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author psikun
 * @Description TestByteBuffer
 * @Date 2023/06/15/ 16:01
 */
public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            // 准备缓冲区，allocate分配缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocate(10);

            // 从channel读取数据，向buffer写入
            channel.read(buffer);

            // 打印buffer内容
            // 切换至读模式
            buffer.flip();
            while (buffer.hasRemaining()) {
                byte b = buffer.get();
                System.out.println((char) b);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
