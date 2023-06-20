package com.demo.nio.bytebuffer;

import java.nio.ByteBuffer;

import static com.demo.nio.bytebuffer.ByteBufferUtil.debugAll;

/**
 * @Author psikun
 * @Description TestByteBufferReadWrite
 * @Date 2023/06/16/ 14:07
 */
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{0x61,0x62,0x63,0x64});
        debugAll(buffer);
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65,0x6f});
        debugAll(buffer);
    }
}
