package org.eva.netty_websocket.user;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import io.netty.util.internal.StringUtil;

/*
 * 测试使用的一些用户
 */
@Singleton
public class MemoryUserModel implements DatabaseUser {

	final static Map<String, String> users = new ConcurrentHashMap<>();

	static {
		Properties prop = new Properties();
		try {
			prop.load(MemoryUserModel.class.getResourceAsStream("users.properties"));
			prop.forEach((k, v) -> {
				users.put(k.toString(), v.toString());
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkUserPasswd(String userid, String passwd) {
		String pwd = users.get(userid);
		if (StringUtil.isNullOrEmpty(pwd)) {
			return false;
		}
		return pwd.equals(passwd);
	}

}
