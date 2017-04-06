package org.eva.netty_cloud.curator;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eva.netty_cloud.client.WebSocketServerPool;

import com.google.protobuf.MessageLite;

/**
 * 操作服务本地缓存
 * 
 * @author 976175665
 * @date 2017年4月7日 上午1:10:45
 */
public enum ServerClouds {
	INSTANCE;

	ExecutorService executor = Executors.newFixedThreadPool(100);

	private Map<String, NettyServerNode> nodes = new HashMap<>();

	private Map<String, WebSocketServerPool> clientPool = new HashMap<>();

	public void addServer(NettyServerNode node) throws URISyntaxException {
		clientPool.put(node.getNodeName(), new WebSocketServerPool(node.getUrl(), node.getSubprotocol()));
		nodes.put(node.getNodeName(), node);
	}

	/**
	 * 修改
	 * 
	 * @param enode
	 * @throws URISyntaxException
	 */
	public void editServer(NettyServerNode enode) throws URISyntaxException {
		String key = enode.getNodeName();
		if (nodes.get(key).getUrl().equals(enode.getUrl())) {// url没有变化，则不需要改变pool
			nodes.put(key, enode);
		} else {
			addServer(enode);// 新增
		}
	}

	/**
	 * 移除server
	 * 
	 * @param enode
	 */
	public void deleteServer(NettyServerNode enode) {
		String key = enode.getNodeName();
		nodes.remove(key);
		clientPool.remove(key);
	}

	public void write(String key, MessageLite obj) {
		clientPool.forEach((k, v) -> {
			if (!key.equals(k)) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							v.makeObject().getObject().write(obj);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
}
