package org.eva.netty_cloud.client;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.eva.netty_websocket.client.WebSocketJavaClient;

/**
 * 对象池
 * 
 * @author 976175665
 * @date 2017年4月8日 下午1:07:05
 */
public class WebSocketClientPool extends GenericObjectPool<WebSocketJavaClient> {

	public WebSocketClientPool(PooledObjectFactory<WebSocketJavaClient> factory) {
		super(factory);
	}

	public WebSocketClientPool(PooledObjectFactory<WebSocketJavaClient> factory, GenericObjectPoolConfig config) {
		super(factory, config);
	}

	/**
	 * 对象池通用配置
	 * 
	 * @return
	 */
	public static GenericObjectPoolConfig config() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(5);
		config.setMaxTotal(10);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		return config;
	}

	/**
	 * 获取对象池
	 * 
	 * @param connectString
	 * @return
	 */
	public static WebSocketClientPool createObjectPool(String connectString,String subprotocol) {
		GenericObjectPoolConfig config = config();
		WebSocketClientPool pool = new WebSocketClientPool(new WebSocketClientPoolFactory(connectString,subprotocol), config);
		return pool;
	}
}
