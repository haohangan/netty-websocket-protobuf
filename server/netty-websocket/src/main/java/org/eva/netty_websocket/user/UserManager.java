package org.eva.netty_websocket.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.AttributeKey;

/**
 * 管理用户信息的操作
 * 
 * @author 976175665
 * @date 2017年4月2日 下午3:04:16
 */
public interface UserManager {

	static Logger LOGGER = Logger.getLogger(UserManager.class.getName());

	final static ConcurrentHashMap<String, Channel> members = new ConcurrentHashMap<>();// 所有用户

	final static ConcurrentHashMap<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();// 用户组

	public static final AttributeKey<UserInfo> CHANNEL_INFO = AttributeKey.valueOf("channelInfo");// 所有用户的用户信息

	void show(ChannelHandlerContext ctx);// 输出用户信息

	void increase(ChannelHandlerContext ctx);// 增加请求次数

	// void login(ChannelHandlerContext ctx, String uid);// 设置用户状态为登陆状态
	//
	// void logout(ChannelHandlerContext ctx, String uid);// 设置用户状态为非登陆状态

	boolean check(ChannelHandlerContext ctx);// 检查用户是否登录

	/**
	 * 结束会话时调用,清空掉用户信息
	 * 
	 * @param ctx
	 */
	public static void remove(ChannelHandlerContext ctx) {
		UserInfo ui = ctx.channel().attr(CHANNEL_INFO).get();
		if (ui != null) {
			if (members.containsKey(ui.getUid())) {
				members.remove(ui.getUid());
			}
			ctx.channel().attr(CHANNEL_INFO).set(null);
			LOGGER.info("goodbye " + ui.getUid());
		}
	}

	/**
	 * 创建会话时调用,创建用户信息
	 * 
	 * @param ctx
	 */
	public static void create(ChannelHandlerContext ctx, String uid) {
		LOGGER.info("welcome " + uid);
		UserInfo ui = new UserInfo(ctx, uid);
		ctx.channel().attr(CHANNEL_INFO).set(ui);
		members.put(uid, ctx.channel());// 业务id-->对应的channel
	}

	public static void print() {
		System.out.println("members剩余用户");
		for (Map.Entry<String, Channel> entry : members.entrySet()) {
			System.out.println(entry.getKey() + "\t:\t" + entry.getValue());
		}
		System.out.println("members剩余用户——————————————————————————");
	}
}
