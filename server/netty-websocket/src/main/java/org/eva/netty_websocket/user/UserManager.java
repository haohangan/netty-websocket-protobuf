package org.eva.netty_websocket.user;

/**
 * 管理用户信息的操作接口
 * 
 * @author 976175665
 * @date 2017年4月2日 下午3:04:16
 * @date 2017年4月4日 下午22:49:20 大的改动
 */
public interface UserManager {

//	void login(String uid);// 用户登录

	void logout();// 用户下线

	void increase();// 增加请求次数

	void show();// 输出用户信息

//	boolean check();// 检查用户登录是否合法

}
