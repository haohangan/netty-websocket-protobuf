package org.eva.netty_websocket.message.protobuf;

import org.eva.netty_websocket.message.AbstractMessageProcess;
import org.eva.netty_websocket.user.GroupManager;
import org.eva.netty_websocket.util.ProtobufMessageUtil;

import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.channel.ChannelHandlerContext;

/**
 * 用来解析并处理WSMessage消息
 * 
 * @author 976175665
 * @date 2017年4月4日 下午7:40:55
 */
public class ProtebufMessageProgress extends AbstractMessageProcess {
	WSMessage usermsg;

	public ProtebufMessageProgress(ChannelHandlerContext ctx, byte[] bytes) throws InvalidProtocolBufferException {
		super(ctx);
		this.usermsg = WSMessage.parseFrom(bytes);
	}

	@Override
	public void progress() {
		switch (usermsg.getType()) {
		case MSG:// 用户消息
			String tid = usermsg.getTId();// 目标用户id
			boolean result = GroupManager.INSTANCE.writeToMember(tid, ProtobufMessageUtil.getFrame(usermsg));
			if (!result) {
				writeBack("发送失败：目标用户" + tid + "，无法发送消息");
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
			break;
		default:// 无法识别的信息类型
			writeBack("无法识别的信息类型");
		}
	}

	/**
	 * 回写，可实现acknowledge
	 * 
	 */
	@Override
	protected void writeBack(String msg) {// new BinaryWebSocketFrame()
		WSMessage writemsg = WSMessage.newBuilder(usermsg).setTxt(msg).build();
		ctx.channel().writeAndFlush(ProtobufMessageUtil.getFrame(writemsg));
	}

}
