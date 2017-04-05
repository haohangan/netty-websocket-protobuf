package org.eva.netty_cloud.curator;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Crud {
	public static void main(String[] args) throws Exception {

		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		String connectString = "192.168.0.108:2181";
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
		client.start();
//		client.create().forPath("/app", "this is netty server".getBytes(StandardCharsets.UTF_8));
		System.out.println(new String(client.getData().forPath("/app")));
		client.close();
	}
}
