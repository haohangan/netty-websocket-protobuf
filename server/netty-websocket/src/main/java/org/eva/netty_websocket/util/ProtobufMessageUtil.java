package org.eva.netty_websocket.util;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

/**
 * protobuf 和 ByteBuf 工具类
 * 
 * @author 976175665
 * @date 2017年4月4日 下午7:42:45
 */
public class ProtobufMessageUtil {

	@Deprecated
	public static ByteBuf getBytes(AbstractMessageLite<?, ?> proto) {
		byte[] bytes = proto.toByteArray();
		return Unpooled.copiedBuffer(bytes);
	}

	public static BinaryWebSocketFrame getFrame(MessageLite proto) {
		byte[] bytes = proto.toByteArray();
		ByteBuf buf = Unpooled.copiedBuffer(bytes);
		return new BinaryWebSocketFrame(buf);
	}
}
