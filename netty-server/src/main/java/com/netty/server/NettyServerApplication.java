package com.netty.server;

import com.netty.server.config.NettyConfig;
import com.netty.server.handler.WebSocketNettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyServerApplication implements CommandLineRunner {

    @Autowired
    WebSocketNettyServer webSocketNettyServer;

    @Autowired
    NettyConfig nettyConfig;

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                webSocketNettyServer.start(nettyConfig.getPort());
            }
        }).start();
    }
}
