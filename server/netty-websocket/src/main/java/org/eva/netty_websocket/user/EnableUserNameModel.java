package org.eva.netty_websocket.user;

import javax.inject.Singleton;

import com.google.common.base.Strings;

/**
 * 无需密码就可以登陆，仅测试时使用
 * 
 * @author 976175665
 * @date 2017年4月4日 下午2:41:43
 */
@Singleton
public class EnableUserNameModel implements DatabaseUser {

	public EnableUserNameModel() {
		super();
		System.out.println("EnableUserNameModel");
	}

	@Override
	public boolean checkUserPasswd(String userid, String passwd) {
		if (!Strings.isNullOrEmpty(userid)) {
			return true;
		}
		return false;
	}

}
