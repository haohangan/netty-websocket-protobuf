package org.eva.netty_websocket.util;

import com.google.protobuf.AbstractMessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

public class ProtobufMessageUtil {

	@Deprecated
	public static ByteBuf getBytes(AbstractMessageLite<?, ?> proto) {
		byte[] bytes = proto.toByteArray();
		return Unpooled.copiedBuffer(bytes);
	}

	public static BinaryWebSocketFrame getFrame(AbstractMessageLite<?, ?> proto) {
		byte[] bytes = proto.toByteArray();
		ByteBuf buf = Unpooled.copiedBuffer(bytes);
		return new BinaryWebSocketFrame(buf);
	}
}
