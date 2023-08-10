package com.netty.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author psikun
 * @Description NettyConfig
 * @Date 2023/08/10/ 11:03
 */

@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {

    /**
     * netty监听端口
     */
    private int port;

    /**
     * websocket访问路径
     */
    private String path;

}
