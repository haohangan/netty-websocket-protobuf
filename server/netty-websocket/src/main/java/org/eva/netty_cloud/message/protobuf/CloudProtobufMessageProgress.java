package org.eva.netty_cloud.message.protobuf;

import org.eva.netty_cloud.curator.ServerClouds;
import org.eva.netty_websocket.message.AbstractMessageProcess;
import org.eva.netty_websocket.user.GroupManager;
import org.eva.netty_websocket.user.UserInfo;
import org.eva.netty_websocket.util.ProtobufMessageUtil;

import io.netty.channel.ChannelHandlerContext;

public class CloudProtobufMessageProgress extends AbstractMessageProcess {
	WSCMessage usermsg;

	public CloudProtobufMessageProgress() {
	}

	// public CloudProtobufMessageProgress(ChannelHandlerContext ctx, byte[]
	// bytes) throws InvalidProtocolBufferException {
	// super(ctx);
	// usermsg = WSCMessage.parseFrom(bytes);
	// }

	@Override
	public void progress(ChannelHandlerContext ctx, byte[] bytes) throws Exception {
		init(ctx, bytes);
		switch (usermsg.getType()) {
		case MSG:// 用户消息
			String tid = usermsg.getTId();// 目标用户id
			if (GroupManager.INSTANCE.containMember(tid)) {// 目标用户是否在本地
				GroupManager.INSTANCE.writeToLocalMember(tid, ProtobufMessageUtil.getFrame(usermsg));
			} else {
				writeCloud(usermsg);
			}
			break;
		case REG:// 消息系统？暂时不考虑
			String groupName = usermsg.getTId();
			boolean flag = ui.regGroup(groupName);
			if (flag) {
				writeBack("注册成功：" + groupName);
			} else {
				writeBack("注册失败：" + groupName);
			}
			break;
		case GROUP:// 用户组消息
			String toGroupName = usermsg.getTId();
			Object msg = ProtobufMessageUtil.getFrame(usermsg);
			GroupManager.INSTANCE.writeToAll(toGroupName, msg);
			writeCloud(usermsg);
			break;
		default:// 无法识别的信息类型
			writeBack("无法识别的信息类型");
		}
	}

	@Override
	protected void writeBack(String msg) {
		WSCMessage writemsg = WSCMessage.newBuilder(usermsg).setTxt(msg).build();
		ctx.channel().writeAndFlush(ProtobufMessageUtil.getFrame(writemsg));
	}

	protected void writeCloud(WSCMessage msg) {
		if (msg.getBroadcast()) {
			WSCMessage newmsg = WSCMessage.newBuilder(msg).setBroadcast(false).build();
			ServerClouds.INSTANCE.write(newmsg);
		}
	}

	@Override
	public void init(ChannelHandlerContext ctx, byte[] bytes) throws Exception {
		this.ctx = ctx;
		ui = ctx.channel().attr(UserInfo.CHANNEL_INFO).get();
		usermsg = WSCMessage.parseFrom(bytes);
	}
}
