package com.example.netty.chatroom.common.core.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteOrder;

/**
 * @description: buff工厂
 * @author: TGL
 *
 */
public class BufferFactory {
	
	public static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;
	
	private static ByteBufAllocator bufAllocator = PooledByteBufAllocator.DEFAULT;

	/**
	 * 获取一个buffer
	 * 
	 * @return
	 */
	public static ByteBuf getBuffer() {
		ByteBuf buffer = bufAllocator.heapBuffer();
		buffer = buffer.order(BYTE_ORDER);
		return buffer;
	}

	/**
	 * 将数据写入buffer
	 * @param bytes
	 * @return
	 */
	public static ByteBuf getBuffer(byte[] bytes) {
		ByteBuf buffer = bufAllocator.heapBuffer();
		buffer = buffer.order(BYTE_ORDER);
		buffer.writeBytes(bytes);
		return buffer;
	}

}
