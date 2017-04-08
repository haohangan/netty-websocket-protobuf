package org.eva.netty_websocket.message;

import io.netty.channel.ChannelHandlerContext;

/**
 * 解析消息接口
 * @author 976175665
 * @date 2017年4月2日 下午4:32:56
 */
public interface MessageProgress {
	
	void progress(ChannelHandlerContext ctx, byte[] bytes)throws Exception ;//开始解析
	
}
