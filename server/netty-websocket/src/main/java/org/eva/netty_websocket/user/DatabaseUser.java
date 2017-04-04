package org.eva.netty_websocket.user;

/**
 * 简单的用户登录认证模块
 * @author 976175665
 * @date 2017年4月2日 下午5:12:41
 */
public interface DatabaseUser {

	boolean checkUserPasswd(String userid,String passwd);
	
}
