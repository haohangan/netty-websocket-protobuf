package org.eva.netty_cloud.curator;

import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eva.common.CommonParam;
import org.eva.netty_cloud.client.WebSocketClientPool;
import org.eva.netty_websocket.client.WebSocketJavaClient;

import com.google.protobuf.MessageLite;

/**
 * 操作服务本地缓存
 * 
 * @author 976175665
 * @date 2017年4月7日 上午1:10:45
 */
public enum ServerClouds {
	INSTANCE;

	static Logger LOGGER = Logger.getGlobal();

	private String currentNodeName;// 自身节点名称

	private NettyServerManager manager;

	public void init(int port, String currentNodeName, String connectString) throws Exception {
		this.currentNodeName = currentNodeName;
		String address = InetAddress.getLocalHost().getHostAddress();
		String url = "ws://" + address + ":" + port + "/" + currentNodeName;
		NettyServerNode node = new NettyServerNode.Builder().setNodeName(currentNodeName)
				.setSubprotocol(CommonParam.SUB_PROTOCOL).setUrl(url).build();

		manager = new NettyServerManager(CommonParam.PARENT_NODE, connectString, node);
		manager.initClouds();
	}

	public void shudownCloud() {
		LOGGER.log(Level.SEVERE, "关闭集群开始");
		try {
			manager.closeCloud();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "关闭集群", e);
		}
		pools.forEach((k, v) -> {
			v.close();
		});
		executor.shutdown();
		pools = null;
		LOGGER.log(Level.SEVERE, "关闭集群结束");
	}

	ExecutorService executor = Executors.newFixedThreadPool(100);

	private Map<String, NettyServerNode> nodes = new ConcurrentHashMap<>();// zookeeper上所有节点的配置信息

	private Map<String, WebSocketClientPool> pools = new ConcurrentHashMap<>();// 连接其他服务器的

	/**
	 * 增加服务端
	 * 
	 * @param node
	 * @throws URISyntaxException
	 */
	public void addServer(NettyServerNode node) throws URISyntaxException {
		String nodeName = node.getNodeName();
		if (!nodeName.equals(currentNodeName)) {
			String url = node.getUrl()+"?uname="+currentNodeName+"&pwd=pwd";
			String subprotocol = node.getSubprotocol();
			pools.put(nodeName, WebSocketClientPool.createObjectPool(url, subprotocol));
			nodes.put(nodeName, node);
		}
	}

	/**
	 * 修改
	 * 
	 * @param enode
	 * @throws URISyntaxException
	 */
	public void editServer(NettyServerNode enode) throws URISyntaxException {
		String key = enode.getNodeName();
		if (!currentNodeName.equals(key)) {
			if (nodes.get(key).getUrl().equals(enode.getUrl())) {// url没有变化，则不需要改变pool
				nodes.put(key, enode);
			} else {
				addServer(enode);// 新增
			}
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
		pools.remove(key);
	}

	public void write(MessageLite obj) {
		pools.forEach((k, v) -> {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					WebSocketJavaClient client = null;
					try {
						client = v.borrowObject();
						client.write(obj);
					} catch (Exception e) {
						LOGGER.log(Level.SEVERE, "写入其他websocket服务器出错", e);
					} finally {
						if (client != null) {
							v.returnObject(client);
						}
					}
				}
			});
		});
	}
}
