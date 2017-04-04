package org.eva.netty_websocket.inject;

import org.eva.netty_websocket.user.DatabaseUser;
import org.eva.netty_websocket.user.EnableUserNameModel;

import com.google.inject.AbstractModule;

/**
 * IOC
 * 
 * @author 976175665
 * @date 2017年4月4日 下午2:42:17
 */
public class DBInjector extends AbstractModule{

	@Override
	protected void configure() {
		bind(DatabaseUser.class).to(EnableUserNameModel.class);
	}

}
