package org.eva.netty_websocket.message.protobuf;

import org.eva.netty_websocket.message.AbstractMessageProcess;
import org.eva.netty_websocket.user.DatabaseUser;
import org.eva.netty_websocket.user.MemoryUserModel;
import org.eva.netty_websocket.util.ProtobufMessageUtil;

import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.channel.ChannelHandlerContext;

public class ProtebufMessageProgress extends AbstractMessageProcess {
	WSMessage usermsg;
	DatabaseUser database;

	public ProtebufMessageProgress(ChannelHandlerContext ctx, byte[] bytes) throws InvalidProtocolBufferException {
		super(ctx);
		this.usermsg = WSMessage.parseFrom(bytes);
		database = new MemoryUserModel();
	}

	@Override
	public void progress() {
		switch (usermsg.getType()) {
		case MSG:// 用户消息
			String tid = usermsg.getTId();// 目标用户id
			if (members.containsKey(tid)) {// 如果目标用户也登陆，则发送消息给目标用户
				writeAnother(members.get(tid), ProtobufMessageUtil.getFrame(usermsg));
			} else {
				writeBack("目标用户" + tid + "尚未登陆，无法发送消息");
			}
			break;
		case REG:// 消息系统？暂时不考虑
			System.out.println("注册到某个通道，必须事先已经存在某个通道");
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
	protected void writeBack(String msg) {//new BinaryWebSocketFrame()
		WSMessage writemsg = WSMessage.newBuilder(usermsg).setTxt(msg).build();
		ctx.channel().writeAndFlush(ProtobufMessageUtil.getFrame(writemsg));
	}

}
