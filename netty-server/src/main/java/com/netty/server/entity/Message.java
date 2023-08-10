package com.netty.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * @Author psikun
 * @Description Message
 * @Date 2023/08/10/ 11:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {


    /**
     * 消息id
     */
    private String id;

    /**
     * 发送者
     */
    private String sender;

    /**
     * 接收者
     */
    private String recipient;


    /**
     * 消息值
     */
    private String info;

    /**
     * 类型 1 上线 2发消息
     */
    private int type;
}