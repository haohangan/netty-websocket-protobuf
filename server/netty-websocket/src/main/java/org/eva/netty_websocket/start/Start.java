package org.eva.netty_websocket.start;

import org.eva.netty_websocket.handler.CommonParam;
import org.eva.netty_websocket.handler.WSServer;

/**
 * 启动
 * 
 * @author 976175665
 * @date 2017年1月8日 下午3:54:04
 */
public class Start {
	public static void main(String[] args) {
		try {
			new WSServer().run(80, CommonParam.WEBSOCKET_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
