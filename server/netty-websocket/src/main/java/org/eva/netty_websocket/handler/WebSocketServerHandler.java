package org.eva.netty_websocket.handler;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eva.netty_websocket.message.MessageProgress;
import org.eva.netty_websocket.message.protobuf.ProtebufMessageProgress;
import org.eva.netty_websocket.user.DatabaseUser;
import org.eva.netty_websocket.user.GroupManager;
import org.eva.netty_websocket.user.UserInfo;

import com.google.common.base.Strings;
import com.google.inject.Injector;
import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author guihao
 * @date 2017年1月6日下午5:33:29
 * @desc 参考了github上面的c1000-server的代码
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

	static Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());

	Injector injector;

	public WebSocketServerHandler(Injector injector) {
		super();
		this.injector = injector;
	}

	static {
		logger.setLevel(Level.INFO);
	}

	private WebSocketServerHandshaker handshaker;// 第一次握手时获取实例

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);// 仅仅第一次走这个分支
		} else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		} else {
			throw new Exception("真的是无法理解");
		}
	}

	/**
	 * 解析握手请求
	 * 
	 * @param ctx
	 * @param request
	 */
	public void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
		Map<String, List<String>> parameters = queryStringDecoder.parameters();// 解析uri上带的参数
		List<String> list = parameters.get(CommonParam.UID_PARAM);
		List<String> pwds = parameters.get(CommonParam.PWD_PARAM);
		if (pwds == null || (pwds.size() == 0) || list == null || (list.size() == 0)
				|| Strings.isNullOrEmpty(list.get(0))) {
			logger.log(Level.SEVERE, "用户id为空");// "用户id为空"
			String msg = "error id";
			auth(ctx, msg);
			return;// 直接退回
		}
		final String uid = list.get(0);
		// DatabaseUser db = new MemoryUserModel();
		DatabaseUser db = injector.getInstance(DatabaseUser.class);
		if (!db.checkUserPasswd(uid, pwds.get(0))) {//
			auth(ctx, "error pwd");
			return;// 直接退回
		}
		WebSocketServerHandshakerFactory wsFactory = factory(request);
		handshaker = wsFactory.newHandshaker(request);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), request).addListener(new GenericFutureListener<Future<? super Void>>() {

				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					if (future.isSuccess()) {
						UserInfo userInfo = new UserInfo(ctx, uid);
						ctx.channel().attr(UserInfo.CHANNEL_INFO).set(userInfo);//用户登录
						logger.severe("成功登陆:" + ctx.channel().remoteAddress());
					}
				}
			});
		}
	}

	/*
	 * 用于处理认证失败的方法，可以修改
	 */
	private void auth(ChannelHandlerContext ctx, String msg) {
		HttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
		res.headers().set(HttpHeaderNames.WWW_AUTHENTICATE, msg);
		HttpUtil.setContentLength(res, 0);
		ctx.channel().writeAndFlush(res, ctx.channel().newPromise());
	}

	/**
	 * 创建websocket
	 * 
	 * @param request
	 * @return
	 */
	private WebSocketServerHandshakerFactory factory(FullHttpRequest request) {
		return new WebSocketServerHandshakerFactory(
				"ws://" + request.headers().get(HttpHeaderNames.HOST) + CommonParam.WEBSOCKET_PATH,
				CommonParam.SUB_PROTOCOL, true);
	}

	public void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		if (frame instanceof TextWebSocketFrame) {
			logger.log(Level.SEVERE, "无法解析TextWebSocketFrame");
			TextWebSocketFrame txt = (TextWebSocketFrame) frame;
			String rtn = getUserInfo(ctx).getUid() + ":无法解析TextWebSocketFrame:" + txt.text();
			ctx.channel().writeAndFlush(new TextWebSocketFrame(rtn));
			return;
		}
		if (frame instanceof BinaryWebSocketFrame) {
			BinaryWebSocketFrame binframe = (BinaryWebSocketFrame) frame;
			byte[] bytes = new byte[binframe.content().readableBytes()];
			binframe.content().readBytes(bytes);

			try {
				MessageProgress mp = new ProtebufMessageProgress(ctx, bytes);
				mp.progress();
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
				logger.log(Level.SEVERE, "无法解析", e);
			}
			return;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		removeUser(ctx);
		ctx.channel().writeAndFlush(new TextWebSocketFrame("err:" + cause.getMessage()));
		ctx.close();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		logger.log(Level.SEVERE, "收到" + incoming.remoteAddress() + " 握手请求");
	}

	/**
	 * 断开连接
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		removeUser(ctx);
//		handshaker.close(ctx.channel(), new CloseWebSocketFrame());
		GroupManager.INSTANCE.showAllMembers();
		GroupManager.INSTANCE.showGroups();
	}

	private UserInfo getUserInfo(ChannelHandlerContext ctx) {
		return ctx.channel().attr(UserInfo.CHANNEL_INFO).get();
	}

	private void removeUser(ChannelHandlerContext ctx){
		UserInfo ui = getUserInfo(ctx);
		if(ui != null){
			ui.logout();
			ctx.channel().attr(UserInfo.CHANNEL_INFO).set(null);
		}
	}
}
