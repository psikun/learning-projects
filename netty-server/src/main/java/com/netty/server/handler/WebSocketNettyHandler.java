package com.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author psikun
 * @Description WebSocketNettyHandler
 * @Date 2023/08/10/ 11:06
 */

@ChannelHandler.Sharable
@Component
@Slf4j
public class WebSocketNettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    /**
     * 存储当前连接上的通道
     */
    List<ChannelHandlerContext> CHANNEL_CONNECT = new CopyOnWriteArrayList<>();


    /**
     * 存储用户对应的通道
     */
    Map<String, ChannelHandlerContext> CHANNEL_USER = new ConcurrentHashMap<>(16);


    /**
     * 当一个通道（Channel）被激活时，会调用此方法，可以在通道就绪并且可以进行数据传输之前执行一些初始化操作
     *
     * @param ctx 上下文信息
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 存储新链接上的通道
        CHANNEL_CONNECT.add(ctx);

        // 获取客户端连接IP
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = remoteAddress.getAddress().getHostAddress();

        log.info("新增连接：" + clientIp + "--当前连接数量:" + CHANNEL_CONNECT.size());
    }


    /**
     * 有新消息时会调用这个方法
     *
     * @param channelHandlerContext 上下文处理器
     * @param textWebSocketFrame    文本
     * @throws Exception 异常信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 获取通道发来的消息
        String text = textWebSocketFrame.text();
        System.out.println("客户端消息:" + text);
        channelHandlerContext.writeAndFlush(new TextWebSocketFrame("服务器: 收到客户端发送来的消息: " + text));
    }


    /**
     * 当通道变为不活跃状态时（通常是连接关闭时），Netty会调用此方法。可以在此方法释放资源、清理状态等
     *
     * @param ctx 上下文信息
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_CONNECT.remove(ctx);
    }

    /**
     * 当在处理过程中出现异常时，会调用此方法。您可以在这里处理异常情况，比如关闭通道等。
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        CHANNEL_CONNECT.remove(ctx);
    }
}
