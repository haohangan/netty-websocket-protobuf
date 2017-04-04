package org.eva.netty_websocket.user;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 操作所有用户组，包括增加用户组，增加用户，移除用户，广播普通信息
 * 
 * @author 976175665
 * @date 2017年4月4日 下午10:19:49
 */
public enum GroupManager {
	INSTANCE;

	private final ConcurrentHashMap<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();// 用户组集合

	// 用户uid<-->用户channel
	private final ConcurrentHashMap<String, Channel> members = new ConcurrentHashMap<>();// 普通用户，客户端用户

	/**
	 * 将某个channel放入某个用户组中
	 * 
	 * @param groupName
	 * @param channle
	 * @return
	 */
	public boolean regToGroup(String groupName, Channel channel) {
		if (channelGroupMap.containsKey(groupName)) {
			return channelGroupMap.get(groupName).add(channel);
		}
		return false;
	}

	/**
	 * 多播给某个用户组
	 * 
	 * @param groupName
	 *            用户组名称
	 * @param msg
	 *            写入的消息
	 */
	public void writeToAll(String groupName, Object msg) {
		if (channelGroupMap.containsKey(groupName)) {
			ChannelGroup group = channelGroupMap.get(groupName);
			group.flush();
			group.writeAndFlush(msg);
		}
	}

	/**
	 * 创建一个新的用户组
	 * 
	 * @param groupName
	 *            用户组名称
	 * @return
	 */
	public void createNewChannelGroup(String groupName) {
		if (channelGroupMap.contains(groupName)) {
			return;
		}
		ChannelGroup cg = new DefaultChannelGroup(groupName, GlobalEventExecutor.INSTANCE);
		channelGroupMap.put(groupName, cg);
	}

	/**
	 * 创建多个用户组
	 * 
	 * @param groupNames
	 */
	public void createNewChannelGroup(Set<String> groupNames) {
		groupNames.forEach(s -> {
			createNewChannelGroup(s);
		});
	}

	/**
	 * 从某个组中移除channel
	 * 
	 * @param groupName
	 * @param channel
	 */
	private void removeFromGroup(String groupName, Channel channel) {
		if (channelGroupMap.contains(groupName)) {
			channelGroupMap.get(groupName).remove(channel);
		}
	}

	/**
	 * 某个用户下线前，所有用户组中移除该用户
	 * 
	 * @param userinfo
	 */
	public void removeChannel(UserInfo userinfo) {
		userinfo.getGroups().forEach(groupName -> {
			removeFromGroup(groupName, userinfo.getChannel());
		});
		members.remove(userinfo.getUid());
	}

	/**
	 * 初始登陆时调用，握手后调用
	 * 
	 * @param userinfo
	 */
	public void addToMembers(UserInfo userinfo) {
		if (members.contains(userinfo.getUid())) {
			// 先要强制下线
			userinfo.getChannel().writeAndFlush(new Object())
					.addListener(new GenericFutureListener<Future<? super Void>>() {

						@Override
						public void operationComplete(Future<? super Void> future) throws Exception {
							userinfo.getChannel().closeFuture();
						}
					});
		}
		members.put(userinfo.getUid(), userinfo.getChannel());
	}

	/**
	 * =====================================================================================
	 */
	/**
	 * 广播给所有用户
	 * 
	 * @param msg
	 */
	public void writeToAllMembers(Object msg) {
		members.forEach((k, v) -> {
			v.writeAndFlush(msg);
		});
	}

	public boolean writeToMember(String tid, Object msg) {
		if (members.containsKey(tid)) {
			members.get(tid).writeAndFlush(msg);
			return true;
		}
		return false;
	}

	public void showAllMembers() {
		System.out.println("All Members=======================");
		members.forEach((k, v) -> {
			System.out.println(k+":"+v.toString());
		});

		System.out.println("=======================All Members");
	}
	
	
	public void showGroups(){
		System.out.println("All Groups=======================");
		channelGroupMap.forEach((k,v)->{
			System.out.println(k.toString()+":"+v.name()+":"+v.isEmpty());
			v.forEach(c->{
				System.out.println(c.attr(UserInfo.CHANNEL_INFO).get().getUid());
			});
			System.out.println("--------------------------------");
		});
		System.out.println("=======================All Groups");
	}
}
