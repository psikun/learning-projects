package com.netty.demo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author psikun
 * @Description com.netty.demo.nio.TestFileChannelTransferTo
 * @Date 2023/06/19/ 10:00
 */
public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("F:\\Codes\\LearningProjects\\netty-demo\\src\\main\\resources\\data.txt").getChannel();
             FileChannel to = new FileOutputStream("F:\\Codes\\LearningProjects\\netty-demo\\src\\main\\resources\\to.txt").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化,上限2G
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
