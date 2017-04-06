package org.eva.netty_cloud.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.eva.netty_websocket.client.WebSocketJavaClient;

/**
 * 对象池化
 * 
 * @author 976175665
 * @date 2017年4月6日 下午9:32:36
 */
public class WebSocketServerPool extends BasePooledObjectFactory<WebSocketJavaClient> {
	private final String url;

	private final String subprotocol;

	public WebSocketServerPool(String url, String subprotocol) throws URISyntaxException {
		super();
		this.url = url;
		this.subprotocol = subprotocol;
	}

	public String getUrl() {
		return url;
	}

	public String getSubprotocol() {
		return subprotocol;
	}

	@Override
	public WebSocketJavaClient create() throws Exception {
		WebSocketJavaClient client = new WebSocketJavaClient(new URI(url), subprotocol);
		client.init();
		return client;
	}

	@Override
	public PooledObject<WebSocketJavaClient> wrap(WebSocketJavaClient arg0) {
		return new DefaultPooledObject<WebSocketJavaClient>(arg0);
	}

	@Override
	public void destroyObject(PooledObject<WebSocketJavaClient> p) throws Exception {
		p.getObject().close();
		super.destroyObject(p);
	}

}
