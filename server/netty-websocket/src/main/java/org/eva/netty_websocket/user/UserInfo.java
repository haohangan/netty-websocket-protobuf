package org.eva.netty_websocket.user;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * channel的用户session，记录用户的状态
 * 
 * @author 976175665
 * @date 2017年4月2日 下午2:34:28
 */
public class UserInfo {
	private String channelId;
	private String uid;//用户id
	private AtomicLong reqTime;// 暂时不考虑重置请求次数，没那么多次
	private Channel channel;
	private AtomicBoolean login;// false:未登录，true：已登陆
	
	public UserInfo(ChannelHandlerContext ctx,String id) {
		channel = ctx.channel();
		channelId = channel.id().asLongText();
		reqTime = new AtomicLong(0);
		login = new AtomicBoolean(true);
		uid = id;
	}

	public String getChannelId() {
		return channelId;
	}

	public AtomicLong getReqTime() {
		return reqTime;
	}

	public Channel getChannel() {
		return channel;
	}

	public boolean isLogin() {
		return login.get();
	}

	public void login(String uid){
		login.set(true);
		this.uid = uid;
	}

	public void logout(){
		login.set(false);
		uid = null;
	}

	public String getUid() {
		return uid;
	}

}
