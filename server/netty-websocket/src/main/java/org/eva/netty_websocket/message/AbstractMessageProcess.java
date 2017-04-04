package org.eva.netty_websocket.message;

import org.eva.netty_websocket.user.UserInfo;
import org.eva.netty_websocket.user.UserManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息解析的基本实现
 * 
 * @author 976175665
 * @date 2017年4月3日 下午1:48:09
 */
public abstract class AbstractMessageProcess implements MessageProgress, UserManager {
	protected ChannelHandlerContext ctx;
	protected UserInfo ui;

	public AbstractMessageProcess(ChannelHandlerContext ctx) {
		super();
		this.ctx = ctx;
		ui = ctx.channel().attr(UserInfo.CHANNEL_INFO).get();
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
	public void show() {
		UserInfo userInfo = ctx.channel().attr(UserInfo.CHANNEL_INFO).get();
		userInfo.showUserInfo();
	}

	@Override
	public void increase() {
		UserInfo userInfo = ctx.channel().attr(UserInfo.CHANNEL_INFO).get();
		userInfo.increaseReqTime();// 记录请求次数
	}


	@Override
	public void logout() {
		UserInfo userInfo = ctx.channel().attr(UserInfo.CHANNEL_INFO).get();
		if(userInfo!=null){
			userInfo.logout();
		}
	}

}
