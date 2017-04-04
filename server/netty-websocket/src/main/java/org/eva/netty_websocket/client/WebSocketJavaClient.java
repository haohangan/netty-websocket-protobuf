package org.eva.netty_websocket.client;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

import org.eva.netty_websocket.message.protobuf.WSMessage;
import org.eva.netty_websocket.message.protobuf.WSMessage.MsgType;
import org.eva.netty_websocket.util.ProtobufMessageUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

/**
 * websocket java客户端简单封装
 * 
 * @author 976175665
 * @date 2017年4月4日 下午7:35:31
 */
public class WebSocketJavaClient {
	private AtomicLong reqTime = new AtomicLong(0);
	private Channel channel;
	EventLoopGroup group;
	ChannelFuture lastWriteFuture;
	private URI uri;
	private String subprotocol;

	public WebSocketJavaClient(URI uri, String subprotocol) {
		super();
		this.uri = uri;
		this.subprotocol = subprotocol;
	}

	public void init() {
		group = new NioEventLoopGroup();
		WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13,
				subprotocol, true, new DefaultHttpHeaders());
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new HttpClientCodec());
				pipeline.addLast(new HttpObjectAggregator(65536));
				pipeline.addLast(new WebSocketClientHandler(handshaker));
			}

		});
		try {
			channel = b.connect("127.0.0.1", 80).sync().channel();
		} catch (InterruptedException e) {
			close();
		}
	}

	public void write(String msg) {
		lastWriteFuture = channel.writeAndFlush(new TextWebSocketFrame(msg));
	}

	public void write(String uid, String tid, String msg) {
		WSMessage writemsg = WSMessage.newBuilder().setType(MsgType.MSG).setMid(reqTime.incrementAndGet()).setUid(uid)
				.setTId(tid).setTxt(msg).build();
		channel.writeAndFlush(ProtobufMessageUtil.getFrame(writemsg));
	}

	public void close() {
		if (lastWriteFuture != null) {
			try {
				lastWriteFuture.sync();
			} catch (InterruptedException e) {
			}
		}
		channel.closeFuture();
		group.shutdownGracefully();
	}
}
