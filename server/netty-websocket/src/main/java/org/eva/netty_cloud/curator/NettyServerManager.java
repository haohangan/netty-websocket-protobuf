package org.eva.netty_cloud.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

public class NettyServerManager {
	private String pnode;
	private String connectionString;

	NettyServerNode current;

	static int connectionTimeoutMs = 15 * 1000;
	static int sessionTimeoutMs = 60 * 1000;

	CuratorFramework client = null;
	PathChildrenCache cache = null;

	public NettyServerManager(String pnodeName, String connectString, NettyServerNode current) {
		super();
		this.current = current;
		this.pnode = pnodeName;
		this.connectionString = connectString;
	}

	public void initClouds() throws Exception {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).build();
		client.start();// 初始化zookeeper连接

		client.create().forPath(pnode + "/" + current.getNodeName(), current.toJson().getBytes());

		cache = new PathChildrenCache(client, pnode, true);// 初始化缓存并监听
		cache.start();
		PathChildrenCacheListener listener = new ServerCacheListener();
		cache.getListenable().addListener(listener);

	}

	public void closeCloud() throws Exception {
		try {
			client.delete().forPath(pnode + "/" + current.getNodeName());
		} finally {
			CloseableUtils.closeQuietly(cache);
			CloseableUtils.closeQuietly(client);
		}
	}
}
