package com.netty.demo.nio.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @Author psikun
 * @Description TestByteBufferAllocate
 * @Date 2023/06/16/ 14:25
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        // 静态，不能动态分配
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

        /*
        class java.nio.HeapByteBuffer        - Java堆内存，读写效率低，受到GC影响
        class java.nio.DirectByteBuffer      - 直接内存，读写效率高(少一次拷贝)，不会受GC影响，分配的效率较低
         */
    }
}
