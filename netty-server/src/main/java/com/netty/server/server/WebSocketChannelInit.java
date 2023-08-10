package com.netty.server.server;

import com.netty.server.config.NettyConfig;
import com.netty.server.handler.WebSocketNettyHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author psikun
 * @Description 初始化通道
 * @Date 2023/08/10/ 10:59
 */

@Component
public class WebSocketChannelInit extends ChannelInitializer<SocketChannel> {

    @Autowired
    NettyConfig nettyConfig;

    @Autowired
    WebSocketNettyHandler webSocketNettyHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 对http协议的支持
        pipeline.addLast(new HttpServerCodec());
        // 对大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // HttpObjectAggregator将多个信息转化成单一的request或者response对象
        pipeline.addLast(new HttpObjectAggregator(8000));
        // 将http协议升级为ws协议. websocket的支持
        pipeline.addLast(new WebSocketServerProtocolHandler(nettyConfig.getPath()));
        // 自定义处理handler
        pipeline.addLast(webSocketNettyHandler);
    }
}
