package com.demo.nio.selector;

import com.demo.nio.bytebuffer.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Author psikun
 * @Description Sever
 * @Date 2023/06/19/ 11:15
 */

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {

        // 1. 创建selector,管理多个channel
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2.建立selector和channel的联系(注册)
        // SelectionKey：事件发生后，通过他可以知道事件和哪个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);

        /*
            accept-会在有连接请求时触发
            connect-是客户端，连接建立后触发
            read-可读事件
            write-可写事件
         */


        // key 只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key:{}", sscKey);

        ssc.bind(new InetSocketAddress(8080));


        while (true) {
            // 3. select方法，没有时间发生，线程阻塞，有事件，线程才会恢复运行
            selector.select();

            // 4. 处理事件,selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 处理key时，要从selectedKeys集合中删除，否词下次会有问题
                iterator.remove();
                log.debug("key:{}", key);
                // 5. 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                } else if (key.isReadable()) {
                    // 拿到触发事件的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    channel.read(buffer);
                    buffer.flip();
                    ByteBufferUtil.debugRead(buffer);
                }

            }
        }
    }
}
