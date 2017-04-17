package org.eva.netty_websocket.handler;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eva.netty_cloud.group.Group;
import org.eva.netty_websocket.inject.DBInjector;
import org.eva.netty_websocket.inject.SingleServerProgressModule;
import org.eva.netty_websocket.user.GroupManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * @author guihao
 * @date 2017年1月6日下午4:00:00
 * @desc websocket server
 */
public class WSServer {
	static final int DEFAULT_PORT = 80;
	static boolean SSL = false;// 不开启ssl
	Injector injector;// 依赖

	public void run(int port,String nodeName) throws Exception {
		final SslContext sslCtx;
		if (SSL) {
			SelfSignedCertificate cercificate = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(cercificate.certificate(), cercificate.privateKey()).build();
		} else {
			sslCtx = null;
		}

		System.out.println("初始化依赖控制");
		injector = Guice.createInjector(new DBInjector(),new SingleServerProgressModule());//多个injector
		Group.INSTANCE.initGroups();
		Group.INSTANCE.addSpecial("USERS");
		GroupManager.INSTANCE.createNewChannelGroup(Group.INSTANCE.getAllGroup());// 初始化时初始化用户组，其他时间不可新增用户组

		EventLoopGroup bossGroup = bossGroup();
		EventLoopGroup workGroup = workGroup();

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workGroup).option(ChannelOption.SO_BACKLOG, 1024)
					.option(ChannelOption.SO_REUSEADDR, true).childOption(ChannelOption.SO_REUSEADDR, true);
			if (Epoll.isAvailable()) {
				serverBootstrap.channel(EpollServerSocketChannel.class);
			} else {
				serverBootstrap.channel(NioServerSocketChannel.class);
			}
			serverBootstrap.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new WebSocketServerInitializer(sslCtx, injector,nodeName));
			Channel channel = serverBootstrap.bind(port).sync().channel();
			System.out.println("netty websocket启动成功，端口：" + port);
			Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					// String now =
					// LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd
					// HH:mm:ss"));
					// String time = "time:" + System.currentTimeMillis();
					// GroupManager.INSTANCE.writeToAllMembers(new
					// TextWebSocketFrame(time));
				}
			}, 1000 * 5, 1000, TimeUnit.MILLISECONDS);
			channel.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	/**
	 * @return
	 */
	private EventLoopGroup workGroup() {
		if (Epoll.isAvailable()) {
			return new EpollEventLoopGroup();
		}
		return new NioEventLoopGroup();
	}

	/**
	 * @return
	 */
	private EventLoopGroup bossGroup() {
		if (Epoll.isAvailable()) {
			return new EpollEventLoopGroup();
		}
		return new NioEventLoopGroup();
	}

}
