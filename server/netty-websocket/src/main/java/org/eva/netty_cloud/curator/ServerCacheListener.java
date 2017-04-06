package org.eva.netty_cloud.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;

public class ServerCacheListener implements PathChildrenCacheListener {

	@Override
	public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
		switch (event.getType()) {
		case CHILD_ADDED: {
			System.out.println("Node added: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
			String json = new String(event.getData().getData());
			NettyServerNode node = NettyServerNode.Builder.buildFromJson(json);
			ServerClouds.INSTANCE.addServer(node);
			break;
		}

		case CHILD_UPDATED: {
			System.out.println("Node changed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
			String json = new String(event.getData().getData());
			NettyServerNode node = NettyServerNode.Builder.buildFromJson(json);
			ServerClouds.INSTANCE.editServer(node);
			break;
		}

		case CHILD_REMOVED: {
			System.out.println("Node removed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
			String json = new String(event.getData().getData());
			NettyServerNode node = NettyServerNode.Builder.buildFromJson(json);
			ServerClouds.INSTANCE.deleteServer(node);
			break;
		}
		case CONNECTION_LOST:
			break;
		case CONNECTION_RECONNECTED:
			break;
		case CONNECTION_SUSPENDED:
			break;
		case INITIALIZED:
			break;
		default:
			break;
		}
	}

}
