package com.netty.demo.nio.blocking;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Objects;

import static com.netty.demo.nio.bytebuffer.ByteBufferUtil.debugRead;

/**
 * @Author psikun
 * @Description Sever
 * @Date 2023/06/19/ 11:15
 */

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 使用nio来理解阻塞模式，单线程

        // 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置为非阻塞模式
        ssc.configureBlocking(false);

        // 2. 绑定监听接口
        ssc.bind(new InetSocketAddress(8080));

        // 3. 连接集合
        ArrayList<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // 4. accept 建立与客户端连接,SocketChannel 用来与客户端之间通信
            log.debug("connecting... ");
            // 阻塞方法
            // 非阻塞方法，线程还会继续运行，如果没有连接建立，sc为null
            SocketChannel sc = ssc.accept();
            if (Objects.nonNull(sc)) {
                log.debug("connected... {}", sc);
                // 非阻塞方法
                sc.configureBlocking(false);
                channels.add(sc);
            }
            // 5. 接受客户端发送的数据
            for (SocketChannel channel : channels) {
                log.debug("before read... {}", channel);
                // 非阻塞，线程仍会继续运行，如果没有读到数据，read返回0
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.debug("after read... {}", channel);
                }

            }
        }
    }
}
