package org.eva.netty_websocket.handler;

import com.google.inject.Injector;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;

/**
 * @author guihao
 * @date 2017年1月6日下午4:09:57
 * @desc websocket ChannelInitializer
 */
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {

	private final SslContext sslCtx;
	private Injector injector;

	public WebSocketServerInitializer(final SslContext sslCtx,Injector injector) {
		this.sslCtx = sslCtx;
		this.injector = injector;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (sslCtx != null) {
			pipeline.addLast(sslCtx.newHandler(ch.alloc()));
		}
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new WebSocketServerCompressionHandler());
		pipeline.addLast(new WebSocketServerHandler(injector));
	}

}
