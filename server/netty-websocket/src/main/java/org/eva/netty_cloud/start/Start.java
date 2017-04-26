package org.eva.netty_cloud.start;

import org.eva.netty_cloud.handler.WSCloudServer;

import com.google.common.base.Strings;

public class Start {
	public static void main(String[] args) {

		int port = Integer.parseInt(args[0]);
		String app = args[1];
		String zookeeper = args[2];
		if(Strings.isNullOrEmpty(zookeeper)){
			zookeeper = "localhost:2181";
		}
		try {
			new WSCloudServer().run(port, app, zookeeper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
