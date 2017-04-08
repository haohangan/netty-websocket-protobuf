package org.eva.netty_websocket.user;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.eva.netty_cloud.group.Group;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * channel的用户session，记录用户的状态
 * 
 * @author 976175665
 * @date 2017年4月2日 下午2:34:28
 * @date 2017年4月4日 下午22:28:28 不支持多端登录，没有定义多种客户端
 */
public class UserInfo {

	public static final AttributeKey<UserInfo> CHANNEL_INFO = AttributeKey.valueOf("channelInfo");// 所有用户的用户信息

	private String channelId;
	private String uid;// 用户id
	private AtomicLong reqTime;// 暂时不考虑重置请求次数，没那么多次
	private Channel channel;
	private Set<String> groups;
	private UserRole role;

	public UserInfo(ChannelHandlerContext ctx, String id, UserRole role) {
		channel = ctx.channel();
		channelId = channel.id().asLongText();
		reqTime = new AtomicLong(0);
		uid = id;
		this.role = role;
		if (role.equals(UserRole.User)) {
			groups = new HashSet<>();
			groups.addAll(Group.INSTANCE.getCommonGroup());// 增加默认的group
			groups.forEach(s -> {
				boolean flag = GroupManager.INSTANCE.regToGroup(s, channel);
				System.out.println("reg to " + s + ":" + flag);
			});
			GroupManager.INSTANCE.addToMembers(this);
		}
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

	/**
	 * 用户退出的，退出所有用户组
	 */
	public void logout() {
		if (role.equals(UserRole.User)) {
			GroupManager.INSTANCE.removeChannel(this);
		}
	}

	public String getUid() {
		return uid;
	}

	public Set<String> getGroups() {
		return groups;
	}

	/**
	 * 增加请求次数
	 */
	public void increaseReqTime() {
		reqTime.incrementAndGet();
	}

	/**
	 * 用户注册到用户组
	 * 
	 * @param groupName
	 */
	public boolean regGroup(String groupName) {
		if (role.equals(UserRole.Server)) {
			return false;
		}
		groups.add(groupName);
		return GroupManager.INSTANCE.regToGroup(groupName, channel);
	}

	/**
	 * 打印用户信息
	 */
	public void showUserInfo() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", reqTime=" + reqTime + ", groups=" + groups + "]";
	}

}
