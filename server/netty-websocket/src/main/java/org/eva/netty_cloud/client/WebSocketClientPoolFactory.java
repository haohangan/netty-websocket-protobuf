package org.eva.netty_cloud.client;

import java.net.URI;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.eva.netty_websocket.client.WebSocketJavaClient;

/**
 * 缓存的对象包装
 * 
 * @author 976175665
 * @date 2017年4月8日 下午1:07:29
 */
public class WebSocketClientPoolFactory extends BasePooledObjectFactory<WebSocketJavaClient> {
	private String connectString;
	private String subprotocol;

	public WebSocketClientPoolFactory(String connectString, String subprotocol) {
		super();
		this.connectString = connectString;
		this.subprotocol = subprotocol;
	}

	@Override
	public WebSocketJavaClient create() throws Exception {
		WebSocketJavaClient client = new WebSocketJavaClient(new URI(connectString), subprotocol);
		client.init();
		return client;
	}

	@Override
	public PooledObject<WebSocketJavaClient> wrap(WebSocketJavaClient obj) {
		return new DefaultPooledObject<WebSocketJavaClient>(obj);
	}

	@Override
	public void destroyObject(PooledObject<WebSocketJavaClient> p) throws Exception {
		p.getObject().close();
		super.destroyObject(p);
	}

}
