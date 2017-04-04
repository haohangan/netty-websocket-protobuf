package org.eva.netty_websocket.client;

import java.net.URI;
import java.util.Scanner;

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
 * 测试时候使用的类
 * @author 976175665
 * @date 2017年4月4日 下午7:34:46
 */
public class JavaConsoleClient {
	static final String url = "ws://127.0.0.1/websocket?uname=jack&pwd=";
	static final String subprotocol = "zookeeperWS";
	
	public static void main(String[] args) {

		EventLoopGroup group = new NioEventLoopGroup();
		Scanner scanner = null;
		try {
			WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(
					new URI(url), WebSocketVersion.V13, subprotocol, true, new DefaultHttpHeaders());
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new HttpClientCodec());
					pipeline.addLast(new HttpObjectAggregator(65536));
					// pipeline.addLast(WebSocketClientCompressionHandler.INSTANCE);
					pipeline.addLast(new WebSocketClientHandler(handshaker));
				}

			});

			// Start the connection attempt.
			Channel ch = b.connect("127.0.0.1", 80).sync().channel();

			// Read commands from the stdin.
			ChannelFuture lastWriteFuture = null;

			scanner = new Scanner(System.in);
			for (;;) {
				System.out.println(
						"isActive:" + ch.isActive() + "\tisopen:" + ch.isOpen() + "\tisWritable:" + ch.isWritable());
				String line = scanner.nextLine();
				if (line == null) {
					break;
				}

				// Sends the received line to the server.
				lastWriteFuture = ch.writeAndFlush(new TextWebSocketFrame(line));
				// If user typed the 'bye' command, wait until the server closes
				// the connection.
				if ("bye".equals(line.toLowerCase())) {
					ch.closeFuture();
					break;
				}
			}
			System.out.println("挂壁");
			if (lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			group.shutdownGracefully();
		}
	}
}
