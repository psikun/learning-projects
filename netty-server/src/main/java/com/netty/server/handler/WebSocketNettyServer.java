package com.netty.server.handler;

import com.netty.server.server.WebSocketChannelInit;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Objects;

/**
 * @Author psikun
 * @Description WebSocketNettyServer
 * @Date 2023/08/10/ 14:14
 */

@Data
@Slf4j
@Component
public class WebSocketNettyServer {


    /**
     * 自定义入站处理器
     */
    @Autowired
    private WebSocketNettyHandler webSocketNettyHandler;


    /**
     * 通道初始化对象
     */
    @Autowired
    private WebSocketChannelInit webSocketChannelInitializer;


    /**
     * boss线程组，用于处理连接工作
     */
    private EventLoopGroup boss;


    /**
     * work线程组，用于进行数据的处理
     */
    private EventLoopGroup work;


    /**
     * 自定义启动方法
     *
     * @param port 端口
     */
    public void start(int port) {

        // 设置boss线程组
        boss = new NioEventLoopGroup(1);

        // 设置work线程组
        work = new NioEventLoopGroup();

        // Netty服务器启动对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();


        serverBootstrap
                // 指定两个线程池
                .group(boss, work)

                // 制定Netty通道类型
                .channel(NioServerSocketChannel.class)

                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)

                //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)

                //将小的数据包包装成更大的帧进行传送，提高网络的负载,即TCP延迟传输
                .childOption(ChannelOption.TCP_NODELAY, true)

                // 指定通道初始化器用来加载当Channel收到事件消息后
                .childHandler(webSocketChannelInitializer);

        // 绑定ip和端口启动服务端
        ChannelFuture future = null;
        try {
            // 绑定netty的启动端口
            future = serverBootstrap.bind(port).sync();
            if (future.isSuccess()) {
                log.info("Netty服务器启动成功" + "--端口:" + port);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            // 调用关闭方法
            close();
        }
    }


    /**
     * 关闭方法
     */
    @PreDestroy
    public void close() {
        if (Objects.nonNull(boss)) {
            boss.shutdownGracefully();
        }
        if (Objects.nonNull(work)) {
            work.shutdownGracefully();
        }
        log.info("关闭Netty服务器");
    }

}
