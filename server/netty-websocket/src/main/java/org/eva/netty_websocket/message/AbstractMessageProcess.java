package org.eva.netty_websocket.message;


import org.eva.netty_websocket.user.UserInfo;
import org.eva.netty_websocket.user.UserManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
/**
 * 消息解析的基本实现
 * @author 976175665
 * @date 2017年4月3日 下午1:48:09
 */
public abstract class AbstractMessageProcess implements MessageProgress, UserManager {
	protected ChannelHandlerContext ctx;

	public AbstractMessageProcess(ChannelHandlerContext ctx) {
		super();
		this.ctx = ctx;
	}

	protected abstract void writeBack(String msg);// 使用不同的消息，需要具体的实现

	/**
	 * 传递消息
	 * 
	 * @param channel
	 * @param obj
	 */
	protected void writeAnother(Channel channel, Object obj) {
		channel.writeAndFlush(obj);
	}

	@Override
	public void show(ChannelHandlerContext ctx) {
		UserInfo userInfo = ctx.channel().attr(CHANNEL_INFO).get();
		System.out.println(
				"输出用户信息：" + userInfo.getChannelId() + "\t" + userInfo.getReqTime() + "\t" + userInfo.isLogin());
	}


	@Override
	public void increase(ChannelHandlerContext ctx) {
		UserInfo userInfo = ctx.channel().attr(CHANNEL_INFO).get();
		userInfo.getReqTime().incrementAndGet();// 记录请求次数
	}


	@Override
	public boolean check(ChannelHandlerContext ctx) {
		UserInfo userInfo = ctx.channel().attr(CHANNEL_INFO).get();
		return userInfo.isLogin();
	}

}
