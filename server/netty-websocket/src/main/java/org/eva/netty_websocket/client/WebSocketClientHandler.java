package org.eva.netty_websocket.client;

import org.eva.netty_websocket.message.protobuf.WSMessage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;

/**
 * websocket客户端消息处理handler
 * 
 * @author 976175665
 * @date 2017年4月4日 下午4:59:53
 */
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
	
	private final WebSocketClientHandshaker handshaker;

	private ChannelPromise handshakeFuture;

	public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
		this.handshaker = handshaker;
	}

	public ChannelFuture handshakeFuture() {
		return handshakeFuture;
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		handshakeFuture = ctx.newPromise();
	}

	/**
	 * 开始握手
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		handshaker.handshake(ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		System.out.println("WebSocket Client disconnected!");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		Channel ch = ctx.channel();
		if (!handshaker.isHandshakeComplete()) {
			handshaker.finishHandshake(ch, (FullHttpResponse) msg);
			System.out.println("WebSocket Client connected!");
			handshakeFuture.setSuccess();
			return;
		}
		if (msg instanceof FullHttpResponse) {
			FullHttpResponse response = (FullHttpResponse) msg;
			throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + response.status() + ", content="
					+ response.content().toString(CharsetUtil.UTF_8) + ')');
		}

		WebSocketFrame frame = (WebSocketFrame) msg;
		if (frame instanceof TextWebSocketFrame) {
			TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
			if(textFrame.text().startsWith("time:")){
				return;
			}
			System.out.println("WebSocket Client received message: " + textFrame.text());
		} else if (frame instanceof PongWebSocketFrame) {
			System.out.println("WebSocket Client received pong");
		} else if (frame instanceof CloseWebSocketFrame) {
			System.out.println("WebSocket Client received closing");
			ch.close();
		} else if (frame instanceof BinaryWebSocketFrame) {
			BinaryWebSocketFrame binframe = (BinaryWebSocketFrame) frame;
			byte[] bytes = new byte[binframe.content().readableBytes()];
			binframe.content().readBytes(bytes);
			
			WSMessage servermsg = WSMessage.parseFrom(bytes);
			
			System.out.println("服务端信息："+servermsg.getMid()+"\t"+servermsg.getUid()+"\t"+servermsg.getTxt()+"\t"+servermsg.getTId());
		}
	}

	/**
	 * 报错情况
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if (!handshakeFuture.isDone()) {
			handshakeFuture.setFailure(cause);
		}
		ctx.close();
	}

}
