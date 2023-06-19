package com.netty.demo.nio;

import lombok.extern.slf4j.Slf4j;

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

@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel
        try (FileChannel channel = new FileInputStream("netty-demo/src/main/resources/data.txt").getChannel()) {
            // 准备缓冲区，allocate分配缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (true) {
                // 从channel读取数据，向buffer写入
                int len = channel.read(buffer);
                log.debug("读取到的字节数 {}", len);
                // 没有内容了
                if (len == -1) {
                    break;
                }
                // 切换至读模式
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.debug("读取到的字节 {}", (char) b);
                }
                // 切换至写模式
                buffer.clear();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
